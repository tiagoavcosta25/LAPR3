create or replace procedure addPharamcyManager(p_email "User".email%type, p_password "User".password%type,
                                        p_name "User".name%type, p_nif "User".nif%type)
    is
    userIdentifier    "User".id%type;
begin

    -- Creates a new User
    Insert into "User"(EMAIL, PASSWORD, NIF, NAME)
    Values (p_email, p_password, p_nif, p_name)
    returning id into userIdentifier;

-- Creates a new Pharmacy Manager
    Insert into Client(userId)
    Values (userIdentifier);
end;