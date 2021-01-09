create or replace procedure addInvoiceLine(p_line INVOICELINE.id%type, p_invoiceId INVOICE.id%type, p_orderId "Order".id%type,
                                    p_productId PRODUCT.ID%type, p_name PRODUCT.name%type,
                                    p_description PRODUCT.DESCRIPTION%type, p_unitaryPrice PRODUCT.UNITARYPRICE%type,
                                    p_unitaryWeight PRODUCT.UNITARYWEIGHT%type, p_value INVOICELINE.VALUE%type)
    is
    v_checkOrderId CLIENT.USERID%type;
    v_checkProductId PRODUCT.ID%type;
    invoice_not_found exception;
    order_not_found exception;
    product_not_found exception;
begin

    select ID
    into v_checkOrderId
    from "Order"
    where ID = p_orderId;

    if v_checkOrderId is null then
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
    where ID = p_productId
      and NAME = p_name
      and DESCRIPTION = p_description
      and UNITARYPRICE = p_unitaryPrice
      and UNITARYWEIGHT = p_unitaryWeight;

    if v_checkProductId is null then
        raise product_not_found;
    end if;

-- Creates a new InvoiceLine

    Insert into INVOICELINE(ID, ORDERID, PRODUCTID, VALUE)
    Values (p_line, p_orderId, p_productId, p_value);

EXCEPTION
    when invoice_not_found then
        raise_application_error(-20519, 'Invoice Not Found!');

    when order_not_found then
        raise_application_error(-20619, 'Order Not Found!');

    when product_not_found then
        raise_application_error(-20719, 'Product Not Found!');

end;