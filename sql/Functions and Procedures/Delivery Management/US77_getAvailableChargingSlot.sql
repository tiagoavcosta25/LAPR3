CREATE OR REPLACE FUNCTION getAvailableChargingSlot(strEmail "User".EMAIL%type, strVehicleType PARK.vehicleType%type) RETURN SYS_REFCURSOR IS
    RFC SYS_REFCURSOR;
    chargingSlot_not_found exception;
BEGIN

    OPEN RFC FOR
    SELECT CS.ID, CS.OUTPUTCURRENT
    FROM CHARGINGSLOT CS INNER JOIN PARK P on P.ID = CS.PARKID
                         INNER JOIN PHARMACY PH ON P.PHARMACYID = PH.ID
                         INNER JOIN ADDRESS AD ON PH.ADDRESSID = AD.ID
                         INNER JOIN COURIER C ON C.PHARMACYID = PH.ID
                         INNER JOIN "User" U on C.USERID = U.ID
    WHERE U.EMAIL = strEmail AND CS.VEHICLEID IS NULL AND P.VEHICLETYPE = strVehicleType AND ROWNUM = 1;

    IF RFC IS NULL THEN
        RAISE chargingSlot_not_found;
    END IF;

    RETURN RFC;

EXCEPTION
    WHEN chargingSlot_not_found THEN
        raise_application_error(-20025, 'Address Not Found!');
        RETURN NULL;
END;

