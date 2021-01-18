CREATE OR REPLACE PROCEDURE removeCourier(strEmail "User".EMAIL%TYPE) IS

 userIDP "User".ID%TYPE;
 noCourierFoundException exception;

BEGIN

    SELECT U.ID INTO userIDP
    FROM "User" U
    WHERE U.EMAIL = strEmail;

    if userIDP is null then
        raise_application_error(-20034, 'Courier Not Found!');
    end if;

    DELETE from "COURIER" where "COURIER".USERID = userIDP;
    DELETE from "User" where id = userIDP;

end;