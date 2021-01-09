create or replace function addOrder(p_amount "Order".AMOUNT%type, p_totalWeight "Order".TOTALWEIGHT%type, p_additionalFee "Order".ADDITIONALFEE%type,
                                     p_description "Order".DESCRIPTION%type, p_date "Order".ORDERDATE%type,
                                     p_clientId Client.USERID%type, p_latitude ADDRESS.LATITUDE%type, p_longitude ADDRESS.LONGITUDE%type,
                                     p_streetName ADDRESS.STREETNAME%type, p_doorNumber ADDRESS.DOORNUMBER%type, p_postalCode ADDRESS.POSTALCODE%type,
                                     p_locality ADDRESS.LOCALITY%type, p_country ADDRESS.COUNTRY%type)
    return number is
    v_checkClientId            CLIENT.USERID%type;
    v_addressId Address.id%type;
    v_orderId "Order".id%type;
    client_not_found exception;
begin

    select USERID
    into v_checkClientId
    from CLIENT
    where USERID = p_clientId;

    if v_checkClientId is null then
        raise client_not_found;
    end if;

-- Creates a new Address
    select id
    into v_addressId
    from ADDRESS
    where LATITUDE = p_latitude
      and LONGITUDE = p_longitude
      and DOORNUMBER = p_doorNumber
      and STREETNAME = p_streetName
      and POSTALCODE = p_postalCode
      and LOCALITY = p_locality
      and COUNTRY = p_country;

    if v_addressId is null then
        Insert into Address(LATITUDE, LONGITUDE, DOORNUMBER, STREETNAME, POSTALCODE, LOCALITY, COUNTRY)
        Values (p_latitude, p_longitude, p_doorNumber, p_streetName, p_postalCode, p_locality, p_country)
        returning id into v_addressId;
    end if;

-- Creates a new Order
    Insert into "Order"(AMOUNT, TOTALWEIGHT, ADDITIONALFEE, DESCRIPTION, ORDERDATE, CLIENTID, ADDRESSID)
    Values (p_amount, p_totalWeight, p_additionalFee, p_description, p_date, p_clientId, v_addressId);

    select id
    into v_orderId
    from "Order"
    where AMOUNT = p_amount
      and TOTALWEIGHT = p_totalWeight
      and ADDITIONALFEE = p_additionalFee
      and DESCRIPTION = p_description
      and ORDERDATE = p_date
      and CLIENTID = p_clientId
      and ADDRESSID = v_addressId;

    return v_orderId;

EXCEPTION
    when client_not_found then
        raise_application_error(-20219, 'Client Not Found!');
        return null;

end;