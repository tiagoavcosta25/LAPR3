create or replace function getOrderByCourier(p_email "User".EMAIL%type) RETURN SYS_REFCURSOR is
    v_order SYS_REFCURSOR;
    order_not_found exception;
begin
    open v_order for
        select O.ID, O.DESCRIPTION, O.ORDERSTATUS, O.ORDERDATE, O.TOTALWEIGHT, O.AMOUNT, O.ADDITIONALFEE, O.ISHOMEDELIVERY, U.*,
               C.CREDITS, A1.*, P.ID, P.NAME, P.EMAIL, A3.*
        from "Order" O
                 inner join CLIENT C on O.CLIENTID = C.USERID
                 inner join "User" U on C.USERID = U.ID
                 inner join ADDRESS A1 on (A1.LATITUDE = C.ADDRESSLATITUDE AND A1.LONGITUDE = C.ADDRESSLONGITUDE)
                 inner join PHARMACY P on O.PHARMACYID = P.ID
                 inner join ADDRESS A3 on (P.ADDRESSLATITUDE = A3.LATITUDE AND P.ADDRESSLONGITUDE = A3.LONGITUDE)
                 INNER JOIN "User" U2 ON U.EMAIL = p_email
                 inner join COURIER C ON C.USERID = U.ID
                 INNER JOIN DELIVERYRUN DR ON DR.COURIERID = C.USERID
        where DR.DELIVERYSTATUS = 'Idle';

    if v_order is null then
        raise order_not_found;
    end if;

    return v_order;

EXCEPTION
    when order_not_found then
        raise_application_error(-20020, 'Order Not Found!');
        return null;

end;