create or replace function getDroneById(droneId DRONE.VEHICLEID%TYPE) RETURN SYS_REFCURSOR
    IS
    droneInfo SYS_REFCURSOR;
    drone_not_found exception;
BEGIN

    OPEN droneInfo FOR
        select *
        FROM VEHICLE V
                 INNER JOIN DRONE D ON D.VEHICLEID = V.ID
        where droneId = V.ID;

    if droneInfo is null then
        raise drone_not_found;
    end if;
    RETURN DRONEINFO;
EXCEPTION
    when drone_not_found then
        raise_application_error(-20042, 'Drone Not Found!');
        return null;
end;