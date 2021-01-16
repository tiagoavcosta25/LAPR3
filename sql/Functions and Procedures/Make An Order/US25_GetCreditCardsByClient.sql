create or replace function getCreditCardsByClient(p_clientEmail "User".EMAIL%type)
    return sys_refcursor is
    v_cursor sys_refcursor;
    cc_not_found exception;
begin

    open v_cursor for
        select CC.*
        from CREDITCARD CC
                 inner join CREDITCARDCLIENT CCC on CCC.CREDITCARDNR = CC.CREDITCARDNR
                 inner join CLIENT C on C.USERID = CCC.CLIENTID
                 inner join "User" U on C.USERID = U.ID
        where U.EMAIL = p_clientEmail;

    if v_cursor is null then
        raise cc_not_found;
    end if;

    return v_cursor;

EXCEPTION
    when cc_not_found then
        raise_application_error(-20120, 'Credit Cards Not Found!');
        return null;
end;