create or replace procedure updatePharmacyStock(p_pharmacyEmail "PHARMACY".EMAIL%type, p_productIds "PRODUCT".id%type,
                                                quantity "PHARMACYPRODUCT".STOCK%type)
    is
    quantityStock     int;
    v_checkPharmacyId int;
begin
    SELECT STOCK
    INTO quantityStock
    FROM PHARMACYPRODUCT
             INNER JOIN PHARMACY P on P.ID = PHARMACYPRODUCT.PHARMACYID
    WHERE P.EMAIL = p_pharmacyEmail
    and PRODUCTID = p_productIds;



    SELECT ID
    INTO v_checkPharmacyId
    FROM PHARMACY
    WHERE EMAIL = p_pharmacyEmail;

    UPDATE PHARMACYPRODUCT
    SET STOCK = quantityStock - quantity
    WHERE PHARMACYID = v_checkPharmacyId
      AND PRODUCTID = p_productIds;

    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        NULL;
end;