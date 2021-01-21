create or replace procedure addInvoiceLine(p_line INVOICELINE.id%type, p_invoiceId INVOICE.id%type, p_orderId "Order".id%type,
                                    p_productId PRODUCT.ID%type, p_value INVOICELINE.VALUE%type)
    is
    v_checkInvoiceId INVOICE.ID%type;
    v_checkOrderId "Order".ID%type;
    v_checkProductId PRODUCT.ID%type;
    invoice_not_found exception;
    order_not_found exception;
    product_not_found exception;
begin

    select ID
    into v_checkInvoiceId
    from INVOICE
    where ID = p_invoiceId;

    if v_checkInvoiceId is null then
        raise invoice_not_found;
    end if;

    select ID
    into v_checkOrderId
    from "Order"
    where ID = p_orderId;

    if v_checkOrderId is null then
        raise order_not_found;
    end if;

    select id
    into v_checkProductId
    from PRODUCT
    where ID = p_productId;

    if v_checkProductId is null then
        raise product_not_found;
    end if;

-- Creates a new InvoiceLine

    Insert into INVOICELINE(ID, ORDERID, PRODUCTID, VALUE, INVOICEID)
    Values (p_line, p_orderId, p_productId, p_value, p_invoiceId);

EXCEPTION
    when invoice_not_found then
        raise_application_error(-20519, 'Invoice Not Found!');

    when order_not_found then
        raise_application_error(-20619, 'Order Not Found!');

    when product_not_found then
        raise_application_error(-20719, 'Product Not Found!');

end;