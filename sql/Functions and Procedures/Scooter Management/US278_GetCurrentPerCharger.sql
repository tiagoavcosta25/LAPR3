create or replace function getCurrentPerCharger(p_parkId PARK.ID%type)
    return number is
    v_checkParkId PARK.ID%type;
    v_numberOfScootersParked number;
    v_totalCurrent number;
    v_currentPerCharger number;
    park_not_found exception;
begin

    SELECT count(ID)
    INTO v_checkParkId
    FROM PHARMACY
    WHERE ID = p_parkId;

    IF v_checkParkId = 0 THEN
        raise park_not_found;
    END IF;

    SELECT TOTALOUTPUTCURRENT
    INTO v_totalCurrent
    FROM PARK P
    WHERE P.ID = p_parkId;

    SELECT count(PS.VEHICLEID)
    INTO v_numberOfScootersParked
    FROM PARKINGSLOT PS
    INNER JOIN CHARGINGSLOT CS on PS.ID = CS.PARKINGSLOTID
    INNER JOIN PARK P on PS.PARKID = P.ID
    WHERE P.ID = p_parkId
    AND PS.VEHICLEID != null;

    v_currentPerCharger := v_totalCurrent / v_numberOfScootersParked;

    return v_currentPerCharger;

EXCEPTION
    when park_not_found then
        raise_application_error(-20952, 'Park Not Found!');
        return null;

end;