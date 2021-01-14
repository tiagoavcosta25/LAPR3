create or replace procedure addDrone(p_potency "VEHICLE".POTENCY%type, p_weight "VEHICLE".WEIGHT%type, p_maxPayload "VEHICLE".MAXPAYLOAD%type,
                                       p_chargingStatus "VEHICLE".CHARGINGSTATUS%type, p_batteryPerc "BATTERY".BATTERYPERC%type,
                                       p_batteryCapacity "BATTERY".BATTERYCAPACITY%type, p_batteryVoltage "BATTERY".BATTERYVOLTAGE%type,
                                       p_pharmacyID "PHARMACY".ID%type)
    is
    v_checkPharmacyId PHARMACY.ID%type;
    v_batteryId BATTERY.ID%type;
    v_vehicleId VEHICLE.ID%type;
    drone_not_found exception;
begin

    select ID
    into v_checkPharmacyId
    from PHARMACY
    where ID = p_pharmacyID;

    if v_checkPharmacyID is null then
        raise drone_not_found;
    end if;

-- Creates a new Battery
    INSERT INTO BATTERY(BATTERYPERC, BATTERYCAPACITY, BATTERYVOLTAGE)
    VALUES (p_batteryPerc, p_batteryCapacity, p_batteryVoltage);

    SELECT max(ID)
    INTO v_batteryId
    FROM BATTERY
    WHERE BATTERYPERC = p_batteryPerc
    AND BATTERYCAPACITY = p_batteryCapacity
    AND BATTERYVOLTAGE = p_batteryVoltage;

-- Creates a new Vehicle
    Insert into VEHICLE(BATTERYID,CHARGINGSTATUS,POTENCY,WEIGHT, MAXPAYLOAD, PHARMACYID)
    Values (v_batteryId, p_chargingStatus, p_potency, p_weight, p_maxPayload, p_pharmacyID);

    SELECT max(ID)
    INTO v_vehicleId
    FROM VEHICLE
    WHERE BATTERYID = v_batteryId
      AND CHARGINGSTATUS = p_chargingStatus
      AND POTENCY = p_potency
      AND WEIGHT = p_weight
      AND MAXPAYLOAD = p_maxPayload
      AND PHARMACYID = p_pharmacyID;

-- Creates a new Drone
    INSERT INTO DRONE(VEHICLEID)
    VALUES (v_vehicleId);

EXCEPTION
    when drone_not_found then
        raise_application_error(-20912, 'Drone Not Found!');

end;