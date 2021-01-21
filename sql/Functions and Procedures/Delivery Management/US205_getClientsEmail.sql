create or replace function getClientsEmail(courierEmail "User".EMAIL%TYPE)
    return sys_refcursor is
    v_emails sys_refcursor;
    deliveryRun_not_found exception;
    deliveryRunID DeliveryRun.ID%TYPE;
begin

        SELECT D.ID INTO deliveryRunID
        FROM DELIVERYRUN D INNER JOIN COURIER C3 on C3.USERID = D.COURIERID
                           INNER JOIN "User" U2 on U2.ID = C3.USERID
        WHERE U2.EMAIL = courierEmail AND D.DELIVERYSTATUS = 'idle';

        if deliveryRunID is null then
            raise deliveryRun_not_found;
        end if;

        UPDATE DELIVERYRUN
            SET DELIVERYSTATUS = 'InProgress'
        WHERE ID = deliveryRunID;

    OPEN v_emails FOR
        SELECT U.EMAIL, O.ID, O.DESCRIPTION
        FROM "User" U INNER JOIN CLIENT C2 on U.ID = C2.USERID
                      INNER JOIN "Order" O on C2.USERID = O.CLIENTID
                      INNER JOIN DELIVERYRUN D2 on D2.ID = O.DELIVERYRUNID
        WHERE D2.ID = deliveryRunID;

    if v_emails is null then
        raise deliveryRun_not_found;
    end if;

    return v_emails;

EXCEPTION
    when deliveryRun_not_found then
        raise_application_error(-20812, 'DeliveryRun Not Found!');
        return null;

end;