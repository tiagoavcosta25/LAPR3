CREATE OR REPLACE FUNCTION getAddressesByDeliveryRunId(paramDeliveryRunId DELIVERYRUN.ID%type) return sys_refcursor is
    cur sys_refcursor;
    tempId "Order".id%type;
    addresses_not_found exception;
BEGIN
    OPEN cur FOR
        SELECT distinct A.*
        FROM ADDRESS A
                INNER JOIN "Order" O ON A.ID = O.ADDRESSID
                INNER JOIN DELIVERY D ON D.ORDERID = O.ID
                INNER JOIN DELIVERYRUN Dr ON DR.ID = D.DELIVERYRUNID
        WHERE DR.ID = paramDeliveryRunId
    ;
    close cur;
    SELECT O.ID into tempId
        FROM ADDRESS A
        INNER JOIN "Order" O ON A.ID = O.ADDRESSID
        INNER JOIN DELIVERY D ON D.ORDERID = O.ID
        INNER JOIN DELIVERYRUN DR ON DR.ID = D.DELIVERYRUNID
        WHERE DR.ID = paramDeliveryRunId
        FETCH FIRST ROW ONLY
    ;

    open cur for
        SELECT * FROM ADDRESS A
                          INNER JOIN PHARMACY P ON A.ID = P.ADDRESSID
                          INNER JOIN "Order" O ON P.ID = O.PHARMACYID
        where O.ID =tempId;
    if cur is null then
        raise addresses_not_found;
    end if;
    return cur;
EXCEPTION
    when addresses_not_found then
        raise_application_error(-20085, 'Addresses Not Found!');
        return null;

END;