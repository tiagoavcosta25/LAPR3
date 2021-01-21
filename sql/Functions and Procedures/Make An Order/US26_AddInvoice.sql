create or replace function addInvoice(p_date INVOICE.INVOICEDATE%type, p_totalPrice INVOICE.TOTALPRICE%type, p_orderId INVOICE.ORDERID%type)
    return INVOICE.ID%type is
    v_InvoiceId    INVOICE.ID%type;
    client_not_found exception;
begin

        Insert into Invoice(INVOICEDATE, TOTALPRICE, ORDERID)
        Values (p_date, p_totalPrice, p_orderId)
        returning id into v_InvoiceId;

    return v_InvoiceId;
end;