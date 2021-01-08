CREATE OR REPLACE PROCEDURE removeProductPharmacy(orderId "Order".id%type) is
    productID number%type;
    v_checkOrderId number%type;
    v_checkProductId number%type;
    order_not_found exception;
    product_not_found exception;
BEGIN

    SELECT COUNT("Order".ID) INTO v_checkOrderId
    FROM "Order"
    WHERE "Order".ID = orderId;

    IF v_checkOrderId IS NULL THEN
        RAISE order_not_found;
    END IF;

    SELECT COUNT(PHARMACYPRODUCT.PRODUCTID) INTO v_checkProductId
    FROM PHARMACYPRODUCT INNER JOIN PHARMACY P on P.ID = PHARMACYPRODUCT.PHARMACYID
                         INNER JOIN "Order" O on P.ID = O.PHARMACYID
    WHERE O.ID = orderId;

    IF v_checkProductId IS NULL THEN
        RAISE product_not_found;
    END IF;

-- Removes the PharmacyProduct

    SELECT PHARMACYPRODUCT.PRODUCTID INTO productID
    FROM PHARMACYPRODUCT INNER JOIN PHARMACY P on P.ID = PHARMACYPRODUCT.PHARMACYID
                         INNER JOIN "Order" O on P.ID = O.PHARMACYID
    WHERE O.ID = orderId;

    DELETE
    FROM PHARMACYPRODUCT
    WHERE PHARMACYPRODUCT.PRODUCTID = productID;


EXCEPTION
    when order_not_found then
        raise_application_error(-20025, 'Order Not Found!');
        return null;

    when product_not_found then
        raise_application_error(-20025, 'Product Not Found!');
        return null;

END;