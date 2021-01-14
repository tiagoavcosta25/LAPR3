create or replace procedure addOrderToDeliveryRun(p_deliveryRunId DELIVERYRUN.id%type,
                                              p_orderId "Order".id%type)
    is
begin

    update "Order"
    set "Order".DELIVERYRUNID = p_deliveryRunId
    where "Order".ID = p_orderId;


end;