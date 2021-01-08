create or replace function getPharmacyByManagerEmail(p_email "User".EMAIL%type) RETURN SYS_REFCURSOR is
    v_pharmacy SYS_REFCURSOR;
    order_not_found exception;
begin
    open v_pharmacy for
        SELECT PH.ID, PH.NAME, A2.*,U.*
        FROM PHARMACY PH INNER JOIN ADDRESS A2 on A2.ID = PH.ADDRESSID
                         INNER JOIN PHARMACYMANAGER P on P.USERID = PH.MANAGERID
                         INNER JOIN "User" U on U.ID = P.USERID
        where U.EMAIL = p_email;

    if v_pharmacy is null then
        raise order_not_found;
    end if;

    return v_pharmacy;

EXCEPTION
    when order_not_found then
        raise_application_error(-20020, 'Order Not Found!');
        return null;

end;