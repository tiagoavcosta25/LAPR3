select *
from DELIVERYRUN;

select *
from vehicle;

select *
from VEHICLEMODEL;

select *
from courier;

select *
from "User";


create or replace function getMostChargedScooter(p_vehicleModelDesignation VehicleModel.DESIGNATION%type) return sys_refcursor
    is
    v_curs sys_refcursor;
begin

    open v_curs for
        select v.ID,v.BATTERYPERC, vm.id, vm.designation, vm.potency, vm.weight, vm.maxpayload, V3.DESIGNATION,
               b.ID,b.EFFICIENCY, b.BATTERYCAPACITY,b.BATTERYVOLTAGE,phm.ID,phm.NAME,phm.EMAIL, a.LATITUDE,
               a.LONGITUDE, a.ALTITUDE, a.DOORNUMBER, a.STREETNAME, a.POSTALCODE, a.LOCALITY, a.COUNTRY
        from Vehicle v
                 inner join VehicleModel vm on v.MODELID = vm.ID
                 inner join PHARMACY phm on phm.ID = v.PHARMACYID
                 inner join ADDRESS a on a.LATITUDE = phm.ADDRESSLATITUDE and a.LONGITUDE = phm.ADDRESSLONGITUDE
                 inner join PARK p on p.PHARMACYID = phm.ID and p.VEHICLETYPE = vm.VEHICLETYPE
                 inner join CHARGINGSLOT cs on cs.PARKINGSLOTID = p.ID
                 inner join VEHICLETYPE V3 on p.VEHICLETYPE = V3.DESIGNATION
                 inner join BATTERY B on vm.BATTERYID = B.ID
        where vm.DESIGNATION = p_vehicleModelDesignation and v.BATTERYPERC = (select Max(v1.BATTERYPERC)
                                                                              from VEHICLE v1
                                                                                       inner join VehicleModel v2 on v1.MODELID = v2.ID
                                                                              where v2.DESIGNATION = p_vehicleModelDesignation
                                                                                and v2.VEHICLETYPE = 'Scooter')
            FETCH FIRST ROW ONLY;


    return v_curs;

EXCEPTION
    when no_data_found then
        return null;
end;