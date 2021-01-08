CREATE OR REPLACE FUNCTION getStartingAndDeliveryAddressByOrderId(orderId "Order".ID%type) return sys_refcursor is
    addresses sys_refcursor;
    order_not_found exception;
BEGIN
    OPEN addresses FOR
        SELECT A.*, A2.*
        FROM ADDRESS A
                 INNER JOIN "Order" O ON A.ID = O.ADDRESSID
                 INNER JOIN PHARMACY P ON O.PHARMACYID = P.ID
                 INNER JOIN ADDRESS A2 ON A2.ID = P.ADDRESSID
        WHERE O.ID = orderId;

    if addresses is null then
        raise order_not_found;
    end if;
    return addresses;
EXCEPTION
    when order_not_found then
        raise_application_error(-20080, 'Order Not Found!');
        return null;

END;