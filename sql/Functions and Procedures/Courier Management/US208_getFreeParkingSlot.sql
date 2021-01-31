create or replace FUNCTION getFreeParkingSlot(intId IN SCOOTER.VEHICLEID%TYPE) RETURN INT
    IS
    rf_cur SYS_REFCURSOR;
    v_count int;
    park_id PARK.ID%TYPE;
    parkingSlot_id PARKINGSLOT.ID%TYPE := -1;
    battery_percentage VEHICLE.BATTERYPERC%TYPE;
    no_avaiable_parkingSlots EXCEPTION;
    scooter_already_parked EXCEPTION;
BEGIN

    SELECT COUNT(*) INTO  v_count
    FROM PARKINGSLOT PS
             INNER JOIN(
        SELECT V.ID
        FROM VEHICLE V
        WHERE V.ID = intId)PSV
                       ON PSV.ID = PS.VEHICLEID;

    IF v_count > 0 then
        raise scooter_already_parked;
    end if;

    SELECT BATTERYPERC INTO battery_percentage
    FROM VEHICLE WHERE ID = intId;

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

    IF parkingSlot_id = -1 THEN
        RAISE no_avaiable_parkingSlots;
    end if;

    RETURN parkingSlot_id;

EXCEPTION
    when no_avaiable_parkingSlots then
        raise_application_error(-20055, 'No Avaiable Parking Slots!');
        return null;
    when scooter_already_parked then
        raise_application_error(-20056, 'Scooter already parked!');
        return null;

end;