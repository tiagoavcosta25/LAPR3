create or replace function checkIfAddressExists(
                                      p_latitude IN Address.latitude%type, p_longitude IN Address.longitude%type,
                                      p_altitude IN Address.altitude%type,
                                      p_streetName IN Address.streetName%type, p_doorNumber IN Address.doorNumber%type,
                                      p_postalCode IN Address.postalCode%type, p_locality IN Address.locality%type,
                                      p_country IN Address.country%type) return ADDRESS.id%type
    is
    addressIdentifier Address.id%type;
begin

-- Checks if address exists
    select id
    into addressIdentifier
    from ADDRESS
    where LATITUDE = p_latitude
      and LONGITUDE = p_longitude
      and ALTITUDE = p_altitude
      and DOORNUMBER = p_doorNumber
      and STREETNAME = p_streetName
      and POSTALCODE = p_postalCode
      and LOCALITY = p_locality
      and COUNTRY = p_country;

    return addressIdentifier;

    Exception when NO_DATA_FOUND then
    return -1;

end;