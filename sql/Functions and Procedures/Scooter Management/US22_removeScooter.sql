CREATE OR REPLACE PROCEDURE removeScooter(p_id IN VEHICLE.ID%TYPE)
IS
    v_vehicle VEHICLE.ID%TYPE;
BEGIN

    SELECT VEHICLEID into v_vehicle
    FROM SCOOTER
    WHERE VEHICLEID = p_id;

    DELETE from SCOOTER WHERE VEHICLEID = p_id;

    DELETE from VEHICLE where id = p_id;

EXCEPTION
    when no_data_found then
        raise_application_error(-20039, 'Vehicle Not Found!');
end;