create or replace procedure addPharmacy(p_name PHARMACY.NAME%type, p_email PHARMACY.EMAIL%type, p_managerId PHARMACYMANAGER.USERID%type,
                                       p_latitude ADDRESS.LATITUDE%type, p_longitude ADDRESS.LONGITUDE%type,
                                       p_streetName ADDRESS.STREETNAME%type, p_doorNumber ADDRESS.DOORNUMBER%type, p_postalCode ADDRESS.POSTALCODE%type,
                                       p_locality ADDRESS.LOCALITY%type, p_country ADDRESS.COUNTRY%type)
    is
    v_checkManagerId PHARMACYMANAGER.USERID%type;
    v_addressId Address.id%type;
    manager_not_found exception;
begin

    select USERID
    into v_checkManagerId
    from PHARMACYMANAGER
    where USERID = p_managerId;

    if v_checkManagerId is null then
        raise manager_not_found;
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

-- Creates a new Pharmacy
    Insert into PHARMACY(NAME, EMAIL, MANAGERID, ADDRESSID)
    Values (p_name, p_email, p_managerId, v_addressId);

EXCEPTION
    when manager_not_found then
        raise_application_error(-20025, 'Client Not Found!');

end;