CREATE OR REPLACE FUNCTION getFreeChargingSlot(intParkID IN PARK.ID%TYPE) RETURN INT
    IS
    parkingSlot_id PARKINGSLOT.ID%TYPE;
BEGIN

    SELECT ps.ID INTO parkingSlot_id
    FROM PARKINGSLOT ps
    INNER JOIN(
        SELECT cs.PARKINGSLOTID
        FROM CHARGINGSLOT cs
        )pscs
    on pscs.PARKINGSLOTID = ps.ID
    WHERE ps.PARKID = intParkID AND ps.VEHICLEID IS NULL AND ROWNUM = 1;

    RETURN parkingSlot_id;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        return -1;
end;