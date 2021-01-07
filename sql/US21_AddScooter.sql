create or replace procedure addScooter(p_batteryPerc "SCOOTER".BATTERYPERC%type, p_chargingStatus "SCOOTER".CHARGINGSTATUS%type,
                                     p_potency "SCOOTER".POTENCY%type, p_weight "SCOOTER".WEIGHT%type,
                                     p_batteryCapacity "SCOOTER".BATTERYCAPACITY%type, p_pharmacyID "PHARMACY"%type,
                                     p_name "PHARMACY".NAME%type, p_latitude ADDRESS.LATITUDE%type, p_longitude ADDRESS.LONGITUDE%type,
                                     p_streetName ADDRESS.STREETNAME%type, p_doorNumber ADDRESS.DOORNUMBER%type,
                                     p_postalCode ADDRESS.POSTALCODE%type, p_locality ADDRESS.LOCALITY%type, p_country ADDRESS.COUNTRY%type)
    is
    v_checkPharmacyId PHARMACY%type;
    scooter_not_found exception;
begin

    select ID
    into v_checkPharmacyId
    from PHARMACY
    where ID = p_pharmacyID;

    if v_checkPharmacyID is null then
        raise scooter_not_found;
    end if;

-- Creates a new Scooter
    Insert into SCOOTER(BATTERYPERC,CHARGINGSTATUS,POTENCY,WEIGHT,BATTERYCAPACITY, PHARMACYID)
    Values (p_batteryPerc, p_chargingStatus, p_potency, p_weight, p_batteryCapacity, p_pharmacyID);

EXCEPTION
    when scooter_not_found then
        raise_application_error(-20025, 'Scooter Not Found!');

end;