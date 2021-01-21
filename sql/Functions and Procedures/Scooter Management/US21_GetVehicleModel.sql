CREATE OR REPLACE FUNCTION getVehicleModel(p_designation VEHICLEMODEL.DESIGNATION%TYPE) RETURN SYS_REFCURSOR
    IS
    v_cursor sys_refcursor;
    model_not_found exception;
BEGIN
    open v_cursor for
        SELECT VM.ID, VM.POTENCY, VM.WEIGHT, VM.MAXPAYLOAD, VM.VEHICLETYPE, B.ID,
               B.BATTERYCAPACITY, B.BATTERYVOLTAGE, B.EFFICIENCY
        FROM VEHICLEMODEL VM
        INNER JOIN BATTERY B ON VM.BATTERYID = B.ID
        WHERE VM.DESIGNATION = p_designation;


    if v_cursor is null then
        raise model_not_found;
    end if;

    return v_cursor;

EXCEPTION
    when model_not_found then
        raise_application_error(-20142, 'Model Not Found!');
        return null;
end;