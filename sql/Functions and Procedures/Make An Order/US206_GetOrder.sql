create or replace function getOrder(p_orderId IN "Order".ID%type)
    return sys_refcursor is
    v_cursor sys_refcursor;
    order_not_found exception;
begin

    open v_cursor for
    select O.ID, O.DESCRIPTION, O.ORDERSTATUS, O.ORDERDATE, O.TOTALWEIGHT, O.AMOUNT, O.ADDITIONALFEE, O.ISHOMEDELIVERY, U.*,
           C.CREDITS, A1.*, P.ID, P.NAME, P.EMAIL, A2.*
    from "Order" O
             inner join CLIENT C on O.CLIENTID = C.USERID
             inner join "User" U on C.USERID = U.ID
             inner join ADDRESS A1 on A1.LONGITUDE = C.ADDRESSLONGITUDE and A1.LATITUDE = C.ADDRESSLATITUDE
             inner join PHARMACY P on O.PHARMACYID = P.ID
             inner join ADDRESS A2 on A2.LONGITUDE = P.ADDRESSLONGITUDE and A2.LATITUDE = P.ADDRESSLATITUDE
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