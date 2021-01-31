create or replace function getCurrentPerCharger(p_parkSlotId PARK.ID%type)
    return number is
    v_parkId PARK.ID%TYPE;
    v_checkParkId int;
    v_numberOfScootersParked int;
    v_totalCurrent PARK.TOTALOUTPUTCURRENT%TYPE;
    v_currentPerCharger number;
    park_not_found exception;
begin

    SELECT PRK.ID
    INTO v_parkId
    FROM PARK PRK
             INNER JOIN PARKINGSLOT P on PRK.ID = P.PARKID
    WHERE P.ID = p_parkSlotId;

    IF v_checkParkId = 0 THEN
        raise park_not_found;
    END IF;

    SELECT TOTALOUTPUTCURRENT
    INTO v_totalCurrent
    FROM PARK P
    WHERE P.ID = v_parkId;

    SELECT count(PS.VEHICLEID)
    INTO v_numberOfScootersParked
    FROM PARKINGSLOT PS
             INNER JOIN CHARGINGSLOT CS on PS.ID = CS.PARKINGSLOTID
             INNER JOIN PARK P on PS.PARKID = P.ID
    WHERE P.ID = v_parkId
      AND PS.VEHICLEID IS NOT NULL;

    IF v_numberOfScootersParked = 0 then
        v_currentPerCharger := v_totalCurrent;
    ELSE
        v_currentPerCharger := v_totalCurrent / v_numberOfScootersParked;
    end if;

    return v_currentPerCharger;

EXCEPTION
    when park_not_found then
        raise_application_error(-20952, 'Park Not Found!');
        return null;

end;