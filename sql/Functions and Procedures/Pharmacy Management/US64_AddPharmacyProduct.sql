create or replace procedure addPharmacyProduct(p_pharmacyEmail PHARMACY.EMAIL%type, p_productId PRODUCT.ID%type, p_stock PHARMACYPRODUCT.STOCK%type)
    is
    v_checkPharmacyId PHARMACY.ID%type;
    v_checkProductId PRODUCT.ID%type;
    v_checkStock number;
    v_existingStock PHARMACYPRODUCT.STOCK%type;
    pharmacy_not_found exception;
    product_not_found exception;
begin

    select ID
    into v_checkPharmacyId
    from PHARMACY
    where EMAIL = p_pharmacyEmail;

    if v_checkPharmacyId is null then
        raise pharmacy_not_found;
    end if;

    select ID
    into v_checkProductId
    from PRODUCT
    where ID = p_productId;

    if v_checkProductId is null then
        raise product_not_found;
    end if;

-- Creates a Pharmacy Product or Updates its stock
    select count(PHARMACYID)
    into v_checkStock
    from PHARMACYPRODUCT
    where PHARMACYID = v_checkPharmacyId
      and PRODUCTID = p_productId;

    if v_checkStock = 0 then
        Insert into PHARMACYPRODUCT(PHARMACYID, PRODUCTID, STOCK)
        Values (v_checkPharmacyId, p_productId, p_stock);
    else
        select STOCK
        into v_existingStock
        from PHARMACYPRODUCT
        where PHARMACYID = v_checkPharmacyId
          and PRODUCTID = p_productId;

        v_existingStock := v_existingStock + p_stock;
        update PHARMACYPRODUCT
        set STOCK = v_existingStock
        where PHARMACYID = v_checkPharmacyId
          and PRODUCTID = p_productId;
    end if;

EXCEPTION
    when pharmacy_not_found then
        raise_application_error(-20998, 'Pharmacy Not Found!');
    when product_not_found then
        raise_application_error(-20997, 'Product Not Found!');
end;