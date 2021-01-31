CREATE OR REPLACE FUNCTION checkIfScooterAndCourierFromSamePh(p_scooterId IN PARKINGSLOT.VEHICLEID%TYPE,
 p_emailCourier IN "User".EMAIL%TYPE)RETURN NUMBER IS
    v_count number;
BEGIN

    SELECT COUNT(V.ID) INTO v_count
    FROM VEHICLE V
        INNER JOIN (
            SELECT C.PHARMACYID
            FROM COURIER C
                INNER JOIN(
                    SELECT U.ID
                    FROM "User" U
                    WHERE U.EMAIL = p_emailCourier)CU
                    ON CU.ID = C.USERID)VCU
            ON VCU.PHARMACYID = V.PHARMACYID
    WHERE V.ID = p_scooterId;

    RETURN v_count;

end;
