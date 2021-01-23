create or replace function getPharmacyTransfer(p_transferId IN PHARMACYTRANSFER.ID%type)
    return sys_refcursor is
    v_cursor sys_refcursor;
    transfer_not_found exception;
begin

    open v_cursor for
    select PT.ID, PT.TRANSFERDATE, PT.QUANTITY, P2.*, PH.ID, PH.NAME, PH.EMAIL, A3.*, O.ID, O.DESCRIPTION, O.ORDERSTATUS, O.ORDERDATE, O.TOTALWEIGHT, O.AMOUNT, O.ADDITIONALFEE, O.ISHOMEDELIVERY, U.*,
           C.CREDITS, A1.*, P.ID, P.NAME, P.EMAIL, A2.*
    from PHARMACYTRANSFER PT
             inner join PHARMACY PH ON PT.NEARBYPHARMACYID = PH.ID
             inner join ADDRESS A3 on A3.LONGITUDE = PH.ADDRESSLONGITUDE and A3.LATITUDE = PH.ADDRESSLATITUDE
             inner join PRODUCT P2 on PT.PRODUCTID = P2.ID
             inner join "Order" O on O.ID = PT.ORDERID
             inner join CLIENT C on O.CLIENTID = C.USERID
             inner join "User" U on C.USERID = U.ID
             inner join ADDRESS A1 on A1.LONGITUDE = C.ADDRESSLONGITUDE and A1.LATITUDE = C.ADDRESSLATITUDE
             inner join PHARMACY P on O.PHARMACYID = P.ID
             inner join ADDRESS A2 on A2.LONGITUDE = P.ADDRESSLONGITUDE and A2.LATITUDE = P.ADDRESSLATITUDE
    where O.ID = p_transferId;


    if v_cursor is null then
        raise transfer_not_found;
    end if;

    return v_cursor;

EXCEPTION
    when transfer_not_found then
        raise_application_error(-20141, 'Transfer Not Found!');
        return null;
end;