create or replace function getClosestPharmacyWithStock(p_orderId "Order".ID%type, p_productId PRODUCT.ID%type, p_quantity PHARMACYTRANSFER.QUANTITY%type)
    return sys_refcursor is
    v_orderPharmacyId "Order".PHARMACYID%type;
    v_checkProductId PRODUCT.ID%type;
    v_cursor sys_refcursor;
    pharmacy_not_found exception;
    product_not_found exception;
    order_not_found exception;
    pharmacy_transfer_not_found exception;
begin

    if v_checkProductId is null then
        raise product_not_found;
    end if;

    select A2.ID
    into v_orderPharmacyId
    from "Order"
    inner join PHARMACY PH on "Order".PHARMACYID = PH.ID
    inner join ADDRESS A2 on "Order".ADDRESSID = A2.ID
    where ID = p_orderId;

    if v_orderPharmacyId is null then
        raise order_not_found;
    end if;

-- Creates a Pharmacy Product or Updates its stock
    open v_cursor for
        select PH.ID, PH.NAME, A.*
        from PHARMACY PH
        inner join ADDRESS A on PH.ADDRESSID = A.ID
        inner join PATH P on A.ID = P.ADDRESSIDB
        inner join PHARMACYPRODUCT PP on PH.ID = PP.PHARMACYID
        where P.ADDRESSIDA = v_orderPharmacyId
    and PP.PRODUCTID = p_productId
    and PP.STOCK >= p_quantity;

    return v_cursor;


EXCEPTION
    when pharmacy_not_found then
        raise_application_error(-20998, 'Pharmacy Not Found!');
        return null;
    when product_not_found then
        raise_application_error(-20997, 'Product Not Found!');
        return null;
    when order_not_found then
        raise_application_error(-20997, 'Product Not Found!');
        return null;
    when pharmacy_transfer_not_found then
        raise_application_error(-20997, 'Product Not Found!');
        return null;
end;