CREATE OR REPLACE FUNCTION getMaxPayload(paramEmail "User".EMAIL%type) return SCOOTER.MAXPAYLOAD%type is

    maxPayload SCOOTER.MAXPAYLOAD%type;
    invalidPayload exception;
BEGIN
    SELECT maxPayload from SCOOTER S
        INNER JOIN DELIVERYRUN DR ON DR.SCOOTERID = S.ID
        INNER JOIN "User" U on U.ID =DR.COURIERID
        where U.EMAIL = paramEmail;
    return maxPayload;
    EXCEPTION
    when invalidPayload then
        raise_application_error(-20021, 'Max Payload not found!');
return null;
end;

