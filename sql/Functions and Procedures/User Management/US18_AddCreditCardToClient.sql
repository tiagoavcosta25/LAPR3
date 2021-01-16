create or replace procedure addCreditCardToClient(p_userId IN "User".id%type,
                                                  p_creditCardNr IN CreditCard.CREDITCARDNR%type,
                                                  p_validityDate IN CreditCard.VALIDITYDATE%type,
                                                  p_ccv IN CreditCard.CCV%type)
    is
    creditCardId      CreditCard.creditCardNr%type;
begin

    Insert into CreditCard(CREDITCARDNR, VALIDITYDATE, CCV)
    Values (p_creditCardNr, p_validityDate, p_ccv)
    returning CREDITCARDNR into creditCardId;

    -- Creates a new Credit Card
-- VERIFICAR SE CREDIT CARD JA EXISTE, SE SIM SALTAR ESTA PARTE À FRENTE

    creditCardId := CHECKIFCREDITCARDEXISTS(p_creditCardNr);

    if creditCardId = -1 then
        Insert into CreditCard(CREDITCARDNR, VALIDITYDATE, CCV)
        Values (p_creditCardNr, p_validityDate, p_ccv)
        returning CREDITCARDNR into creditCardId;
    end if;

    -- Creates a new Credit Card Client
    Insert into CreditCardClient(CREDITCARDNR, CLIENTID)
    Values (creditCardId, p_userId);


end;