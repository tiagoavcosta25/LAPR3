create or replace function addOrder(p_amount "Order".AMOUNT%type, p_totalWeight "Order".TOTALWEIGHT%type, p_additionalFee "Order".ADDITIONALFEE%type,
                                     p_description "Order".DESCRIPTION%type, p_date "Order".ORDERDATE%type, p_status "Order".ORDERSTATUS%type,
                                    p_isHomeDelivery "Order".ISHOMEDELIVERY%type, p_clientId Client.USERID%type, p_credits Client.CREDITS%type, p_pharmacyId PHARMACY.ID%type)
    return "Order".id%type is
    v_checkClientId            CLIENT.USERID%type;
    v_checkPharmacyId            PHARMACY.ID%type;
    v_orderId "Order".id%type;
    client_not_found exception;
    pharmacy_not_found exception;
begin

    select USERID
    into v_checkClientId
    from CLIENT
    where USERID = p_clientId;

    if v_checkClientId is null then
        raise client_not_found;
    end if;

    update CLIENT
    set CREDITS = p_credits
    where USERID = p_clientId;

    select ID
    into v_checkPharmacyId
    from PHARMACY
    where ID = p_pharmacyId;

    if v_checkPharmacyId is null then
        raise pharmacy_not_found;
    end if;

-- Creates a new Order
    Insert into "Order"(AMOUNT, TOTALWEIGHT, ADDITIONALFEE, DESCRIPTION, ORDERDATE, CLIENTID, PHARMACYID, ORDERSTATUS, ISHOMEDELIVERY, DELIVERYRUNID)
    Values (p_amount, p_totalWeight, p_additionalFee, p_description, p_date, p_clientId, p_pharmacyId, p_status, p_isHomeDelivery, 2);

    select id
    into v_orderId
    from "Order"
    where AMOUNT = p_amount
      and TOTALWEIGHT = p_totalWeight
      and ADDITIONALFEE = p_additionalFee
      and DESCRIPTION = p_description
      and ORDERDATE = p_date
      and CLIENTID = p_clientId
      and PHARMACYID = p_pharmacyId
    and ORDERSTATUS = p_status
    and ISHOMEDELIVERY = p_isHomeDelivery;

    return v_orderId;

EXCEPTION
    when client_not_found then
        raise_application_error(-20219, 'Client Not Found!');
        return null;
    when pharmacy_not_found then
        raise_application_error(-20220, 'Pharmacy Not Found!');
        return null;

end;