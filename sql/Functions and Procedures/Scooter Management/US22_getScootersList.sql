create or replace function getScootersList(p_pharmacyEmail IN PHARMACY.EMAIL%TYPE)
    return sys_refcursor is
    v_scooters sys_refcursor;
    scooter_not_found exception;
begin

    OPEN v_scooters FOR
        SELECT S.VEHICLEID, V.BATTERYPERC, VM.ID, VM.DESIGNATION, VM.POTENCY, VM.WEIGHT, VM.MAXPAYLOAD, VM.VEHICLETYPE, B.*, P.ID, P.NAME,
               P.EMAIL, A.*
        FROM SCOOTER S
                 INNER JOIN VEHICLE V ON V.ID = S.VEHICLEID
                 INNER JOIN VEHICLEMODEL VM ON V.MODELID = VM.ID
                 INNER JOIN BATTERY B ON VM.BATTERYID = B.ID
                 INNER JOIN PHARMACY P on V.PHARMACYID = P.ID
                 INNER JOIN ADDRESS A on A.LATITUDE = P.ADDRESSLATITUDE and A.LONGITUDE = P.ADDRESSLONGITUDE
        WHERE P.EMAIL = p_pharmacyEmail;

    if v_scooters is null then
        raise scooter_not_found;
    end if;

    return v_scooters;

EXCEPTION
    when scooter_not_found then
        raise_application_error(-20812, 'Scooters Not Found!');
        return null;

end;