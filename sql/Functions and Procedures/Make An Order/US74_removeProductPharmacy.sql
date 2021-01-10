CREATE OR REPLACE PROCEDURE removeProductPharmacy(orderId "Order".id%type) is
    RFC sys_refcursor;
    productID PRODUCT.ID%type;
    v_checkOrderId int;
    v_checkProductId int;
    order_not_found exception;
    product_not_found exception;
BEGIN

    SELECT COUNT("Order".ID) INTO v_checkOrderId
    FROM "Order"
    WHERE "Order".ID = orderId;

    IF v_checkOrderId = 0 THEN
        RAISE order_not_found;
    END IF;

    SELECT COUNT(PHARMACYPRODUCT.PRODUCTID) INTO v_checkProductId
    FROM PHARMACYPRODUCT INNER JOIN PRODUCT P on P.ID = PHARMACYPRODUCT.PRODUCTID
                         INNER JOIN ORDERPRODUCT O3 on P.ID = O3.PRODUCTID
                         INNER JOIN "Order" O4 on O4.ID = O3.ORDERID
    WHERE O4.ID = orderId AND PHARMACYPRODUCT.STOCK = 0;

    IF v_checkProductId = 0 THEN
        RAISE product_not_found;
    END IF;

-- Removes the PharmacyProduct

    OPEN RFC FOR
    SELECT PHARMACYPRODUCT.PRODUCTID
    FROM PHARMACYPRODUCT INNER JOIN PRODUCT P on P.ID = PHARMACYPRODUCT.PRODUCTID
                         INNER JOIN ORDERPRODUCT O3 on P.ID = O3.PRODUCTID
                         INNER JOIN "Order" O4 on O4.ID = O3.ORDERID
    WHERE O4.ID = orderId AND PHARMACYPRODUCT.STOCK = 0;

   LOOP
    FETCH rfc INTO productID;
    EXIT WHEN rfc%NOTFOUND;

    DELETE
    FROM PHARMACYPRODUCT
    WHERE PHARMACYPRODUCT.PRODUCTID = productID;
END LOOP;

EXCEPTION
    when order_not_found then
        raise_application_error(-20025, 'Order Not Found!');

    when product_not_found then
        raise_application_error(-20026, 'The product has stock');

END;