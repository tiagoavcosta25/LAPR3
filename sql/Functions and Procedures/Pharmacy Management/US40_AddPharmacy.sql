create or replace procedure addPharmacy(p_name PHARMACY.NAME%type, p_email PHARMACY.EMAIL%type,
                                       p_latitude ADDRESS.LATITUDE%type, p_longitude ADDRESS.LONGITUDE%type, p_altitude ADDRESS.ALTITUDE%type,
                                       p_streetName ADDRESS.STREETNAME%type, p_doorNumber ADDRESS.DOORNUMBER%type, p_postalCode ADDRESS.POSTALCODE%type,
                                       p_locality ADDRESS.LOCALITY%type, p_country ADDRESS.COUNTRY%type)
    is
    v_addressLatitude Address.LATITUDE%type;
begin

-- Creates a new Address
    select LATITUDE
    into v_addressLatitude
    from ADDRESS
    where LATITUDE = p_latitude
      and LONGITUDE = p_longitude
      and ALTITUDE = p_altitude
      and DOORNUMBER = p_doorNumber
      and STREETNAME = p_streetName
      and POSTALCODE = p_postalCode
      and LOCALITY = p_locality
      and COUNTRY = p_country;

    if v_addressLatitude is null then
    Insert into Address(LATITUDE, LONGITUDE, ALTITUDE, DOORNUMBER, STREETNAME, POSTALCODE, LOCALITY, COUNTRY)
        Values (p_latitude, p_longitude, p_altitude, p_doorNumber, p_streetName, p_postalCode, p_locality, p_country);
    end if;

-- Creates a new Pharmacy
    Insert into PHARMACY(NAME, EMAIL, ADDRESSLATITUDE, ADDRESSLONGITUDE)
    Values (p_name, p_email, p_latitude, p_longitude);

end;