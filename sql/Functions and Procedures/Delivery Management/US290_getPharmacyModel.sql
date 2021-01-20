create or replace function getPharmacyModel(pharmacyEmail "PHARMACY".EMAIL%TYPE)
    return sys_refcursor is
    rfc sys_refcursor;
    no_models_exception exception;
begin

    OPEN RFC FOR
    SELECT VM.ID,VM.DESIGNATION,VM.POTENCY,VM.WEIGHT,VM.MAXPAYLOAD,B.ID,B.BATTERYCAPACITY,B.BATTERYVOLTAGE,B.EFFICIENCY, VM.VEHICLETYPE
    FROM VEHICLEMODEL VM INNER JOIN BATTERY B on B.ID = VM.BATTERYID
                         INNER JOIN VEHICLE V on VM.ID = V.MODELID
                         INNER JOIN PHARMACY P on P.ID = V.PHARMACYID
    WHERE P.EMAIL = pharmacyEmail AND V.BATTERYPERC > 0;


    if rfc is null then
        raise no_models_exception;
    end if;

    RETURN RFC;

EXCEPTION
    when no_models_exception then
        raise_application_error(-20812, 'There were no available models for this Pharmacy');
        return null;

end;