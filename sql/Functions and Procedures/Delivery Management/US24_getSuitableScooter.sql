CREATE OR REPLACE FUNCTION getSuitableScooter(distance double precision, strEmail "User".EMAIL%type) RETURN SYS_REFCURSOR IS
    RFC SYS_REFCURSOR;
    scooter_not_found exception;
BEGIN

    OPEN RFC FOR
        SELECT S.ID, P.ID,P.NAME,S.BATTERYPERC,S.POTENCY,S.WEIGHT,S.BATTERYCAPACITY,S.CHARGINGSTATUS,S.MAXPAYLOAD,
               A2.*, (S.POTENCY * (distance / 4.84f)) "deliverEnergy"
        FROM SCOOTER S INNER JOIN PHARMACY P on P.ID = S.PHARMACYID
                       INNER JOIN ADDRESS A2 on A2.ID = P.ADDRESSID
                       INNER JOIN CHARGINGSLOT C2 on S.ID = C2.SCOOTERID
                       INNER JOIN PARKINGSLOT P2 on S.ID = P2.SCOOTERID
                       INNER JOIN COURIER C3 on P.ID = C3.PHARMACYID
                       INNER JOIN "User" U on U.ID = C3.USERID
        WHERE U.EMAIL = strEmail;

    IF RFC IS NULL THEN
        RAISE scooter_not_found;
    END IF;

    RETURN RFC;

EXCEPTION
    WHEN scooter_not_found THEN
        raise_application_error(-20025, 'Scooter Not Found!');
        RETURN NULL;
END;

