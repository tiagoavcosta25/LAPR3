create or replace procedure addPharmacyTransfer(p_orderId "Order".ID%type, p_date PHARMACYTRANSFER.TRANSFERDATE%TYPE, p_productId PRODUCT.ID%type, p_quantity PHARMACYTRANSFER.QUANTITY%type, p_pharmacyId PHARMACYTRANSFER.ID%type)
    is
    v_checkOrderId "Order".ID%type;
    v_checkProductId PRODUCT.ID%type;
    v_checkPharmacyId PHARMACY.ID%type;
    v_checkPharmacyTransferId PHARMACYTRANSFER.ID%type;
    pharmacy_not_found exception;
    product_not_found exception;
    order_not_found exception;
    pharmacy_transfer_not_found exception;
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

    select ID
    into v_checkOrderId
    from "Order"
    where ID = p_orderId;

    if v_checkOrderId is null then
        raise order_not_found;
    end if;

    update "Order"
    set ORDERSTATUS = 'Transfering Products'
    where ID = p_orderId;

-- Creates a Pharmacy Product or Updates its stock
    Insert into PHARMACYTRANSFER(TRANSFERDATE,ORDERID, PRODUCTID, QUANTITY, NEARBYPHARMACYID)
    Values (p_date,p_orderId, p_productId, p_quantity, p_pharmacyId);

    select ID
    into v_checkPharmacyTransferId
    from PHARMACYTRANSFER
    where NEARBYPHARMACYID = p_pharmacyId
      and PRODUCTID = p_productId
    and ORDERID = p_orderId;

    if v_checkPharmacyTransferId is null then
        raise pharmacy_transfer_not_found;
    end if;

EXCEPTION
    when pharmacy_not_found then
        raise_application_error(-20998, 'Pharmacy Not Found!');
    when product_not_found then
        raise_application_error(-20997, 'Product Not Found!');
    when order_not_found then
        raise_application_error(-20997, 'Product Not Found!');
    when pharmacy_transfer_not_found then
        raise_application_error(-20997, 'Product Not Found!');
end;