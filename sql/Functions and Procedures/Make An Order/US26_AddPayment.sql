create or replace procedure addPayment(p_invoiceId INVOICE.id%type, p_ccNum CREDITCARD.CREDITCARDNR%type, p_value PAYMENT.VALUE%type)
    is
    v_checkInvoice INVOICE.ID%type;
    v_checkCC CREDITCARD.CREDITCARDNR%type;
    invoice_not_found exception;
    cc_not_found exception;
begin

    select ID
    into v_checkInvoice
    from INVOICE
    where ID = p_invoiceId;

    if v_checkInvoice is null then
        raise invoice_not_found;
    end if;

    select CREDITCARDNR
    into v_checkCC
    from CREDITCARD
    where CREDITCARDNR = CREDITCARDNR;

    if v_checkCC is null then
        raise cc_not_found;
    end if;

-- Creates a new PAYMENT

    Insert into PAYMENT(INVOICEID, CREDITCARDNR, VALUE)
    Values (p_invoiceId, p_ccNum, p_value);

EXCEPTION
    when invoice_not_found then
        raise_application_error(-20519, 'Invoice Not Found!');

    when cc_not_found then
        raise_application_error(-20719, 'Credit Card Not Found!');

end;