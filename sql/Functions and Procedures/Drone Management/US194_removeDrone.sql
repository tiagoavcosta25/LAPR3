CREATE OR REPLACE PROCEDURE removeDrone(p_id IN VEHICLE.ID%TYPE)
IS
    v_batteryId BATTERY.ID%type;
BEGIN

    DELETE from VEHICLE where id = p_id;

    SELECT BATTERYID
    INTO v_batteryId
    FROM VEHICLE
    WHERE ID = p_id;

    DELETE from BATTERY WHERE ID = v_batteryId;

    DELETE from DRONE WHERE VEHICLEID = p_id;

end;