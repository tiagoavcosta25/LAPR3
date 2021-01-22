create or replace function getPharmaciesWithStock(p_orderId "Order".ID%type, p_productId PRODUCT.ID%type, p_quantity PHARMACYTRANSFER.QUANTITY%type)
    return sys_refcursor is
    v_cursor sys_refcursor;
begin

    open v_cursor for
        select PH.ID, PH.NAME, PH.EMAIL, A.*
        from PHARMACY PH
        inner join ADDRESS A on PH.ADDRESSLATITUDE = A.LATITUDE and PH.ADDRESSLONGITUDE = A.LONGITUDE
        inner join PHARMACYPRODUCT PP on PH.ID = PP.PHARMACYID
    and PP.PRODUCTID = p_productId
    and PP.STOCK >= p_quantity;

    return v_cursor;
end;