CREATE OR REPLACE FUNCTION getDeliveryAddress(strEmail "User".EMAIL%type) RETURN ADDRESS%ROWTYPE IS
    address ADDRESS%ROWTYPE;
    address_not_found exception;
BEGIN

    SELECT AD.*, PD.*
    FROM ADDRESS AD INNER JOIN "Order" ord on ad.ID = ord.ADDRESSID
                    INNER JOIN ORDERPRODUCT OP on OP.ORDERID = ord.ID
                    INNER JOIN PRODUCT PD on PD.ID = OP.PRODUCTID
                    INNER JOIN DELIVERY dv on dv.ORDERID = ord.ID
                    INNER JOIN DELIVERYRUN D on dv.DELIVERYRUNID = D.ID
                    INNER JOIN COURIER C2 on D.COURIERID = C2.USERID
                    INNER JOIN "User" us ON C2.USERID = us.ID
    WHERE us.EMAIL = strEmail AND D.DELIVERYSTATUS = 'InProgress';

    IF address IS NULL THEN
        RAISE address_not_found;
    END IF;

    RETURN address;

EXCEPTION
    WHEN address_not_found THEN
        raise_application_error(-20025, 'Address Not Found!');
        RETURN NULL;
END;

