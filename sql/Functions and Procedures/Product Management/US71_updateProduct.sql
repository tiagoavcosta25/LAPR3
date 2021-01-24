create or replace PROCEDURE updateProduct(strProductName IN PRODUCT.NAME%TYPE, strName IN PRODUCT.NAME%TYPE := NULL,
 strDescription IN PRODUCT.DESCRIPTION%TYPE := NULL, fltUnitaryPrice IN PRODUCT.UNITARYPRICE%TYPE := -1,
  fltUnitaryWeight IN PRODUCT.UNITARYWEIGHT%TYPE := -1) IS
    no_data_to_update exception ;
BEGIN

    IF(strName IS NULL AND strDescription IS NULL AND fltUnitaryPrice = 0 AND fltUnitaryWeight = 0) THEN
        raise no_data_to_update;
    end if;

    IF(strName IS NOT NULL) THEN
        UPDATE PRODUCT
            SET NAME = strName
        WHERE NAME = strProductName;
    end if;

    IF(strDescription IS NOT NULL) THEN
        UPDATE PRODUCT
            SET DESCRIPTION = strDescription
        WHERE NAME = strName;
    end if;

    IF(fltUnitaryPrice != -1) THEN
        UPDATE PRODUCT
            SET UNITARYPRICE = fltUnitaryPrice
        WHERE NAME = strName;
    end if;

    IF(fltUnitaryWeight != -1) THEN
        UPDATE PRODUCT
            SET UNITARYWEIGHT = fltUnitaryWeight
        WHERE NAME = strName;
    end if;

    EXCEPTION
    when no_data_to_update then
        raise_application_error(-20035, 'No data to update!');

end;