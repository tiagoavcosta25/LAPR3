CREATE OR REPLACE PROCEDURE parkScooter(intId IN SCOOTER.VEHICLEID%TYPE) IS
    id_parkingSlot PARKINGSLOT.ID%TYPE;
    no_avaiable_parkingSlots EXCEPTION;
BEGIN

    id_parkingSlot := GETFREEPARKINGSLOT(intId);

    IF id_parkingSlot = -1 THEN
        raise no_avaiable_parkingSlots;
    end if;

    UPDATE PARKINGSLOT ps
    SET VEHICLEID = intId
    WHERE ps.ID = id_parkingSlot;

EXCEPTION
    when no_avaiable_parkingSlots then
        raise_application_error(-20055, 'No Avaiable Parking Slots!');
end;
