create or replace function getLatestOrder(p_email "User".EMAIL%type)
    return sys_refcursor is
    v_cursor sys_refcursor;
    v_orderId "Order".id%type;
    order_not_found exception;
begin

    select max("Order".ID)
    into v_orderId
    from "Order"
             inner join "User" on "Order".CLIENTID = "User".ID
    where "User".EMAIL = p_email;

    if v_orderId is null then
        raise order_not_found;
    end if;

    open v_cursor for
        select O.ID, O.DESCRIPTION, O.ORDERSTATUS, O.ORDERDATE, O.TOTALWEIGHT, O.AMOUNT, O.ADDITIONALFEE, U.*, C.CREDITS, A.*, P.ID, P.NAME, U2.*, A2.*
        from "Order" O
                 inner join ADDRESS A on O.ADDRESSID = A.ID
                 inner join CLIENT C on O.CLIENTID = C.USERID
                 inner join "User" U on C.USERID = U.ID
                 inner join PHARMACY P on O.PHARMACYID = P.ID
                 inner join ADDRESS A2 on P.ADDRESSID = A2.ID
                 inner join PHARMACYMANAGER PM on P.MANAGERID = PM.USERID
                 inner join "User" U2 on PM.USERID = U2.ID
        where O.ID = v_orderId;

    if v_cursor is null then
        raise order_not_found;
    end if;

    return v_cursor;

EXCEPTION
    when order_not_found then
        raise_application_error(-20819, 'Order Not Found!');
        return null;

end;