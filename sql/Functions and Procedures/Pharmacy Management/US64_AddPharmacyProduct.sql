create or replace procedure addPharmacyProduct(p_pharmacyId PHARMACY.ID%type, p_productId PRODUCT.ID%type, p_stock PHARMACYPRODUCT.STOCK%type)
    is
    v_checkPharmacyId PHARMACY.ID%type;
    v_checkProductId PRODUCT.ID%type;
    v_existingStock PHARMACYPRODUCT.STOCK%type;
    pharmacy_not_found exception;
    product_not_found exception;
begin

    select ID
    into v_checkPharmacyId
    from PHARMACY
    where ID = p_pharmacyId;

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

-- Creates a new Address
    select STOCK
    into v_existingStock
    from PHARMACYPRODUCT
    where PHARMACYID = p_pharmacyId
      and PRODUCTID = p_productId;

    if v_existingStock is null then
        Insert into PHARMACYPRODUCT(PHARMACYID, PRODUCTID, STOCK)
        Values (p_pharmacyId, p_productId, p_stock);
    else
        v_existingStock := v_existingStock + p_stock;
        update PHARMACYPRODUCT
        set STOCK = v_existingStock
        where PHARMACYID = p_pharmacyId
          and PRODUCTID = p_productId;
    end if;

EXCEPTION
    when pharmacy_not_found then
        raise_application_error(-20025, 'Pharmacy Not Found!');
        return null;
    when product_not_found then
        raise_application_error(-20025, 'Product Not Found!');
        return null;

end;