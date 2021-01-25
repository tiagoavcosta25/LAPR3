create or replace function getVehiclePayload(vehicleId VEHICLE.ID%TYPE) RETURN VEHICLEMODEL.MAXPAYLOAD%TYPE
    IS
    payload VEHICLEMODEL.MAXPAYLOAD%TYPE;
    vehicle_not_found exception;
BEGIN

    select VM.MAXPAYLOAD into payload
    FROM VEHICLEMODEL VM
             INNER JOIN VEHICLE V ON V.MODELID = VM.ID
    where vehicleId = V.ID;

    if payload is null then
        raise vehicle_not_found;
    end if;
    RETURN payload;
EXCEPTION
    when vehicle_not_found then
        raise_application_error(-20048, 'Drone Not Found!');
        return null;
end;