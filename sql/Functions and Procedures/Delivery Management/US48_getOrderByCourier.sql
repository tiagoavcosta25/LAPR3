create or replace function getOrderByCourier(p_email "User".EMAIL%type)
    return "Order"%rowtype is
    v_order Order%rowtype;
    order_not_found exception;
begin

    select "Order".*, CLIENT.*, ADDRESS.*
    into v_order
    from "Order"
    inner join CLIENT on CLIENT.USERID = "Order".ID
    inner join ADDRESS on CLIENT.ADDRESSID = ADDRESS.ID
    inner join "User" on CLIENT.USERID = "User".ID
    where "User".EMAIL = p_email;

    if v_order is null then
        raise order_not_found;
    end if;

    return v_order;

EXCEPTION
    when order_not_found then
        raise_application_error(-20025, 'Client Not Found!');
        return null;

end;