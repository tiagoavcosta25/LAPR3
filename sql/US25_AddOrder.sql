create or replace procedure addOrder(p_amount "Order".amount%type, p_totalWeight "Order".TOTALWEIGHT%type, p_additionalFee "Order".ADDITIONALFEE%type,
                                     p_description "Order".DESCRIPTION%type, p_date "Order".ORDERDATE%type,
                                     p_clientId Client.USERID%type, p_latitude ADDRESS.LATITUDE%type, p_longitude ADDRESS.LONGITUDE%type,
                                     p_streetName ADDRESS.STREETNAME%type, p_doorNumber ADDRESS.DOORNUMBER%type, p_postalCode ADDRESS.POSTALCODE%type,
                                     p_locality ADDRESS.LOCALITY%type, p_country ADDRESS.COUNTRY%type)
    is
    checkClientID            CLIENT.USERID%type;
    addressId Address.id%type;
    client_not_found exception;
begin

    select USERID
    into checkClientID
    from CLIENT
    where USERID = p_clientId;

    if checkClientID is null then
        raise client_not_found;
    end if;

-- Creates a new Address
    select id
    into addressId
    from ADDRESS
    where LATITUDE = p_latitude
      and LONGITUDE = p_longitude
      and DOORNUMBER = p_doorNumber
      and STREETNAME = p_streetName
      and POSTALCODE = p_postalCode
      and LOCALITY = p_locality
      and COUNTRY = p_country;

    if addressId is null then
        Insert into Address(LATITUDE, LONGITUDE, DOORNUMBER, STREETNAME, POSTALCODE, LOCALITY, COUNTRY)
        Values (p_latitude, p_longitude, p_doorNumber, p_streetName, p_postalCode, p_locality, p_country)
        returning id into addressId;
    end if;

-- Creates a new Order
    Insert into "Order"(amount, TOTALWEIGHT, ADDITIONALFEE, DESCRIPTION, ORDERDATE, CLIENTUSERID, ADDRESSID)
    Values (p_amount, p_totalWeight, p_additionalFee, p_description, p_date, p_clientId, addressId);

EXCEPTION
    when client_not_found then
        raise_application_error(-20025, 'Client Not Found!');

end;