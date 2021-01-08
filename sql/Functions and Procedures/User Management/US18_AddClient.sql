create or replace procedure addClient(p_name IN "User".name%type, p_nif IN "User".nif%type, p_credits IN Client.credits%type,
                                      p_latitude IN Address.latitude%type, p_longitude IN Address.longitude%type,
                                      p_streetName IN Address.streetName%type, p_doorNumber IN Address.doorNumber%type,
                                      p_postalCode IN Address.postalCode%type, p_locality IN Address.locality%type,
                                      p_country IN Address.country%type, p_creditCardNr IN CreditCard.creditCardNr%type,
                                      p_validityDate IN CreditCard.validityDate%type, p_ccv IN CreditCard.CCV%type,
                                      p_email IN "User".email%type, p_password IN "User".password%type)
    is
    userIdentifier    "User".id%type;
    addressIdentifier Address.id%type;
    creditCardId      CreditCard.creditCardNr%type;
begin

    -- Creates a new User
    Insert into "User"(EMAIL, PASSWORD, NIF, NAME)
    Values (p_email, p_password, p_nif, p_name)
    returning id into userIdentifier;

    addressIdentifier := CHECKIFADDRESSEXISTS(p_latitude,p_longitude,p_streetName,
        p_doorNumber,p_postalCode,p_locality,p_country);

    if addressIdentifier = -1 then
        Insert into Address(LATITUDE, LONGITUDE, DOORNUMBER, STREETNAME, POSTALCODE, LOCALITY, COUNTRY)
        Values (p_latitude, p_longitude, p_doorNumber, p_streetName, p_postalCode, p_locality, p_country)
        returning id into addressIdentifier;
    end if;

    -- Creates a new Credit Card
-- VERIFICAR SE CREDIT CARD JA EXISTE, SE SIM SALTAR ESTA PARTE À FRENTE

    creditCardId := CHECKIFCREDITCARDEXISTS(p_creditCardNr);

    if creditCardId = -1 then
        Insert into CreditCard(CREDITCARDNR, VALIDITYDATE, CCV)
        Values (p_creditCardNr, p_validityDate, p_ccv)
        returning CREDITCARDNR into creditCardId;
    end if;

-- Creates a new Client
    Insert into Client(userId, CREDITS, ADDRESSID)
    Values (userIdentifier, p_credits, addressIdentifier);

-- Creates a new Credit Card Client
    Insert into CreditCardClient(CREDITCARDNR, CLIENTID)
    Values (creditCardId, userIdentifier);




end;