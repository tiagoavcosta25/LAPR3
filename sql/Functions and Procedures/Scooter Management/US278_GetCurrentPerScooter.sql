create or replace function getCurrentPerScooter(p_parkId PARK.ID%type)
    return number is
    v_checkParkId PARK.ID%type;
    v_numberOfScootersParked number;
    park_not_found exception;
begin

    select count(ID)
    into v_checkParkId
    from PHARMACY
    where ID = p_parkId;

    if v_checkParkId = 0 then
        raise park_not_found;
    end if;

    SELECT count(PS.VEHICLEID)
    INTO v_numberOfScootersParked
    FROM PARKINGSLOT PS
    INNER JOIN CHARGINGSLOT CS on PS.ID = CS.PARKINGSLOTID
    WHERE PS.VEHICLEID = 1;

    return 1;

EXCEPTION
    when park_not_found then
        raise_application_error(-20952, 'Park Not Found!');
        return null;

end;