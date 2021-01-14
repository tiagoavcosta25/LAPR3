CREATE OR REPLACE FUNCTION getScooter(p_id VEHICLE.ID%TYPE) RETURN SYS_REFCURSOR
    IS
    v_cursor sys_refcursor;
    scooter_not_found exception;
BEGIN
    open v_cursor for
        SELECT V.ID, V.POTENCY, V.WEIGHT, V.MAXPAYLOAD, V.CHARGINGSTATUS, B.ID, B.BATTERYPERC,
               B.BATTERYCAPACITY, B.BATTERYVOLTAGE, P.ID, P.NAME, A.*
        FROM VEHICLE V
        INNER JOIN BATTERY B ON V.BATTERYID = B.ID
        INNER JOIN PHARMACY P on V.PHARMACYID = P.ID
        INNER JOIN ADDRESS A on A.ID = P.ADDRESSID
        WHERE V.ID = p_id;


    if v_cursor is null then
        raise scooter_not_found;
    end if;

    return v_cursor;

EXCEPTION
    when scooter_not_found then
        raise_application_error(-20141, 'Scooter Not Found!');
        return null;
end;