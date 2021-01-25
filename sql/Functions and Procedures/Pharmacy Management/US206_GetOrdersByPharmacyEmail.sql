create or replace function getOrdersByPharmacyEmail(p_pharEmail IN PHARMACY.EMAIL%type)
    return sys_refcursor is
    v_cursor sys_refcursor;
begin

    open v_cursor for
            select O.ID, O.DESCRIPTION, O.ORDERSTATUS, O.ORDERDATE, O.TOTALWEIGHT, O.AMOUNT, O.ADDITIONALFEE, O.ISHOMEDELIVERY, U.*,
                   C.CREDITS, A1.*, P.ID, P.NAME, P.EMAIL, A2.*
            from "Order" o
                     inner join CLIENT C on O.CLIENTID = C.USERID
                     inner join "User" U on C.USERID = U.ID
                     inner join ADDRESS A1 on A1.LONGITUDE = C.ADDRESSLONGITUDE and A1.LATITUDE = C.ADDRESSLATITUDE
                     inner join PHARMACY P on O.PHARMACYID = P.ID
                     inner join ADDRESS A2 on A2.LONGITUDE = P.ADDRESSLONGITUDE and A2.LATITUDE = P.ADDRESSLATITUDE
            where P.EMAIL = p_pharEmail;



    return v_cursor;

EXCEPTION
    when no_data_found then
        return null;
end;