create or replace function getDronesList(p_pharmacyId IN PHARMACY.ID%TYPE)
    return sys_refcursor is
    v_drones sys_refcursor;
    drone_not_found exception;
begin

    OPEN v_drones FOR
        SELECT V.ID, V.POTENCY, V.WEIGHT, V.MAXPAYLOAD, V.CHARGINGSTATUS, B.ID, B.BATTERYPERC,
               B.BATTERYCAPACITY, B.BATTERYVOLTAGE, P.ID, P.NAME, A.*
        FROM VEHICLE V
        INNER JOIN BATTERY B ON V.BATTERYID = B.ID
        INNER JOIN PHARMACY P on V.PHARMACYID = P.ID
        INNER JOIN ADDRESS A on A.ID = P.ADDRESSID
        WHERE V.PHARMACYID = p_pharmacyId;

    if v_drones is null then
        raise drone_not_found;
    end if;

    return v_drones;

EXCEPTION
    when drone_not_found then
        raise_application_error(-20812, 'Drones Not Found!');
        return null;

end;