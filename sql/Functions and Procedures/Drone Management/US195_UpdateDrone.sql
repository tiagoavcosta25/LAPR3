create or replace PROCEDURE updateDrone(p_percentage
    Drone.BATTERYPERC%type, p_pharmacyId Drone.pharmacyId%type,
                            p_potency Drone.potency%type, p_weight Drone.weight%type, p_batteryCapacity Drone.BATTERYCAPACITY%type,
                            p_maxPayload Drone.MAXPAYLOAD%type, p_batteryVoltage Drone.BATTERYVOLTAGE%type,
                            p_ChargingStatus Drone.chargingStatus%type, p_droneId Drone.id%type
                            ) IS
BEGIN

    UPDATE DRONE
    SET BATTERYPERC = p_percentage, PHARMACYID = p_pharmacyId,POTENCY = p_potency,WEIGHT = p_weight
      ,BATTERYCAPACITY = p_batteryCapacity,MAXPAYLOAD = p_maxPayload,BATTERYVOLTAGE = p_batteryVoltage,
        CHARGINGSTATUS = p_ChargingStatus
    WHERE ID = p_droneId;

end;