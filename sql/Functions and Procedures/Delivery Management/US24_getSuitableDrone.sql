CREATE OR REPLACE FUNCTION getSuitableDrone(distance double precision, strEmail "User".EMAIL%type) RETURN SYS_REFCURSOR IS
    RFC SYS_REFCURSOR;
    drone_not_found exception;
    droneID drone.VEHICLEID%TYPE;
    droneFinalBatteryEnergy Double Precision;
BEGIN
                                                                               /*INMPLEMENTAT AQUI EQUACAO DRONE*/
        WITH DRONES AS(SELECT S.ID, ((B.BATTERYVOLTAGE * B.BATTERYCAPACITY) - ((S.POTENCY * (distance / 4.84f)) * 2)) "finalBatteryEnergy"
        FROM VEHICLE S INNER JOIN DRONE D on S.ID = D.VEHICLEID
                       INNER JOIN BATTERY B on B.ID = S.BATTERYID
                       INNER JOIN PHARMACY P on P.ID = S.PHARMACYID
                       INNER JOIN ADDRESS A2 on A2.ID = P.ADDRESSID
                       INNER JOIN COURIER C3 on P.ID = C3.PHARMACYID
                       INNER JOIN "User" U on U.ID = C3.USERID
        WHERE U.EMAIL = strEmail
            GROUP BY S.ID, B.BATTERYPERC,S.POTENCY,S.WEIGHT,B.BATTERYCAPACITY,
                     S.CHARGINGSTATUS,S.MAXPAYLOAD,B.BATTERYVOLTAGE,P.ID,P.NAME,
                     A2.ID,A2.LONGITUDE,A2.LATITUDE,A2.LOCALITY,A2.STREETNAME,A2.DOORNUMBER,A2.POSTALCODE,A2.COUNTRY)

        SELECT DRONES.ID, "finalBatteryEnergy" INTO droneID,droneFinalBatteryEnergy
        FROM DRONES
        WHERE (DRONES."finalBatteryEnergy") IN (SELECT MAX(DRONES."finalBatteryEnergy")
                                                      FROM DRONES) AND ROWNUM = 1
        ORDER BY DRONES."finalBatteryEnergy" DESC;


        OPEN RFC FOR
            SELECT S.ID, B.BATTERYPERC,droneFinalBatteryEnergy,
                   S.POTENCY,S.WEIGHT,B.BATTERYCAPACITY,S.CHARGINGSTATUS,S.MAXPAYLOAD,B.BATTERYVOLTAGE
            FROM VEHICLE S INNER JOIN BATTERY B on B.ID = S.BATTERYID
                           INNER JOIN PHARMACY P on P.ID = S.PHARMACYID
                           INNER JOIN ADDRESS A2 on A2.ID = P.ADDRESSID
        WHERE S.ID = droneID;


        IF RFC IS NULL THEN
        RAISE drone_not_found;
        END IF;

    RETURN RFC;

EXCEPTION
    WHEN drone_not_found THEN
        raise_application_error(-20025, 'drone Not Found!');
        RETURN NULL;
END;
