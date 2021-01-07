create or replace function getOrderByCourier(p_email "User".EMAIL%type) RETURN SYS_REFCURSOR is
    v_order SYS_REFCURSOR;
    order_not_found exception;
begin
    open v_order for
        select O.ID, O.DESCRIPTION, O.ORDERSTATUS, O.ORDERDATE, O.TOTALWEIGHT, O.AMOUNT, O.ADDITIONALFEE,
               C.CREDITS, U.*, A.*, CC.*
        from "Order" O
                 inner join CLIENT C on C.USERID = O.CLIENTID
                 inner join CREDITCARDCLIENT CCC on CCC.CLIENTID = C.USERID
                 inner join CREDITCARD CC on CCC.CREDITCARDNR = CC.CREDITCARDNR
                 inner join ADDRESS A on C.ADDRESSID = A.ID
                 inner join "User" U on C.USERID = U.ID
        where U.EMAIL = p_email;

    if v_order is null then
        raise order_not_found;
    end if;

    return v_order;

EXCEPTION
    when order_not_found then
        raise_application_error(-20020, 'Order Not Found!');
        return null;

end;
