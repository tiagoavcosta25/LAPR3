create or replace function getMostChargedDrone(p_vehicleModelDesignation VehicleModel.DESIGNATION%type) return int
    is
    v_curs sys_refcursor;
begin

    open v_curs for
        select v.*
        from Vehicle v
                 inner join VehicleModel vm on v.MODELID = vm.ID
                 inner join PHARMACY phm on phm.ID = v.PHARMACYID
                 inner join PARK p on p.PHARMACYID = phm.ID and p.VEHICLETYPE = vm.VEHICLETYPE
                 inner join CHARGINGSLOT cs on cs.PARKINGSLOTID = p.ID
        where v.BATTERYPERC = (select Max(v1.BATTERYPERC)
                               from VEHICLE v1
                                        inner join VehicleModel v2 on v1.MODELID = v2.ID
                               where v2.DESIGNATION = p_vehicleModelDesignation
                                 and v2.VEHICLETYPE = 'Drone');


    return v_curs;

end;
