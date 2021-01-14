create PROCEDURE updateCourier(intId IN COURIER.USERID%TYPE, strName IN "User".NAME%TYPE,
 strEmail IN "User".EMAIL%TYPE, intNIF IN "User".NIF%TYPE,
  strIban IN COURIER.IBAN%TYPE, pharmacyID IN Pharmacy.ID%TYPE) IS
BEGIN

        UPDATE "User"
            SET NAME = strName
        WHERE ID = intId;

        UPDATE "User"
        SET EMAIL = strEmail
        WHERE ID = intId;

        UPDATE "User"
        SET NIF = intNIF
        WHERE ID = intId;

        UPDATE COURIER
        SET IBAN = strIban
        WHERE USERID = intId;

        UPDATE COURIER
        SET PHARMACYID = pharmacyID
        WHERE USERID = intId;

end;