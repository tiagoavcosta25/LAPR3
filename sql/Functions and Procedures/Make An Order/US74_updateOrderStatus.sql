create or replace procedure updateOrderStatus(orderID "Order".ID%type)
    is

begin

    UPDATE "Order"
    SET ORDERSTATUS = 'Cancelled'
    WHERE ID = orderID;
end;