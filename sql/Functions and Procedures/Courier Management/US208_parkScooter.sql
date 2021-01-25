CREATE OR REPLACE FUNCTION parkScooter(p_scooterId IN SCOOTER.VEHICLEID%TYPE) RETURN PARK.ID%TYPE IS
    v_parkingSlotId PARKINGSLOT.ID%TYPE;
    v_parkId PARK.ID%TYPE;
    no_avaiable_parkingSlots EXCEPTION;
BEGIN

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
end;
