CREATE OR REPLACE PROCEDURE removeProduct(v_name IN PRODUCT.NAME%TYPE) IS
BEGIN

    DELETE from Product where name = v_name;

end;