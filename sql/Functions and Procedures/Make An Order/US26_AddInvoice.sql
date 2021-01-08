create or replace function addInvoice(p_date INVOICE.INVOICEDATE%type, p_totalPrice INVOICE.TOTALPRICE%type, p_orderId INVOICE.ORDERID%type)
    return number is
    v_checkInvoice     INVOICE.ID%type;
    client_not_found exception;
begin

-- Creates a new Invoice
    select id
    into v_checkInvoice
    from INVOICE
    where INVOICEDATE = p_date
      and TOTALPRICE = p_totalPrice
      and ORDERID = p_orderId;

    if v_checkInvoice is null then
        Insert into Invoice(INVOICEDATE, TOTALPRICE, ORDERID)
        Values (p_date, p_totalPrice, p_orderId)
        returning id into v_checkInvoice;
    end if;

    return v_checkInvoice;
end;