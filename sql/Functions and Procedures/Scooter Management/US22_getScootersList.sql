create or replace function getScootersList(p_pharmacyId IN PHARMACY.ID%TYPE)
    return sys_refcursor is
    v_scooters sys_refcursor;
    scooter_not_found exception;
begin

    OPEN v_scooters FOR
        SELECT V.ID, V.BATTERYPERC, VM.DESIGNATION, VM.POTENCY, VM.WEIGHT, VM.MAXPAYLOAD, B.ID,
               B.BATTERYCAPACITY, B.BATTERYVOLTAGE, B.EFFICIENCY, P.ID, P.NAME, A.*
        FROM VEHICLE V
        INNER JOIN VEHICLEMODEL VM ON V.MODELID = VM.ID
        INNER JOIN BATTERY B ON VM.BATTERYID = B.ID
        INNER JOIN PHARMACY P on V.PHARMACYID = P.ID
        INNER JOIN ADDRESS A on A.LATITUDE = P.ADDRESSLATITUDE
        AND A.LONGITUDE = P.ADDRESSLONGITUDE
        WHERE V.PHARMACYID = p_pharmacyId;

    if v_scooters is null then
        raise scooter_not_found;
    end if;

    return v_scooters;

EXCEPTION
    when scooter_not_found then
        raise_application_error(-20812, 'Scooters Not Found!');
        return null;

end;