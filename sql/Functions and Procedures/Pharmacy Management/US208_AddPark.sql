CREATE OR REPLACE PROCEDURE addPark(strPharmacyEmail IN PHARMACY.EMAIL%TYPE, strVehicleType IN PARK.VEHICLETYPE%TYPE,
intMaxSlotsNumber IN PARK.MAXSLOTSNUMBER%TYPE, fltTotalOutputCurrent IN PARK.TOTALOUTPUTCURRENT%TYPE,
intChargingSlotNumber IN INT, intNonChargingSlotNumber IN INT) IS

    intPharmacyId PARK.PHARMACYID%TYPE;
    intIdPark PARK.ID%TYPE;
    intIdParkingSlot PARKINGSLOT.ID%TYPE;
    maxSlotsError EXCEPTION;

BEGIN

    IF intChargingSlotNumber + intNonChargingSlotNumber > intMaxSlotsNumber THEN
        raise maxSlotsError;
    end if;

    select ID
    into intPharmacyId
    from PHARMACY
    where EMAIL = strPharmacyEmail;

    INSERT INTO PARK(PHARMACYID, VEHICLETYPE, MAXSLOTSNUMBER, TOTALOUTPUTCURRENT)
    VALUES (intPharmacyId, strVehicleType, intMaxSlotsNumber, fltTotalOutputCurrent)
    RETURNING ID INTO intIdPark;

    FOR l_counter IN 1..intChargingSlotNumber LOOP
        INSERT INTO PARKINGSLOT(PARKID)
        VALUES (intIdPark)
        RETURNING ID INTO intIdParkingSlot;
        INSERT INTO CHARGINGSLOT(PARKINGSLOTID)
        VALUES(intIdParkingSlot);
    END LOOP;

    FOR l_counter IN 1..intNonChargingSlotNumber LOOP
            INSERT INTO PARKINGSLOT(PARKID)
            VALUES (intIdPark)
            RETURNING ID INTO intIdParkingSlot;
            INSERT INTO NONCHARGINGSLOT(PARKINGSLOTID)
            VALUES(intIdParkingSlot);
    END LOOP;

    EXCEPTION
    when maxSlotsError then
        raise_application_error(-20045, 'Max Slots Error!');

end;