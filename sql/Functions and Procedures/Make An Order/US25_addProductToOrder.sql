create or replace procedure addProductToOrder(p_orderId "Order".ID%type, p_productId PRODUCT.ID%type, p_name PRODUCT.name%type,
                                    p_description PRODUCT.DESCRIPTION%type, p_unitaryPrice PRODUCT.UNITARYPRICE%type,
                                    p_unitaryWeight PRODUCT.UNITARYWEIGHT%type, p_quantity ORDERPRODUCT.QUANTITY%type)
    is
    v_checkOrderId CLIENT.USERID%type;
    v_checkProductId PRODUCT.ID%type;
    order_not_found exception;
    product_not_found exception;
begin

    select ID
    into v_checkOrderId
    from "Order"
    where ID = p_orderId;

    if v_checkOrderId is null then
        raise order_not_found;
    end if;

    select id
    into v_checkProductId
    from PRODUCT
    where ID = p_productId
      and NAME = p_name
      and DESCRIPTION = p_description
      and UNITARYPRICE = p_unitaryPrice
      and UNITARYWEIGHT = p_unitaryWeight;

    if v_checkProductId is null then
        raise product_not_found;
    end if;

-- Creates a new OrderProduct

    Insert into ORDERPRODUCT(ORDERID, PRODUCTID, QUANTITY)
    Values (p_orderId, p_productId, p_quantity);

EXCEPTION
    when order_not_found then
        raise_application_error(-20025, 'Order Not Found!');
        return null;

    when product_not_found then
        raise_application_error(-20025, 'Product Not Found!');
        return null;

end;