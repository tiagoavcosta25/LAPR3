create PROCEDURE updateProduct(intId IN PRODUCT.ID%TYPE,strName IN PRODUCT.NAME%TYPE, strDescription IN PRODUCT.DESCRIPTION%TYPE, fltUnitaryPrice IN PRODUCT.UNITARYPRICE%TYPE, fltUnitaryWeight IN PRODUCT.UNITARYWEIGHT%TYPE) IS
BEGIN

    UPDATE PRODUCT
    SET NAME = strName,
        DESCRIPTION = strDescription,
        UNITARYPRICE = fltUnitaryPrice,
        UNITARYWEIGHT = fltUnitaryWeight
    WHERE ID = intId;

end;