create or replace PROCEDURE updateStockFromTransfer(p_pharmacyTransferId PHARMACYTRANSFER.ID%type)
    is
    v_checkTransfer PHARMACYTRANSFER.ID%type;
    v_orderPharmacyId "Order".PHARMACYID%type;
    v_nearbyPharmacyId PHARMACYTRANSFER.NEARBYPHARMACYID%type;
    v_productId PHARMACYTRANSFER.PRODUCTID%type;
    v_pharmacyStock PHARMACYPRODUCT.STOCK%type;
    v_nearbyPharmacyStock PHARMACYPRODUCT.STOCK%type;
    v_productQuantity ORDERPRODUCT.QUANTITY%type;
    transfer_not_found exception;
begin

    select ID
    into v_checkTransfer
    from PHARMACYTRANSFER
    where ID = p_pharmacyTransferId;

    if v_checkTransfer is null then
        raise transfer_not_found;
    end if;

    select PHARMACYID
    into v_orderPharmacyId
    from PHARMACYTRANSFER PT
    inner join "Order" O ON PT.ORDERID = O.ID
    where PT.ID = p_pharmacyTransferId;

    select NEARBYPHARMACYID
    into v_nearbyPharmacyId
    from PHARMACYTRANSFER PT
    where PT.ID = p_pharmacyTransferId;

    select PRODUCTID
    into v_productId
    from PHARMACYTRANSFER PT
    where PT.ID = p_pharmacyTransferId;

    select count(STOCK)
    into v_pharmacyStock
    from PHARMACYPRODUCT PP
    where PP.PRODUCTID = v_productId
    and PP.PHARMACYID = v_orderPharmacyId;

    select STOCK
    into v_nearbyPharmacyStock
    from PHARMACYPRODUCT PP
    where PP.PRODUCTID = v_productId
    and PP.PHARMACYID = v_nearbyPharmacyId;

    select QUANTITY
    into v_productQuantity
    from PHARMACYTRANSFER PT
    where PT.ID = p_pharmacyTransferId;

    if v_pharmacyStock = 0 then
        INSERT INTO PHARMACYPRODUCT(PHARMACYID, PRODUCTID, STOCK)
        VALUES (v_orderPharmacyId, v_productId, v_productQuantity);
    else
        update PHARMACYPRODUCT PT
        set STOCK = v_pharmacyStock + v_productQuantity
        where PT.PRODUCTID = v_productId
          and PT.PHARMACYID = v_orderPharmacyId;
    end if;

    update PHARMACYPRODUCT PT
    set STOCK = v_nearbyPharmacyStock - v_productQuantity
    where PT.PRODUCTID = v_productId
      and PT.PHARMACYID = v_nearbyPharmacyId;


EXCEPTION
    when transfer_not_found then
        raise_application_error(-20121, 'Transfer Not Found!');
end;