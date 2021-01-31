create or replace function getVehiclePayload(vehicleId VEHICLE.ID%TYPE, p_email "User".EMAIL%TYPE) RETURN VEHICLEMODEL.MAXPAYLOAD%TYPE
    IS
    payload VEHICLEMODEL.MAXPAYLOAD%TYPE;
    vehicle_not_found exception;
BEGIN
    select VM.MAXPAYLOAD
    into payload
    FROM VEHICLEMODEL VM
             INNER JOIN VEHICLE V ON V.MODELID = VM.ID
        AND V.ID IN (SELECT V2.ID
                     FROM VEHICLE V2
                              INNER JOIN "User" U ON U.EMAIL = p_email
                              INNER JOIN COURIER C ON C.USERID = U.ID
                              INNER JOIN PHARMACY P ON C.PHARMACYID = P.ID
                     WHERE V2.PHARMACYID = P.ID)
    where vehicleId = V.ID;

    if payload is null then
        raise vehicle_not_found;
    end if;
    RETURN payload;
EXCEPTION
    when vehicle_not_found then
        raise_application_error(-20158, 'Vehicle Not Found!');
        return null;
end;
