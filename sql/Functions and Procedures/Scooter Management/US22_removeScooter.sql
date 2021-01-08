CREATE OR REPLACE PROCEDURE removeScooter(p_id IN SCOOTER.ID%TYPE) IS
BEGIN

    DELETE from SCOOTER where id = p_id;

end;