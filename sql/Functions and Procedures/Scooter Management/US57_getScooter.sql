CREATE OR REPLACE FUNCTION getScooter(p_id VEHICLE.ID%TYPE) RETURN SYS_REFCURSOR
    IS
    rf_cur sys_refcursor;
BEGIN
    open rf_cur for
        select *
        from VEHICLE
        where id = p_id;

    return rf_cur;
end;