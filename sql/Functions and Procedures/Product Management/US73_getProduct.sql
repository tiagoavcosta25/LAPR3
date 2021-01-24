CREATE OR REPLACE FUNCTION getProduct(strName PRODUCT.NAME%TYPE) RETURN SYS_REFCURSOR
    IS
    rf_cur sys_refcursor;
    product_not_found exception;
BEGIN
    open rf_cur for
        select *
        from PRODUCT
        where NAME = strName;

    if rf_cur is null then
        raise product_not_found;
    end if;

    return rf_cur;
EXCEPTION
    when product_not_found then
        raise_application_error(-20034, 'Product Not Found!');
        return null;
end;