create or replace procedure addPharmacy(p_name PHARMACY.NAME%type, p_email PHARMACY.EMAIL%type,
                                        p_latitude ADDRESS.LATITUDE%type, p_longitude ADDRESS.LONGITUDE%type, p_altitude ADDRESS.ALTITUDE%type,
                                        p_streetName ADDRESS.STREETNAME%type, p_doorNumber ADDRESS.DOORNUMBER%type, p_postalCode ADDRESS.POSTALCODE%type,
                                        p_locality ADDRESS.LOCALITY%type, p_country ADDRESS.COUNTRY%type)
    is
    v_checkAddressLatitude Address.LATITUDE%type;
    v_checkAddressLongitude Address.LONGITUDE%type;
begin

    -- Creates a new Address
    CHECKIFADDRESSEXISTS(p_latitude, p_longitude, p_altitude, p_streetName,
                         p_doorNumber, p_postalCode, p_locality, p_country,
                         v_checkAddressLatitude, v_checkAddressLongitude);

    if v_checkAddressLatitude = -1 and v_checkAddressLongitude = -1 then
        Insert into Address(LATITUDE, LONGITUDE, ALTITUDE, DOORNUMBER, STREETNAME, POSTALCODE, LOCALITY, COUNTRY)
        Values (p_latitude, p_longitude, p_altitude, p_doorNumber, p_streetName, p_postalCode, p_locality, p_country);
    end if;

-- Creates a new Pharmacy
    Insert into PHARMACY(NAME, EMAIL, ADDRESSLATITUDE, ADDRESSLONGITUDE)
    Values (p_name, p_email, p_latitude, p_longitude);

end;
/

