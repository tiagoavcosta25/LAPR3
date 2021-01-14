CREATE OR REPLACE PROCEDURE removeCourier(intID IN "User".ID%TYPE) IS

BEGIN

    DELETE from "COURIER" where "COURIER".USERID = intId;
    DELETE from "User" where id = intID;

end;