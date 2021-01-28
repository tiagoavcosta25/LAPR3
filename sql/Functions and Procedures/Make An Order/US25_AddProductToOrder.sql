create or replace procedure addProductToOrder(p_orderId "Order".ID%type, p_productId PRODUCT.ID%type,
 p_quantity ORDERPRODUCT.QUANTITY%type)
    is
    v_checkOrderId "Order".ID%type;
    v_checkProductId PRODUCT.ID%type;
    v_pharmacyId "Order".PHARMACYID%type;
    v_currentStock PHARMACYPRODUCT.STOCK%type;
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

    select PHARMACYID
    into v_pharmacyId
    from "Order"
    where ID = p_orderId;

    select id
    into v_checkProductId
    from PRODUCT
    where ID = p_productId;

    if v_checkProductId is null then
        raise product_not_found;
    end if;

    select stock
    into v_currentStock
    from PHARMACYPRODUCT
    where PHARMACYID = v_pharmacyId
    and PRODUCTID = p_productId;

    v_currentStock := v_currentStock - p_quantity;

    update PHARMACYPRODUCT
    set STOCK = v_currentStock
    where PHARMACYID = v_pharmacyId
    and PRODUCTID = p_productId;

-- Creates a new OrderProduct

    Insert into ORDERPRODUCT(ORDERID, PRODUCTID, QUANTITY)
    Values (p_orderId, p_productId, p_quantity);

EXCEPTION
    when order_not_found then
        raise_application_error(-20319, 'Order Not Found!');

    when product_not_found then
        raise_application_error(-20419, 'Product Not Found!');

end;