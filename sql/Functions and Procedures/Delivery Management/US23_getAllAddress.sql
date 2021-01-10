CREATE OR REPLACE FUNCTION getAllAddress return SYS_REFCURSOR
    IS
    c1 SYS_REFCURSOR;
    address_not_found exception;
BEGIN

    OPEN c1 FOR
    SELECT *
    FROM ADDRESS;
    if c1 is null then
        raise address_not_found;
    end if;
    return c1;

EXCEPTION
    when address_not_found then
        raise_application_error(-20132, 'Address Not Found!');
        return null;
end;
/

