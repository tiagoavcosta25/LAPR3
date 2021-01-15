CREATE OR REPLACE FUNCTION getSuitableScooter(distance double precision, strEmail "User".EMAIL%type) RETURN SYS_REFCURSOR IS
    RFC SYS_REFCURSOR;
    scooter_not_found exception;
    scooterID SCOOTER.VEHICLEID%TYPE;
    scooterFinalBatteryEnergy Double Precision;
BEGIN

        WITH SCOOTERS AS(SELECT S.ID, ((B.BATTERYVOLTAGE * B.BATTERYCAPACITY) - ((S.POTENCY * (distance / 4.84f)) * 2)) "finalBatteryEnergy"
        FROM VEHICLE S INNER JOIN SCOOTER S2 on S.ID = S2.VEHICLEID
                       INNER JOIN BATTERY B on B.ID = S.BATTERYID
                       INNER JOIN PHARMACY P on P.ID = S.PHARMACYID
                       INNER JOIN ADDRESS A2 on A2.ID = P.ADDRESSID
                       INNER JOIN COURIER C3 on P.ID = C3.PHARMACYID
                       INNER JOIN "User" U on U.ID = C3.USERID
        WHERE U.EMAIL = strEmail
            GROUP BY S.ID, B.BATTERYPERC,S.POTENCY,S.WEIGHT,B.BATTERYCAPACITY,
                     S.CHARGINGSTATUS,S.MAXPAYLOAD,B.BATTERYVOLTAGE,P.ID,P.NAME,
                     A2.ID,A2.LONGITUDE,A2.LATITUDE,A2.LOCALITY,A2.STREETNAME,A2.DOORNUMBER,A2.POSTALCODE,A2.COUNTRY)

        SELECT SCOOTERS.ID, "finalBatteryEnergy" INTO scooterID,scooterFinalBatteryEnergy
        FROM SCOOTERS
        WHERE (SCOOTERS."finalBatteryEnergy") IN (SELECT MAX(SCOOTERS."finalBatteryEnergy")
                                                      FROM SCOOTERS) AND ROWNUM = 1
        ORDER BY SCOOTERS."finalBatteryEnergy" DESC;


        OPEN RFC FOR
            SELECT S.ID, B.BATTERYPERC,scooterFinalBatteryEnergy,
                   S.POTENCY,S.WEIGHT,B.BATTERYCAPACITY,S.CHARGINGSTATUS,S.MAXPAYLOAD,B.BATTERYVOLTAGE,P.ID,P.NAME,A2.*
            FROM VEHICLE S INNER JOIN BATTERY B on B.ID = S.BATTERYID
                           INNER JOIN PHARMACY P on P.ID = S.PHARMACYID
                           INNER JOIN ADDRESS A2 on A2.ID = P.ADDRESSID
        WHERE S.ID = scooterID;


        IF RFC IS NULL THEN
        RAISE scooter_not_found;
        END IF;

    RETURN RFC;

EXCEPTION
    WHEN scooter_not_found THEN
        raise_application_error(-20025, 'Scooter Not Found!');
        RETURN NULL;
END;
