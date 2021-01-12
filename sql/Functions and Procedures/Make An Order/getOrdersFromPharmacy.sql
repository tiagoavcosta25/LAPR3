create or replace function getOrdersFromPharmacy(p_pharmacyId Pharmacy.id%type)
    return sys_refcursor
    is
    v_cursor sys_refcursor;
begin

    open v_cursor for
        select o.id, o.AMOUNT,o.TOTALWEIGHT,o.ADDITIONALFEE,o.ORDERDATE,
               o.DESCRIPTION,o.ORDERSTATUS, c.CREDITS, a.ID,a.LATITUDE,a.LONGITUDE,a.STREETNAME,a.DOORNUMBER,
               a.POSTALCODE,a.LOCALITY,a.COUNTRY, cc.CREDITCARDNR,cc.VALIDITYDATE,cc.CCV,p.ID,p.NAME,, op.quantity,prod.*
        from "Order" o
            inner join Client c on c.userid = o.clientId
            inner join Address a on a.id = o.addressId
            inner join CREDITCARDCLIENT ccc on ccc.CLIENTID = c.USERID
            inner join CreditCard cc on cc.CREDITCARDNR = ccc.CREDITCARDNR
            inner join Pharmacy p on p.id = o.pharmacyId
            inner join OrderProduct op on op.orderId = o.id
            inner join Product prod on prod.id = op.productid
            where o.pharmacyId = p_pharmacyId;

    return v_cursor;


end;