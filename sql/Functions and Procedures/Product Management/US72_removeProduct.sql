CREATE OR REPLACE PROCEDURE removeProduct(v_name IN PRODUCT.NAME%TYPE) IS
    v_product PRODUCT%ROWTYPE;
BEGIN

    SELECT * into v_product
    FROM PRODUCT
    WHERE name = v_name;

    DELETE from Product where name = v_name;

EXCEPTION
    when no_data_found then
        raise_application_error(-20039, 'Product Not Found!');
end;