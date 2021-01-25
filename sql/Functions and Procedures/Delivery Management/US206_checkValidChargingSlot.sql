create or replace function checkValidChargingSlot(p_latitude Address.LATITUDE%type,
                                                  p_longitude Address.LONGITUDE%type) return boolean
    is
    v_slotId CHARGINGSLOT.PARKINGSLOTID%type;
begin


    select cs.PARKINGSLOTID
    into v_slotId
    from CHARGINGSLOT cs
             inner join PARKINGSLOT ps on ps.ID = cs.PARKINGSLOTID
             inner join PARK p on p.ID = ps.PARKID
             inner join PHARMACY phar on phar.ID = p.PHARMACYID
    where phar.ADDRESSLATITUDE = p_latitude
    and phar.ADDRESSLONGITUDE = p_longitude
    and ps.VEHICLEID is NULL;

    return true;

EXCEPTION
    when no_data_found then
        return false;
end;