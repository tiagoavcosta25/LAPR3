CREATE OR REPLACE PROCEDURE removeProduct(intID IN PRODUCT.ID%TYPE) IS
BEGIN

    DELETE from Product where id = intId;

end;