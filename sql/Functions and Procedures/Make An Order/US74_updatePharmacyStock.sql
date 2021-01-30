create or replace procedure updatePharmacyStock(pharmacyEmail "PHARMACY".EMAIL%type, productIds "PRODUCT".id%type,
                                                quantity "PHARMACYPRODUCT".STOCK%type)
    is
    quantityStock     int;
    v_checkPharmacyId int;
begin
    SELECT STOCK
    INTO quantityStock
    FROM PHARMACYPRODUCT
             INNER JOIN PHARMACY P on P.ID = PHARMACYPRODUCT.PHARMACYID
    WHERE P.EMAIL = pharmacyEmail;

    SELECT ID
    INTO v_checkPharmacyId
    FROM PHARMACY
    WHERE EMAIL = pharmacyEmail;

    UPDATE PHARMACYPRODUCT
    SET STOCK = quantityStock - quantity
    WHERE PHARMACYID = v_checkPharmacyId
      AND PRODUCTID = productIds;

    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        NULL;
end;