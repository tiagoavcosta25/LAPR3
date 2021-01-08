CREATE OR REPLACE FUNCTION getScooter(p_id PRODUCT.ID%TYPE) RETURN SYS_REFCURSOR
    IS
    rf_cur sys_refcursor;
BEGIN
    open rf_cur for
        select *
        from SCOOTER
        where id = p_id;

    return rf_cur;
end;