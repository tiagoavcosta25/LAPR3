create or replace function checkIfCreditCardExists(
                                      p_creditCardNr IN CreditCard.creditCardNr%type)
                                      return CreditCard.creditCardNr%type
    is
    creditCardId      CreditCard.creditCardNr%type;
begin

-- Checks if credit card exists
    select CREDITCARDNR
    into creditCardId
    from CREDITCARD
    where CREDITCARDNR = p_creditCardNr;

    return creditCardId;

    Exception when NO_DATA_FOUND then
    return -1;

end;