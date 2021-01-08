CREATE OR REPLACE FUNCTION getProduct(intId PRODUCT.ID%TYPE) RETURN SYS_REFCURSOR
    IS
    rf_cur sys_refcursor;
BEGIN
    open rf_cur for
        select *
        from PRODUCT
        where id = intId;

    return rf_cur;
end;