create PROCEDURE updateScooter(p_id IN SCOOTER.ID%TYPE,p_batteryPerc IN SCOOTER.BATTERYPERC%TYPE, p_chargingStatus IN SCOOTER.CHARGINGSTATUS%TYPE, p_potency IN SCOOTER.POTENCY%TYPE, p_weight IN SCOOTER.WEIGHT%TYPE, p_batteryCpacity IN SCOOTER.BATTERYCAPACITY%TYPE) IS
BEGIN

    UPDATE SCOOTER
    SET BATTERYPERC = p_batteryPerc,
        CHARGINGSTATUS = p_chargingStatus,
        POTENCY = p_potency,
        WEIGHT = p_weight,
        BATTERYCAPACITY = p_batteryCpacity
    WHERE ID = p_id;

end;