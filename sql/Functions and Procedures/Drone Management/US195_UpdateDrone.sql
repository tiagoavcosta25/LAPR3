create or replace PROCEDURE updateDrone(p_percentage
    BATTERY.BATTERYPERC%type, p_pharmacyEmail PHARMACY.EMAIL%type,
                            p_potency VEHICLE.potency%type, p_weight VEHICLE.weight%type, p_batteryCapacity BATTERY.BATTERYCAPACITY%type,
                            p_maxPayload VEHICLE.MAXPAYLOAD%type, p_batteryVoltage BATTERY.BATTERYVOLTAGE%type,
                            p_ChargingStatus VEHICLE.chargingStatus%type, p_droneId VEHICLE.id%type
                            ) IS
    v_pharId PHARMACY.id%type;
    v_batteryId BATTERY.id%type;
BEGIN

    select id
    into v_pharId
    from PHARMACY
    where PHARMACY.EMAIL = p_pharmacyEmail;



    UPDATE VEHICLE
    SET PHARMACYID = v_pharId,POTENCY = p_potency,WEIGHT = p_weight
     ,MAXPAYLOAD = p_maxPayload, CHARGINGSTATUS = p_ChargingStatus
    WHERE ID = p_droneId;

    SELECT BATTERYID INTO v_batteryId
    from VEHICLE v
    where v.ID = p_droneId;


    UPDATE BATTERY
    SET BATTERYPERC = p_percentage, BATTERYCAPACITY = p_batteryCapacity, BATTERYVOLTAGE = p_batteryVoltage
    where BATTERY.ID = v_batteryId ;

end;