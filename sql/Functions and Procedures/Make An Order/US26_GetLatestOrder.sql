create or replace function getLatestOrder(p_clientId "User".ID%type)
    return "Order"%rowtype is
    v_order "Order"%rowtype;
    v_orderId "Order".id%type;
    order_not_found exception;
begin

    select max(id)
    into v_orderId
    from "Order";

    select id, description, orderstatus, orderdate, totalweight, amount, additionalfee, ADDRESS.*
    into v_order
    from "Order"
    inner join ADDRESS on "Order".ADDRESSID = ADDRESS.ID
    where "Order".id = v_orderId
    and "Order".CLIENTID = p_clientId;

    if v_order is null then
        raise order_not_found;
    end if;

    return v_order;

EXCEPTION
    when order_not_found then
        raise_application_error(-20025, 'Order Not Found!');
        return null;

end;