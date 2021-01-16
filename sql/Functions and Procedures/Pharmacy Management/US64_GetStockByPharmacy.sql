create or replace function getStockByPharmacy(p_pharmacyId IN PHARMACY.ID%type)
    return sys_refcursor is
    v_cursor sys_refcursor;
    product_not_found exception;
begin

    open v_cursor for
        select PP.STOCK, P.*
        from PRODUCT P
        inner join PHARMACYPRODUCT PP on P.ID = PP.PRODUCTID
    where PP.PHARMACYID = p_pharmacyId;

    if v_cursor is null then
        raise product_not_found;
    end if;

    return v_cursor;

EXCEPTION
    when product_not_found then
        raise_application_error(-20120, 'Products Not Found!');
        return null;
end;