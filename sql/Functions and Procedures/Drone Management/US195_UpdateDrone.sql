create or replace PROCEDURE updateDrone(p_droneId IN VEHICLE.ID%TYPE, p_batteryPerc IN VEHICLE.BATTERYPERC%TYPE,
                                        p_designation IN VEHICLEMODEL.DESIGNATION%type, p_potency IN VEHICLEMODEL.POTENCY%TYPE,
                                        p_weight IN VEHICLEMODEL.WEIGHT%TYPE, p_maxPayload IN VEHICLEMODEL.MAXPAYLOAD%TYPE,
                                        p_batteryCapacity IN BATTERY.BATTERYCAPACITY%TYPE, p_batteryVoltage IN BATTERY.BATTERYVOLTAGE%TYPE,
                                        p_batteryEfficiency IN BATTERY.EFFICIENCY%TYPE)
IS
    v_batteryId BATTERY.ID%type;

BEGIN

    SELECT B.ID
    INTO v_batteryId
    FROM BATTERY B
        INNER JOIN VEHICLEMODEL VM on B.ID = VM.BATTERYID
        INNER JOIN VEHICLE V on VM.ID = V.MODELID
    WHERE V.ID = p_droneId;

    UPDATE BATTERY
    SET BATTERYCAPACITY = p_batteryCapacity,
        BATTERYVOLTAGE = p_batteryVoltage,
        EFFICIENCY = p_batteryEfficiency
    WHERE ID = v_batteryId;

    UPDATE VEHICLEMODEL
    SET DESIGNATION = p_designation,
        POTENCY = p_potency,
        WEIGHT = p_weight,
        MAXPAYLOAD = p_maxPayload
    WHERE ID = p_droneId;

    UPDATE VEHICLE
    SET BATTERYPERC = p_batteryPerc
    WHERE ID = p_droneId;
end;