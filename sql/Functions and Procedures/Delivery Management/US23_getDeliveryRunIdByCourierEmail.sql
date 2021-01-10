CREATE OR REPLACE FUNCTION getDeliveryRunIdByCourierEmail(paramEmail "User".EMAIL%type) return DELIVERYRUN.ID%TYPE is
    deliveryRunId DELIVERYRUN.ID%TYPE;
    deliveryRun_not_found exception;
BEGIN
        SELECT DR.ID INTO deliveryRunId FROM DELIVERYRUN DR
                 INNER JOIN COURIER C on C.USERID = DR.COURIERID
                 INNER JOIN "User" U ON U.ID = C.USERID
        WHERE DR.DELIVERYSTATUS = 'idle' and U.EMAIL = paramEmail;
        if deliveryRunId is null then
            raise deliveryRun_not_found;
        end if;
        return deliveryRunId;
EXCEPTION
    when deliveryRun_not_found then
        raise_application_error(-20082, 'Order Not Found!');
        return null;

END;