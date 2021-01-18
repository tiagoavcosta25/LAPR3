CREATE OR REPLACE FUNCTION getFreeParkingSlot(intId IN SCOOTER.VEHICLEID%TYPE) RETURN INT
    IS
    rf_cur SYS_REFCURSOR;
    park_id PARK.ID%TYPE;
    parkingSlot_id PARKINGSLOT.ID%TYPE := -1;
    battery_percentage BATTERY.BATTERYPERC%TYPE;

BEGIN

    SELECT b.BATTERYPERC INTO battery_percentage
    FROM BATTERY b
    INNER JOIN(
        SELECT v.BATTERYID
        FROM VEHICLE v
        WHERE v.ID = intId
        )bv
    on bv.BATTERYID = b.ID;

    OPEN rf_cur FOR
        SELECT p.ID
        FROM PARK p
                 inner join(
            SELECT v.PHARMACYID
            FROM VEHICLE v
            WHERE v.ID = intId) vp
                           on vp.PHARMACYID = p.PHARMACYID;

    LOOP
        FETCH rf_cur INTO park_id;
        EXIT WHEN rf_cur%NOTFOUND OR parkingSlot_id != -1;
        IF battery_percentage < 100 THEN
            parkingSlot_id := GETFREECHARGINGSLOT(park_id);
            IF parkingSlot_id = -1 THEN
                parkingSlot_id := GETFREENONCHARGINGSLOT(park_id);
            end if;
        ELSE
            parkingSlot_id := GETFREENONCHARGINGSLOT(park_id);
            IF parkingSlot_id = -1 THEN
                parkingSlot_id := GETFREECHARGINGSLOT(park_id);
            end if;
        end if;
    end loop;

    RETURN parkingSlot_id;

end;