create or replace function getClientByEmail(p_email "User".EMAIL%type)
    return "Order"%rowtype is
    v_client Client%rowtype;
    client_not_found exception;
begin

    select "User".*, CLIENT.CREDITS, ADDRESS.*
    into v_client
    from CLIENT
    inner join "User" on CLIENT.USERID = "User".ID
    inner join ADDRESS on CLIENT.ADDRESSID = ADDRESS.ID
    where "User".EMAIL = p_email;

    if v_client is null then
        raise client_not_found;
    end if;

    return v_client;

EXCEPTION
    when client_not_found then
        raise_application_error(-20025, 'Client Not Found!');
        return null;

end;