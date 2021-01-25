create or replace function getEmailPerChargingScooter(p_parkId IN PARK.ID%TYPE)
    return sys_refcursor is
    v_scooters sys_refcursor;
    scooter_not_found exception;
begin

    OPEN v_scooters FOR
        SELECT U.EMAIL, S.VEHICLEID, V.BATTERYPERC, VM.ID, VM.DESIGNATION, VM.POTENCY, VM.WEIGHT, VM.MAXPAYLOAD, VM.VEHICLETYPE, B.*, P.ID, P.NAME,
               P.EMAIL, A.*
        FROM SCOOTER S
                 INNER JOIN VEHICLE V ON V.ID = S.VEHICLEID
                 INNER JOIN VEHICLEMODEL VM ON V.MODELID = VM.ID
                 INNER JOIN BATTERY B ON VM.BATTERYID = B.ID
                 INNER JOIN PHARMACY P on V.PHARMACYID = P.ID
                 INNER JOIN ADDRESS A on A.LATITUDE = P.ADDRESSLATITUDE and A.LONGITUDE = P.ADDRESSLONGITUDE
                 INNER JOIN PARKINGSLOT PS ON V.ID = PS.VEHICLEID
                 INNER JOIN CHARGINGSLOT CS ON PS.ID = CS.PARKINGSLOTID
                 INNER JOIN PARK PK ON PS.PARKID = PK.ID
                 INNER JOIN DELIVERYRUN D on V.ID = D.VEHICLEID
                 INNER JOIN COURIER C on D.COURIERID = C.USERID
                 INNER JOIN "User" U on C.USERID = U.ID
        WHERE PK.ID = p_parkId
        AND D.ID = (
            SELECT MAX(ID)
            FROM DELIVERYRUN DR
            WHERE DR.VEHICLEID = V.ID);

    if v_scooters is null then
        raise scooter_not_found;
    end if;

    return v_scooters;

EXCEPTION
    when scooter_not_found then
        raise_application_error(-20812, 'Scooters Not Found!');
        return null;
end;