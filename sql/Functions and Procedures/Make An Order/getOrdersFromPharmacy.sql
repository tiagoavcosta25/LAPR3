create or replace function getOrdersFromPharmacy(p_pharmacyId Pharmacy.id%type)
    return sys_refcursor
    is
    v_cursor sys_refcursor;
begin

    open v_cursor for
        select o.*, c.*, a.*, p.*, op.quantity,prod.*
        from Order o
            inner join Client c on c.id = o.clientId
            inner join Address a on a.id = o.addressId
            inner join Pharmacy p on p.id = o.pharmacyId
            inner join OrderProduct op on op.orderId = o.id
            inner join Product prod on prod.id = op.productid
            where o.pharmacyId = p_pharmacyId;




end;