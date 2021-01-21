CREATE OR REPLACE FUNCTION removeProductsPharmacy(pharmacyEmail "PHARMACY".EMAIL%type,productIds "PRODUCT".id%type, quantity "PHARMACYPRODUCT".STOCK%type)
    return sys_refcursor is

    RFC sys_refcursor;
    v_checkPharmacyId int;
    v_checkProductQuantity int;
    v_checkProductPharmacies int;
    pharmacy_not_found exception;
BEGIN

    /*VERIFIES IF THAT PHARMACY EXISTS*/
    SELECT COUNT("PHARMACY".ID) INTO v_checkPharmacyId
    FROM "PHARMACY"
    WHERE "PHARMACY".EMAIL = pharmacyEmail;

    IF v_checkPharmacyId = 0 THEN
        RAISE pharmacy_not_found;
    END IF;

    /*VERIFIES THE STOCK OF THE PHARMACY FOR THAT PRODUCT*/
    SELECT PHARMACYPRODUCT.STOCK INTO v_checkProductQuantity
    FROM PHARMACYPRODUCT INNER JOIN PHARMACY P2 on P2.ID = PHARMACYPRODUCT.PHARMACYID
    WHERE P2.EMAIL = pharmacyEmail AND PHARMACYPRODUCT.PRODUCTID = productIds;


    /*CASE WHERE THE PHARMACY DOESNT HAVE THE RIGHT QUANTITY FOR THE PRODUCT*/
    IF v_checkProductQuantity < quantity AND v_checkProductQuantity > 0 THEN

        /*CHECKS IF THERE IS ANY PHARMACY WITH THE REQUIRED QUANTITY*/
        SELECT COUNT(PRODUCT.ID) INTO v_checkProductPharmacies
        FROM PRODUCT INNER JOIN PHARMACYPRODUCT P on PRODUCT.ID = P.PRODUCTID
                     INNER JOIN PHARMACY P3 on P3.ID = P.PHARMACYID
        WHERE PRODUCT.ID = productIds AND P3.EMAIL != pharmacyEmail AND P.STOCK >= (quantity - v_checkProductQuantity);

        IF v_checkProductPharmacies = 0 THEN
            RETURN NULL;
        END IF;

        OPEN RFC FOR
            SELECT PRODUCT.*, quantity - v_checkProductQuantity
            FROM PRODUCT
            WHERE PRODUCT.ID = productIds;
        RETURN RFC;
    END IF;

    /*CASE WHERE THE PHARMACY DOESNT HAVE ANY QUANTITY FOR THE PRODUCT*/
    IF v_checkProductQuantity = 0 THEN
        DELETE
        FROM PHARMACYPRODUCT
        WHERE PHARMACYPRODUCT.PRODUCTID = productIds;

        /*CHECKS IF THERE IS ANY PHARMACY WITH THE REQUIRED QUANTITY*/
        SELECT COUNT(PRODUCT.ID) INTO v_checkProductPharmacies
        FROM PRODUCT INNER JOIN PHARMACYPRODUCT P on PRODUCT.ID = P.PRODUCTID
                     INNER JOIN PHARMACY P3 on P3.ID = P.PHARMACYID
        WHERE PRODUCT.ID = productIds AND P3.EMAIL != pharmacyEmail AND P.STOCK >= quantity;

        IF v_checkProductPharmacies = 0 THEN
            RETURN NULL;
        END IF;

        OPEN RFC FOR
            SELECT PRODUCT.*, quantity
            FROM PRODUCT
            WHERE PRODUCT.ID = productIds;
        RETURN RFC;
    END IF;

    RETURN RFC;

EXCEPTION
    when pharmacy_not_found then
        raise_application_error(-20025, 'Pharmacy Not Found!');
        RETURN NULL;

END;