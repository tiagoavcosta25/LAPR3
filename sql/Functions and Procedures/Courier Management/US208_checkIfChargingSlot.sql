create or replace FUNCTION checkIfChargingSlot(intId IN PARKINGSLOT.ID%TYPE) RETURN NUMBER
    IS
    v_count number;
BEGIN

    SELECT COUNT(P.ID) INTO v_count
    FROM PARKINGSLOT P
    INNER JOIN(
        SELECT CS.PARKINGSLOTID
        FROM CHARGINGSLOT CS
        WHERE CS.PARKINGSLOTID = intId)PCS
    ON PCS.PARKINGSLOTID = P.ID;

    RETURN v_count;

end;