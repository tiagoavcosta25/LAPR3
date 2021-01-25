CREATE OR REPLACE FUNCTION getEnergyByVehicleModel(p_modelId VEHICLEMODEL.ID%TYPE) RETURN double precision
    IS
    resultado double precision;
    battery_not_found exception;
BEGIN
    SELECT B.BATTERYCAPACITY*B.BATTERYVOLTAGE*(B.EFFICIENCY/100) "Energy" into resultado
    FROM BATTERY B
             INNER JOIN VEHICLEMODEL VM ON B.ID = VM.BATTERYID
    WHERE VM.ID = p_modelId;

    if resultado =0 then
        raise battery_not_found;
    end if;
    if resultado is null then
        raise battery_not_found;
    end if;

    return resultado;

EXCEPTION
    when battery_not_found then
        raise_application_error(-20185, 'Battery not found!');
        return null;
end;