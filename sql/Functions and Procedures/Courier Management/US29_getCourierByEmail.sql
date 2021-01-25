CREATE OR REPLACE FUNCTION getCourierByEmail(strEmail "User".EMAIL%TYPE) RETURN SYS_REFCURSOR
    IS
    rf_cur sys_refcursor;
    courier_not_found exception;
    courierID int;
BEGIN
    select COUNT(c.USERID) INTO courierID
    from COURIER c INNER JOIN "User" U on c.USERID = U.ID
    WHERE U.EMAIL = strEmail;

    if courierID = 0 then
        raise courier_not_found;
    end if;

    open rf_cur for
        select c.USERID, U.NAME, U.EMAIL,U.PASSWORD,U.NIF,c.IBAN, P.ID,P.NAME,P.EMAIL,A2.*
        from COURIER c INNER JOIN "User" U on c.USERID = U.ID
                       INNER JOIN PHARMACY P on P.ID = c.PHARMACYID
                       INNER JOIN ADDRESS A2 on A2.LATITUDE = P.ADDRESSLATITUDE and A2.LONGITUDE = P.ADDRESSLONGITUDE
        WHERE U.EMAIL = strEmail;

    return rf_cur;
EXCEPTION
    when courier_not_found then
        raise_application_error(-20034, 'Courier Not Found!');
        return null;
end;
