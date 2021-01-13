create or replace function getAddressesFromOrdersList(p_orderId "Order".id%type)
    return sys_refcursor
    is
    v_cursor sys_refcursor;
    address_not_found exception;
begin

    open v_cursor for
        select a.ID,a.LATITUDE,a.LONGITUDE,a.DOORNUMBER,a.STREETNAME,a.POSTALCODE,a.LOCALITY,a.COUNTRY
        from Address a
                 inner join "Order" o on o.ADDRESSID = a.ID
        where o.ID = p_orderId;

    if v_cursor is null then
        raise address_not_found;
    end if;

    return v_cursor;


EXCEPTION
    when address_not_found then
        RAISE_APPLICATION_ERROR(-20129,'Address NOT found!');
        return null;

end;