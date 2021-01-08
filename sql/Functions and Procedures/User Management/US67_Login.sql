create or replace function logIn(p_email IN "User".email%type, p_password IN "User".password%type)
return int
    is
    userIdentifier "User".id%type;
    userType int;
    user_not_found exception;
begin

    -- Checks if User exists and if the password given by parameter matches
    select id
    into userIdentifier
    from "User"
    where email = p_email
      and password = p_password;

    if userIdentifier is null then
        raise user_not_found;
    end if;

    -- Client
    select USERID
    into userType
    from Client
    where USERID = userIdentifier;

    -- 1 : Is a Client
    -- 2 : Is an Admin
    -- 3 : Is a Courier
    -- 4 : Is a Pharmacy Manager
    if userType is null then
        select USERID
        into userType
        from ADMINISTRATOR
        where USERID = userIdentifier;

        if userType is null then

            select USERID
            into userType
            from Courier
            where USERID = userIdentifier;

            if userType is null then
                return 4;
            else
                return 3;
            end if;

        else
            return 2;
        end if;

    else
        return 1;
    end if;



EXCEPTION
    when user_not_found then
        return -1;
    when NO_DATA_FOUND then
        return -1;

end;