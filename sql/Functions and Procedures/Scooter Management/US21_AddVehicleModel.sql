create or replace function addVehicleModel(p_designation VEHICLEMODEL.DESIGNATION%type, p_maxPayload VEHICLEMODEL.MAXPAYLOAD%type,
                        p_potency VEHICLEMODEL.POTENCY%type, p_weight VEHICLEMODEL.WEIGHT%type,
                        p_vehicleTypeDesignation VEHICLETYPE.DESIGNATION%type, p_batteryCapacity BATTERY.BATTERYCAPACITY%type,
                        p_batteryVoltage BATTERY.BATTERYVOLTAGE%type, p_efficiency BATTERY.EFFICIENCY%type)
    return VEHICLEMODEL.ID%type is
    v_vehicleModelId VEHICLEMODEL.ID%type;
    v_batteryId BATTERY.ID%type;
begin
-- Creates a new Battery

INSERT INTO BATTERY(BATTERYCAPACITY, BATTERYVOLTAGE, EFFICIENCY)
VALUES (p_batteryCapacity, p_batteryVoltage, p_efficiency);

SELECT max(ID)
INTO v_batteryId
FROM BATTERY
WHERE BATTERYCAPACITY = p_batteryCapacity
  AND BATTERYVOLTAGE = p_batteryVoltage
  AND EFFICIENCY = p_efficiency;

-- Creates a new VehicleModel
    Insert into VEHICLEMODEL(DESIGNATION, MAXPAYLOAD, POTENCY, WEIGHT, VEHICLETYPE, BATTERYID)
    Values (p_designation, p_maxPayload, p_potency, p_weight, p_vehicleTypeDesignation, v_batteryId);

    SELECT max(ID)
    INTO v_vehicleModelId
    FROM VEHICLEMODEL
    WHERE DESIGNATION = p_designation
      AND MAXPAYLOAD = p_maxPayload
      AND POTENCY = p_potency
      AND WEIGHT = p_weight
      AND VEHICLETYPE = p_vehicleTypeDesignation
      AND BATTERYID = v_batteryId;

    return v_vehicleModelId;

end;