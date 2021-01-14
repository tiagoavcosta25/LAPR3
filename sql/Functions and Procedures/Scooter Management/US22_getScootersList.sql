create or replace function getScootersList(p_pharmacyId IN PHARMACY.ID%TYPE)
    return sys_refcursor is
    v_scooters sys_refcursor;
    scooter_not_found exception;
begin

    OPEN v_scooters FOR
        SELECT V.ID, V.POTENCY, V.WEIGHT, V.MAXPAYLOAD, V.CHARGINGSTATUS, B.ID, B.BATTERYPERC,
               B.BATTERYCAPACITY, B.BATTERYVOLTAGE, P.ID, P.NAME, A.*
        FROM VEHICLE V
        INNER JOIN BATTERY B ON V.BATTERYID = B.ID
        INNER JOIN PHARMACY P on V.PHARMACYID = P.ID
        INNER JOIN ADDRESS A on A.ID = P.ADDRESSID
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