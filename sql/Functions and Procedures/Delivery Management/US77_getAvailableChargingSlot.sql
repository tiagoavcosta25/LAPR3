CREATE OR REPLACE FUNCTION getAvailableChargingSlot(strEmail "User".EMAIL%type) RETURN SYS_REFCURSOR IS
    RFC SYS_REFCURSOR;
    chargingSlot_not_found exception;
BEGIN

    OPEN RFC FOR
    SELECT CS.ID,PH.ID, PH.NAME, PH.ADDRESSID, AD.LATITUDE, AD.LONGITUDE, AD.DOORNUMBER,
           AD.STREETNAME, AD.POSTALCODE, AD.LOCALITY, AD.COUNTRY, P.MAXSLOTSNUMBER, CS.OUTPUTPOWER
    FROM CHARGINGSLOT CS INNER JOIN PARK P on P.PHARMACYID = CS.PARKPHARMACYID
                         INNER JOIN PHARMACY PH ON P.PHARMACYID = PH.ID
                         INNER JOIN ADDRESS AD ON PH.ADDRESSID = AD.ID
                         INNER JOIN COURIER C ON C.PHARMACYID = PH.ID
                         INNER JOIN "User" U on C.USERID = U.ID
    WHERE U.EMAIL = strEmail;

    IF RFC IS NULL THEN
        RAISE chargingSlot_not_found;
    END IF;

    RETURN RFC;

EXCEPTION
    WHEN chargingSlot_not_found THEN
        raise_application_error(-20025, 'Address Not Found!');
        RETURN NULL;
END;

