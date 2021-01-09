create or replace function getLatestOrder(p_email "User".EMAIL%type)
    return sys_refcursor is
    v_cursor sys_refcursor;
    v_orderId "Order".id%type;
    order_not_found exception;
begin

    select max("Order".ID)
    into v_orderId
    from "Order"
             inner join "User" on "Order".CLIENTID = "User".ID
    where "User".EMAIL = p_email;

    if v_orderId is null then
        raise order_not_found;
    end if;

    open v_cursor for
        select "Order".ID, "Order".DESCRIPTION, "Order".ORDERSTATUS, "Order".ORDERDATE, "Order".TOTALWEIGHT, "Order".AMOUNT, "Order".ADDITIONALFEE, ADDRESS.*
        from "Order"
                 inner join ADDRESS on "Order".ADDRESSID = ADDRESS.ID
        where "Order".id = v_orderId;

    if v_cursor is null then
        raise order_not_found;
    end if;

    return v_cursor;

EXCEPTION
    when order_not_found then
        raise_application_error(-20819, 'Order Not Found!');
        return null;

end;