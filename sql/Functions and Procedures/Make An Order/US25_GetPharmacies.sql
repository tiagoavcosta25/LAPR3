create or replace function getPharmacies
    return sys_refcursor is
    v_cursor sys_refcursor;
    pharamcy_not_found exception;
begin

    open v_cursor for
        select P.ID, P.NAME, U.*, A.*
        from PHARMACY P
            inner join ADDRESS A on P.ADDRESSID = A.ID
            inner join PHARMACYMANAGER PM on P.MANAGERID = PM.USERID
            inner join "User" U on PM.USERID = U.ID;

    if v_cursor is null then
        raise pharamcy_not_found;
    end if;

    return v_cursor;

EXCEPTION
    when pharamcy_not_found then
        raise_application_error(-20121, 'Pharmacy Not Found!');
        return null;
end;