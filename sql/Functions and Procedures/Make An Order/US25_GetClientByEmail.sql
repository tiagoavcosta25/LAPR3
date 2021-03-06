create or replace function getClientByEmail(p_email "User".EMAIL%type)
    return sys_refcursor is
    v_cursor sys_refcursor;
    client_not_found exception;
begin

    open v_cursor for
        select "User".*, CLIENT.CREDITS, ADDRESS.*
        from CLIENT
                 inner join "User" on CLIENT.USERID = "User".ID
                 inner join ADDRESS on CLIENT.ADDRESSLONGITUDE = ADDRESS.LONGITUDE and CLIENT.ADDRESSLATITUDE = ADDRESS.LATITUDE
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