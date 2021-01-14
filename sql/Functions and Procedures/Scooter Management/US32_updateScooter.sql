create PROCEDURE updateScooter(p_id IN VEHICLE.ID%TYPE, p_potency IN VEHICLE.POTENCY%TYPE,
                               p_weight IN VEHICLE.WEIGHT%TYPE, p_maxPayload IN VEHICLE.MAXPAYLOAD%TYPE,
                               p_chargingStatus IN VEHICLE.CHARGINGSTATUS%TYPE, p_pharmacyID "PHARMACY".ID%type,
                               p_batteryPerc IN BATTERY.BATTERYPERC%TYPE, p_batteryCapacity IN BATTERY.BATTERYCAPACITY%TYPE,
                               p_batteryVoltage IN BATTERY.BATTERYVOLTAGE%TYPE)
    IS
    v_batteryId BATTERY.ID%type;

    BEGIN

    UPDATE VEHICLE
    SET CHARGINGSTATUS = p_chargingStatus,
        POTENCY = p_potency,
        WEIGHT = p_weight,
        MAXPAYLOAD = p_maxPayload,
        PHARMACYID = p_pharmacyID
    WHERE ID = p_id;

    SELECT BATTERYID
    INTO v_batteryId
    FROM VEHICLE
    WHERE ID = p_id;

    UPDATE BATTERY
    SET BATTERYPERC = p_batteryPerc,
        BATTERYCAPACITY = p_batteryCapacity,
        BATTERYVOLTAGE = p_batteryVoltage
    WHERE ID = v_batteryId;

end;