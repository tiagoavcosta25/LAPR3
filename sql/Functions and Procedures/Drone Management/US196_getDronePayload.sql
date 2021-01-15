create or replace function getDronePayload(droneId DRONE.VEHICLEID%TYPE) RETURN VEHICLE.MAXPAYLOAD%TYPE
    IS
    payload VEHICLE.MAXPAYLOAD%TYPE;
    drone_not_found exception;
BEGIN

        select V.MAXPAYLOAD into payload
        FROM VEHICLE V
                 INNER JOIN DRONE D ON D.VEHICLEID = V.ID
        where droneId = V.ID;

    if payload is null then
        raise drone_not_found;
    end if;
    RETURN payload;
EXCEPTION
    when drone_not_found then
        raise_application_error(-20042, 'Drone Not Found!');
        return null;
end;