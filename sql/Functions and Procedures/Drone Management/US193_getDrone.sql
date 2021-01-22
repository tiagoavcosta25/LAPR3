CREATE OR REPLACE FUNCTION getDrone(p_id VEHICLE.ID%TYPE) RETURN SYS_REFCURSOR
    IS
    v_cursor sys_refcursor;
    drone_not_found exception;
BEGIN
    open v_cursor for
        SELECT D.VEHICLEID, V.BATTERYPERC, VM.ID, VM.DESIGNATION, VM.POTENCY, VM.WEIGHT, VM.MAXPAYLOAD, VM.VEHICLETYPE, B.*, P.ID, P.NAME,
               P.EMAIL, A.*
        FROM DRONE D
                 INNER JOIN VEHICLE V ON V.ID = D.VEHICLEID
                 INNER JOIN VEHICLEMODEL VM ON V.MODELID = VM.ID
                 INNER JOIN BATTERY B ON VM.BATTERYID = B.ID
                 INNER JOIN PHARMACY P on V.PHARMACYID = P.ID
                 INNER JOIN ADDRESS A on A.LATITUDE = P.ADDRESSLATITUDE and A.LONGITUDE = P.ADDRESSLONGITUDE
        WHERE V.ID = p_id;


    if v_cursor is null then
        raise drone_not_found;
    end if;

    return v_cursor;

EXCEPTION
    when drone_not_found then
        raise_application_error(-20141, 'Drone Not Found!');
        return null;
end;