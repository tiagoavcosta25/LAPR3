create or replace function addClient(p_name IN "User".name%type, p_nif IN "User".nif%type, p_credits IN Client.credits%type,
                                      p_latitude IN Address.latitude%type, p_longitude IN Address.longitude%type,
                                      p_altitude IN Address.altitude%type,
                                      p_streetName IN Address.streetName%type, p_doorNumber IN Address.doorNumber%type,
                                      p_postalCode IN Address.postalCode%type, p_locality IN Address.locality%type,
                                      p_country IN Address.country%type,
                                      p_email IN "User".email%type, p_password IN "User".password%type)
                                      return int
    is
    userIdentifier    "User".id%type;
    addressIdentifier Address.id%type;
    client_not_created_exception EXCEPTION;
begin

    -- Creates a new User
    Insert into "User"(EMAIL, PASSWORD, NIF, NAME)
    Values (p_email, p_password, p_nif, p_name)
    returning id into userIdentifier;

    addressIdentifier := CHECKIFADDRESSEXISTS(p_latitude,p_longitude, p_altitude,p_streetName,
        p_doorNumber,p_postalCode,p_locality,p_country);

    if addressIdentifier = -1 then
        Insert into Address(LATITUDE, LONGITUDE, ALTITUDE, DOORNUMBER, STREETNAME, POSTALCODE, LOCALITY, COUNTRY)
        Values (p_latitude, p_longitude, p_altitude, p_doorNumber, p_streetName, p_postalCode, p_locality, p_country)
        returning id into addressIdentifier;
    end if;


-- Creates a new Client
    Insert into Client(userId, CREDITS, ADDRESSID)
    Values (userIdentifier, p_credits, addressIdentifier);

    if userIdentifier is null then
        raise client_not_created_exception;
    end if;

    return userIdentifier;

    EXCEPTION
    when client_not_created_exception then
    return -1;


end;