create or replace function getProductsByOrder(p_orderId "Order".ID%type)
    return sys_refcursor is
    v_cursor sys_refcursor;
    product_not_found exception;
begin

    open v_cursor for
        select OP.QUANTITY, P.*
        from PRODUCT P
        inner join ORDERPRODUCT OP on P.ID = OP.PRODUCTID
    where OP.ORDERID = p_orderId;

    if v_cursor is null then
        raise product_not_found;
    end if;

    return v_cursor;

EXCEPTION
    when product_not_found then
        raise_application_error(-20120, 'Products Not Found!');
        return null;
end;