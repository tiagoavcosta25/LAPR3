CREATE OR REPLACE FUNCTION getDeliveryAddress(strEmail "User".EMAIL%type) RETURN ADDRESS%ROWTYPE IS
    address ADDRESS%ROWTYPE;
    address_not_found exception;
begin

    select ADDRESS.* into address
    from ADDRESS ad INNER JOIN "Order" ord on ad.ID = ord.ID /*CHANGE*/
                    INNER JOIN DELIVERY dv on dv.ORDERID = ord.ID
                    INNER JOIN DELIVERYRUN D on dv.DELIVERYRUNID = D.DELIVERYORDERID
                    INNER JOIN COURIER C2 on D.COURIERUSERID = C2.USERID
                    INNER JOIN "User" us ON C2.USERID = us.ID
    WHERE us.EMAIL = strEmail;

    IF address IS NULL THEN
        RAISE address_not_found;
    END IF;

    RETURN address;

EXCEPTION
    WHEN address_not_found THEN
        raise_application_error(-20025, 'Address Not Found!');
        RETURN NULL;
END;