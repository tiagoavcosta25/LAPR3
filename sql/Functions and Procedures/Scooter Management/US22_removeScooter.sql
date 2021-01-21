CREATE OR REPLACE PROCEDURE removeScooter(p_id IN VEHICLE.ID%TYPE)
    IS
BEGIN

    DELETE from SCOOTER WHERE VEHICLEID = p_id;

    DELETE from VEHICLE where id = p_id;

end;