create or replace procedure addClient(p_name "User".name%type, p_nif "User".nif%type, p_credits Client.credits%type,
                                      p_latitude Address.latitude%type, p_longitude Address.longitude%type,
                                      p_streetName Address.streetName%type, p_doorNumber Address.doorNumber%type,
                                      p_postalCode Address.postalCode%type, p_locality Address.locality%type,
                                      p_country Address.country%type, p_creditCardNr CreditCard.creditCardNr%type,
                                      p_validityDate CreditCard.validityDate%type, p_ccv CreditCard.CCV%type,
                                      p_email "User".email%type, p_password "User".password%type)
    is
    userIdentifier    "User".id%type;
    addressIdentifier Address.id%type;
    creditCardId      CreditCard.creditCardNr%type;
begin

    -- Creates a new User
    Insert into "User"(EMAIL, PASSWORD, NIF, NAME)
    Values (p_email, p_password, p_nif, p_name)
    returning id into userIdentifier;

-- Creates a new Address
    select id
    into addressIdentifier
    from ADDRESS
    where LATITUDE = p_latitude
      and LONGITUDE = p_longitude
      and DOORNUMBER = p_doorNumber
      and STREETNAME = p_streetName
      and POSTALCODE = p_postalCode
      and LOCALITY = p_locality
      and COUNTRY = p_country;

    if addressIdentifier is null then
        Insert into Address(LATITUDE, LONGITUDE, DOORNUMBER, STREETNAME, POSTALCODE, LOCALITY, COUNTRY)
        Values (p_latitude, p_longitude, p_doorNumber, p_streetName, p_postalCode, p_locality, p_country)
        returning id into addressIdentifier;
    end if;

    -- Creates a new Credit Card
-- VERIFICAR SE CREDIT CARD JA EXISTE, SE SIM SALTAR ESTA PARTE À FRENTE
    select CREDITCARDNR
    into creditCardId
    from CREDITCARD
    where CREDITCARDNR = p_creditCardNr;

    if creditCardId is null then
        Insert into CreditCard(CREDITCARDNR, VALIDITYDATE, CCV)
        Values (p_creditCardNr, p_validityDate, p_ccv)
        returning CREDITCARDNR into creditCardId;
    end if;

-- Creates a new Credit Card Client
    Insert into CreditCardClient(CREDITCARDNR, CLIENTID)
    Values (creditCardId, userIdentifier);

-- Creates a new Client
    Insert into Client(userId, CREDITS, ADDRESSID)
    Values (userIdentifier, p_credits, addressIdentifier);


end;