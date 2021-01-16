create or replace function getClientByEmail(p_email "User".EMAIL%type)
    return sys_refcursor is
    v_cursor sys_refcursor;
    client_not_found exception;
begin

    open v_cursor for
        select "User".*, CLIENT.CREDITS, ADDRESS.*, CC.*
        from CLIENT
                 inner join "User" on CLIENT.USERID = "User".ID
                 inner join ADDRESS on CLIENT.ADDRESSID = ADDRESS.ID
                 inner join CREDITCARDCLIENT C2 on CLIENT.USERID = C2.CLIENTID
                 inner join CREDITCARD CC on C2.CREDITCARDNR = CC.CREDITCARDNR
        where "User".EMAIL = p_email;


    if v_cursor is null then
        raise client_not_found;
    end if;

    return v_cursor;

EXCEPTION
    when client_not_found then
        raise_application_error(-20119, 'Client Not Found!');
        return null;

end;