CREATE OR REPLACE FUNCTION getFreeNonChargingSlot(intParkID IN PARK.ID%TYPE) RETURN INT
    IS
    parkingSlot_id PARKINGSLOT.ID%TYPE;
BEGIN

    SELECT ps.ID INTO parkingSlot_id
    FROM PARKINGSLOT ps
    INNER JOIN(
        SELECT ncs.PARKINGSLOTID
        FROM NONCHARGINGSLOT ncs
        )psncs
    on psncs.PARKINGSLOTID = ps.ID
    WHERE ps.PARKID = intParkID AND ps.VEHICLEID IS NULL AND ROWNUM = 1;

    RETURN parkingSlot_id;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        return -1;
end;