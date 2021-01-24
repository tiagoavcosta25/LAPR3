create or replace function getPharmacies
    return sys_refcursor is
    v_cursor sys_refcursor;
    pharamcy_not_found exception;
begin

    open v_cursor for
        select P.ID, P.NAME, P.EMAIL, A.*
        from PHARMACY P
                 inner join ADDRESS A on P.ADDRESSLATITUDE = A.LATITUDE and P.ADDRESSLONGITUDE = P.ADDRESSLONGITUDE;

    if v_cursor is null then
        raise pharamcy_not_found;
    end if;

    return v_cursor;

EXCEPTION
    when pharamcy_not_found then
        raise_application_error(-20121, 'Pharmacy Not Found!');
        return null;
end;
/

