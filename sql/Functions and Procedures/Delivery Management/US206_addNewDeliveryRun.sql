create or replace function addNewDeliveryRun(p_orderId "Order".id%type, p_courierId Courier.USERID%type,
                                                p_deliveryStatus DELIVERYSTATUS.designation%type) return int
    is
    v_deliveryRunId DeliveryRun.id%type;
    delivery_not_created exception;
begin

    insert into DELIVERYRUN
        Values(p_orderId,p_courierId,null,p_deliveryStatus,null)
        returning id into v_deliveryRunId;

        return v_deliveryRunId;

EXCEPTION
    when delivery_not_created then
        RAISE_APPLICATION_ERROR(-20129,'Delivery NOT created!');
        return 0;

end;