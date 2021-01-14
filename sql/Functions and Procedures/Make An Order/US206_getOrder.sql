create or replace function getOrder(p_orderId IN "Order".id%type)
    return sys_refcursor is
    v_cursor sys_refcursor;
    order_not_found exception;
begin

    open v_cursor for
    select O.ID, O.DESCRIPTION, O.ORDERSTATUS, O.ORDERDATE, O.TOTALWEIGHT, O.AMOUNT, O.ADDITIONALFEE, A.*, U.*,
           C.CREDITS, A1.*, CC.*, P.ID, P.NAME, A3.*
    from "Order" O
             inner join ADDRESS A on O.ADDRESSID = A.ID
             inner join CLIENT C on O.CLIENTID = C.USERID
             inner join "User" U on C.USERID = U.ID
             inner join ADDRESS A1 on A1.ID = C.ADDRESSID
             inner join CREDITCARDCLIENT CCC on C.USERID = CCC.CLIENTID
             inner join CREDITCARD CC on CC.CREDITCARDNR = CCC.CREDITCARDNR
             inner join PHARMACY P on O.PHARMACYID = P.ID
             inner join ADDRESS A3 on P.ADDRESSID = A3.ID
    where O.ID = p_orderId;


    if v_cursor is null then
        raise order_not_found;
    end if;

    return v_cursor;

EXCEPTION
    when order_not_found then
        raise_application_error(-20141, 'Order Not Found!');
        return null;

end;