CREATE OR REPLACE FUNCTION getScooter(p_id VEHICLE.ID%TYPE) RETURN SYS_REFCURSOR
    IS
    v_cursor sys_refcursor;
    scooter_not_found exception;
BEGIN
    open v_cursor for
        SELECT S.VEHICLEID, V.BATTERYPERC, VM.ID, VM.DESIGNATION, VM.POTENCY, VM.WEIGHT, VM.MAXPAYLOAD, VM.VEHICLETYPE, B.*, P.ID, P.NAME,
               P.EMAIL, A.*
        FROM SCOOTER S
                 INNER JOIN VEHICLE V ON V.ID = S.VEHICLEID
                 INNER JOIN VEHICLEMODEL VM ON V.MODELID = VM.ID
                 INNER JOIN BATTERY B ON VM.BATTERYID = B.ID
                 INNER JOIN PHARMACY P on V.PHARMACYID = P.ID
                 INNER JOIN ADDRESS A on A.LATITUDE = P.ADDRESSLATITUDE and A.LONGITUDE = P.ADDRESSLONGITUDE
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