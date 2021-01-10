CREATE OR REPLACE FUNCTION getPaths return SYS_REFCURSOR
    IS
    c1 SYS_REFCURSOR;
    paths_not_found exception;
BEGIN

    OPEN c1 FOR
        SELECT *
        FROM PATH;
    if c1 is null then
        raise paths_not_found;
    end if;
    return c1;

EXCEPTION
    when paths_not_found then
        raise_application_error(-20130, 'Paths Not Found!');
        return null;
end;
/

