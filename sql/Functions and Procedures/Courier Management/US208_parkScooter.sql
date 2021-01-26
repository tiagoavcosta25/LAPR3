CREATE OR REPLACE FUNCTION parkScooter(p_scooterId IN SCOOTER.VEHICLEID%TYPE) RETURN PARK.ID%TYPE IS
    v_count int;
    v_parkingSlotId PARKINGSLOT.ID%TYPE;
    v_parkId PARK.ID%TYPE;
    no_avaiable_parkingSlots EXCEPTION;
    scooter_already_parked EXCEPTION;
BEGIN

    SELECT COUNT(*) INTO  v_count
    FROM PARKINGSLOT PS
             INNER JOIN(
        SELECT V.ID
        FROM VEHICLE V
        WHERE V.ID = p_scooterId)PSV
                       ON PSV.ID = PS.VEHICLEID;

    IF v_count > 0 then
        raise scooter_already_parked;
    end if;

    v_parkingSlotId := GETFREEPARKINGSLOT(p_scooterId);

    IF v_parkingSlotId = -1 THEN
        raise no_avaiable_parkingSlots;
    end if;

    UPDATE PARKINGSLOT ps
    SET VEHICLEID = p_scooterId
    WHERE ps.ID = v_parkingSlotId;

    SELECT P.ID
    INTO v_parkId
    FROM PARK P
             INNER JOIN PARKINGSLOT PS ON P.ID = PS.PARKID
    WHERE PS.ID = v_parkingSlotId;

    return v_parkId;

EXCEPTION
    when no_avaiable_parkingSlots then
        raise_application_error(-20055, 'No Avaiable Parking Slots!');
        return null;
    when scooter_already_parked then
        raise_application_error(-20056, 'Scooter already parked!');
        return null;
end;
