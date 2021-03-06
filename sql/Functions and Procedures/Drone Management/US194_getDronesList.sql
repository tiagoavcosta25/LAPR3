create or replace function getDronesList(p_pharmacyEmail IN PHARMACY.EMAIL%TYPE)
    return sys_refcursor is
    v_drones sys_refcursor;
    drone_not_found exception;
begin

    OPEN v_drones FOR
        SELECT D.VEHICLEID, V.BATTERYPERC, VM.ID, VM.DESIGNATION, VM.POTENCY, VM.WEIGHT, VM.MAXPAYLOAD, VM.VEHICLETYPE, B.*, P.ID, P.NAME,
               P.EMAIL, A.*
        FROM DRONE D
                 INNER JOIN VEHICLE V ON V.ID = D.VEHICLEID
                 INNER JOIN VEHICLEMODEL VM ON V.MODELID = VM.ID
                 INNER JOIN BATTERY B ON VM.BATTERYID = B.ID
                 INNER JOIN PHARMACY P on V.PHARMACYID = P.ID
                 INNER JOIN ADDRESS A on A.LATITUDE = P.ADDRESSLATITUDE and A.LONGITUDE = P.ADDRESSLONGITUDE
        WHERE P.EMAIL = p_pharmacyEmail;

    if v_drones is null then
        raise drone_not_found;
    end if;

    return v_drones;

EXCEPTION
    when drone_not_found then
        raise_application_error(-20812, 'Drones Not Found!');
        return null;

end;