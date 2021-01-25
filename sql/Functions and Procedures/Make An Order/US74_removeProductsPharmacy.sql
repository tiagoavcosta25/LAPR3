CREATE OR REPLACE FUNCTION removeProductsPharmacy(pharmacyEmail "PHARMACY".EMAIL%type,productIds "PRODUCT".id%type, quantity "PHARMACYPRODUCT".STOCK%type)
    return sys_refcursor is

    RFC sys_refcursor;
    v_checkPharmacyId int;
    v_checkProductyId int;
    v_checkProductyPharmacy int;
    v_checkProductQuantity int;
    v_checkProductPharmacies int;
    pharmacy_not_found exception;
    product_not_found exception;
BEGIN

    /*VERIFIES IF THAT PHARMACY EXISTS*/
    SELECT COUNT("PHARMACY".ID) INTO v_checkPharmacyId
    FROM "PHARMACY"
    WHERE "PHARMACY".EMAIL = pharmacyEmail;

    IF v_checkPharmacyId = 0 THEN
        RAISE pharmacy_not_found;
    END IF;

    /*VERIFIES IF THAT PRODUCT EXISTS*/
    SELECT COUNT(PRODUCT.ID) INTO v_checkProductyId
    FROM PRODUCT
    WHERE PRODUCT.ID = productIds;

    IF v_checkProductyId = 0 THEN
        RAISE product_not_found;
    END IF;

    /*CHECK IF THE PRODUCT EVEN EXISTS ON THE PHARMACY*/
    SELECT COUNT(PHARMACYPRODUCT.PRODUCTID) INTO v_checkProductyPharmacy
    FROM PHARMACYPRODUCT INNER JOIN PHARMACY P2 on P2.ID = PHARMACYPRODUCT.PHARMACYID
    WHERE P2.EMAIL = pharmacyEmail AND PHARMACYPRODUCT.PRODUCTID = productIds;

    /*VERIFIES THE STOCK OF THE PHARMACY FOR THAT PRODUCT*/
    SELECT SUM(PHARMACYPRODUCT.STOCK) INTO v_checkProductQuantity
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
    IF v_checkProductQuantity = 0 OR v_checkProductyPharmacy = 0 THEN

        /*CHECK IF EVEN EXISTS ON THE PHARMACY OR IF THE THE STOCK IS 0*/
        SELECT COUNT(PHARMACYPRODUCT.PRODUCTID) INTO v_checkProductyPharmacy
        FROM PHARMACYPRODUCT INNER JOIN PHARMACY P2 on P2.ID = PHARMACYPRODUCT.PHARMACYID
        WHERE P2.EMAIL = pharmacyEmail AND PHARMACYPRODUCT.PRODUCTID = productIds;

        /* IF THE PRODUCT IS ON THE PHARMACY BUT WITH STOCK 0*/
        IF v_checkProductyPharmacy != 0 THEN
            SELECT ID INTO v_checkPharmacyId
            FROM PHARMACY
            WHERE EMAIL = pharmacyEmail;

            DELETE
            FROM PHARMACYPRODUCT
            WHERE PHARMACYPRODUCT.PRODUCTID = productIds AND PHARMACYPRODUCT.PHARMACYID = v_checkPharmacyId;
        end if;

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

    OPEN RFC FOR
        SELECT PRODUCT.*, -1
        FROM PRODUCT
        WHERE PRODUCT.ID = productIds;
    RETURN RFC;

EXCEPTION
    when pharmacy_not_found then
        raise_application_error(-20025, 'Pharmacy Not Found!');
        RETURN NULL;

    when product_not_found then
        raise_application_error(-20025, 'Product Not Found!');
        RETURN NULL;

END;