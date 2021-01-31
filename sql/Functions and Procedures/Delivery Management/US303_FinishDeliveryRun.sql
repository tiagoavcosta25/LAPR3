create or replace procedure finishDeliveryRun(p_id DELIVERYRUN.ID%TYPE, p_batteryPerc VEHICLE.BATTERYPERC%TYPE, p_email "User".EMAIL%TYPE)
    is
    v_vehicleId VEHICLE.ID%TYPE;
    v_check number;
    deliveryRun_not_found exception;
begin

    SELECT COUNT(DELIVERYRUN.ID)
    INTO v_check
    FROM DELIVERYRUN
    INNER JOIN COURIER C4 on DELIVERYRUN.COURIERID = C4.USERID
    INNER JOIN "User" U3 on C4.USERID = U3.ID
    WHERE U3.EMAIL = p_email
    AND DELIVERYRUN.ID = p_Id
    AND DELIVERYRUN.DELIVERYSTATUS LIKE 'InProgress';

    IF v_check <= 0 then
        raise deliveryRun_not_found;
    end if;

    UPDATE DELIVERYRUN
    SET DELIVERYSTATUS = 'Finished'
    WHERE ID = p_id;

    SELECT V.ID
    INTO v_vehicleId
    FROM VEHICLE V
    INNER JOIN DELIVERYRUN D ON V.ID = D.VEHICLEID
    WHERE D.ID = p_id;

    UPDATE VEHICLE
    SET BATTERYPERC = p_batteryPerc
    WHERE ID = v_vehicleId;

    UPDATE "Order"
    SET ORDERSTATUS = 'Delivered'
    WHERE DELIVERYRUNID = p_Id;

EXCEPTION
    when deliveryRun_not_found then
        raise_application_error(-20812, 'DeliveryRun Not Found!');

end;