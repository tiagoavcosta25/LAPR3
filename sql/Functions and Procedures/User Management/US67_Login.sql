create or replace procedure logIn(p_email "User".email%type, p_password "User".password%type, userType OUT int)
    is
    userIdentifier "User".id%type;
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
                userType = 4;
            else
                userType = 3;
            end if;

        else
            userType = 2;
        end if;

    else
        userType = 1;
    end if;


EXCEPTION
    when user_not_found then
        raise_application_error(-20025, 'User Not Found!');

end;