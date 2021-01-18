create or replace procedure logIn(p_email IN "User".email%type, p_password IN "User".password%type, userType OUT int default -1)
    is
    userIdentifier "User".id%type;
    user_not_found exception;
begin

    -- Checks if User exists and if the password given by parameter matches
    begin
        select id
        into userIdentifier
        from "User"
        where email = p_email
          and password = p_password;
        EXCEPTION
        when NO_DATA_FOUND then
            raise_application_error(-20038,'User not found!');
    end;


    if userIdentifier = -1 then
        raise user_not_found;
    end if;

    -- Client
    begin
        select USERID
        into userType
        from Client
        where USERID = userIdentifier;

        userType := 1;

    EXCEPTION
        when NO_DATA_FOUND then
            userType := -1;
    end;

    -- 1 : Is a Client
    -- 2 : Is an Admin
    -- 3 : Is a Courier
    if userType = -1 then
        select USERID
        into userType
        from ADMINISTRATOR
        where USERID = userIdentifier;

        userType := 2;


    end if;



    EXCEPTION
    when NO_DATA_FOUND then
                    userType := 3;
end;