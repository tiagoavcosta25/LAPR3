CREATE OR REPLACE FUNCTION getProduct(intId PRODUCT.ID%TYPE) RETURN SYS_REFCURSOR
    IS
    rf_cur sys_refcursor;
    product_not_found exception;
BEGIN
    open rf_cur for
        select *
        from PRODUCT
        where id = intId;

    if v_cursor is null then
        raise product_not_found;
    end if;

    return rf_cur;
EXCEPTION
    when product_not_found then
        raise_application_error(-20034, 'Product Not Found!');
        return null;
end;