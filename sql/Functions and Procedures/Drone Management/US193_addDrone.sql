create or replace function addDrone(p_batteryPerc VEHICLE.BATTERYPERC%type, p_modelId VEHICLE.MODELID%type,
                                      p_pharmacyID "PHARMACY".ID%type)
    return VEHICLE.ID%type is
    v_checkPharmacyId PHARMACY.ID%type;
    v_checkModelId VEHICLEMODEL.ID%type;
    v_vehicleId VEHICLE.ID%type;
    model_not_found exception;
    pharmacy_not_found exception;
begin

    select ID
    into v_checkPharmacyId
    from PHARMACY
    where ID = p_pharmacyID;

    if v_checkPharmacyID is null then
        raise pharmacy_not_found;
    end if;

    select ID
    into v_checkModelId
    from VEHICLEMODEL
    where ID = p_modelID;

    if v_checkModelId is null then
        raise model_not_found;
    end if;

-- Creates a new Vehicle
    Insert into VEHICLE(BATTERYPERC, MODELID, PHARMACYID)
    Values (p_batteryPerc, p_modelId, p_pharmacyID);

    SELECT max(ID)
    INTO v_vehicleId
    FROM VEHICLE
    WHERE BATTERYPERC = p_batteryPerc
      AND MODELID = p_modelId
      AND PHARMACYID = p_pharmacyID;

-- Creates a new Drone
    INSERT INTO DRONE(VEHICLEID)
    VALUES (v_vehicleId);

    return v_vehicleId;

EXCEPTION
    when pharmacy_not_found then
        raise_application_error(-20952, 'Pharmacy Not Found!');
        return null;
    when model_not_found then
        raise_application_error(-20953, 'Model Not Found!');
        return null;

end;