create or replace procedure checkIfAddressExists(
                                      p_latitude IN Address.latitude%type, p_longitude IN Address.longitude%type,
                                      p_altitude IN Address.altitude%type,
                                      p_streetName IN Address.streetName%type, p_doorNumber IN Address.doorNumber%type,
                                      p_postalCode IN Address.postalCode%type, p_locality IN Address.locality%type,
                                      p_country IN Address.country%type,
                                      o_latitude OUT Address.latitude%type, o_longitude OUT Address.longitude%type)
    is
begin

-- Checks if address exists
    select a.LATITUDE, a.LONGITUDE
    into o_latitude,o_longitude
    from ADDRESS a
    where a.LATITUDE = p_latitude
      and a.LONGITUDE = p_longitude
      and a.ALTITUDE = p_altitude
      and a.DOORNUMBER = p_doorNumber
      and a.STREETNAME = p_streetName
      and a.POSTALCODE = p_postalCode
      and a.LOCALITY = p_locality
      and a.COUNTRY = p_country;



    Exception when NO_DATA_FOUND then
    o_latitude := -1;
    o_longitude := -1;

end;