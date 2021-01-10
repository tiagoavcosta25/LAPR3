CREATE OR REPLACE FUNCTION getMaxPayload(strEmail "User".EMAIL%type) RETURN SCOOTER.MAXPAYLOAD AS
    maxPayloadScooter SCOOTER.MAXPAYLOAD;
    payload_not_found exception;
BEGIN

    SELECT DISTINCT S.MAXPAYLOAD INTO maxPayloadScooter FROM SCOOTER S
        INNER JOIN DELIVERYRUN DR ON DR.SCOOTERID = S.ID
        INNER JOIN "User" U on DR.COURIERID = U.ID
    WHERE U.EMAIL = strEmail AND DR.DELIVERYSTATUS = 'idle';;

    IF maxPayloadScooter IS NULL THEN
        RAISE payload_not_found;
    end if;
    RETURN maxPayloadScooter;

EXCEPTION
    WHEN payload_not_found THEN
        raise_application_error(-20070, 'Address Not Found!');
        RETURN NULL;
END;