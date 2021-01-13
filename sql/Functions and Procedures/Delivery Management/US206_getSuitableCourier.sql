create or replace function getSuitableCourier
    return sys_refcursor
    is
    v_cursor sys_refcursor;
    courier_not_found exception;
begin

    open v_cursor for
        select c.USERID,u.NAME,u.EMAIL,u.PASSWORD,u.NIF,c.IBAN,p.ID
        from COURIER c
                 inner join PHARMACY p on p.ID = c.PHARMACYID
                 inner join DELIVERYRUN d on d.COURIERID = c.USERID
                 inner join "User" u on u.ID = c.USERID
        where (select count(dd.COURIERID)
               from DELIVERYRUN dd
               where dd.COURIERID = c.USERID) = (select MIN(count (dr.COURIERID)) as num
                                                 from COURIER co inner join DELIVERYRUN dr on dr.COURIERID = co.USERID
                                                 GROUP BY dr.COURIERID) fetch FIRST ROW ONLY;

    if v_cursor is null then
        raise courier_not_found;
    end if;

    return v_cursor;


EXCEPTION
    when courier_not_found then
        RAISE_APPLICATION_ERROR(-20130,'Courier NOT Found!');
        return null;

end;