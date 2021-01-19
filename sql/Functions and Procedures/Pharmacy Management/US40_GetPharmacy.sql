create or replace function getPharmacy(p_email IN PHARMACY.EMAIL%type)
    return sys_refcursor is
    v_cursor sys_refcursor;
    pharmacy_not_found exception;
begin

    open v_cursor for
        select p.ID, p.NAME, p.EMAIL, a.*
        from PHARMACY p
        inner join ADDRESS a on a.ID = p.ADDRESSID
        where p.EMAIL = p_email;


    if v_cursor is null then
        raise pharmacy_not_found;
    end if;

    return v_cursor;

EXCEPTION
    when pharmacy_not_found then
        raise_application_error(-20141, 'Pharmacy Not Found!');
        return null;

end;