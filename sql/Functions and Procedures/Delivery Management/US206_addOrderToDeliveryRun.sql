create or replace procedure addNewDeliveryRun(p_deliveryRunId DELIVERYRUN.id%type,
                                              p_orderId "Order".id%type)
    is
begin

    update "Order"
    set "Order".run = p_deliveryRunId
    where "Order".ID = p_orderId;


end;