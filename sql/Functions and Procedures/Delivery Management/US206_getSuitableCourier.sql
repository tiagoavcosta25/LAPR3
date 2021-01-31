create or replace function getSuitableCourier(phId Pharmacy.id%type)
    return sys_refcursor
    is
    v_cursor     sys_refcursor;
    v_id         COURIER.USERID%type;
    v_name       "User".NAME%TYPE;
    v_email      "User".EMAIL%TYPE;
    v_pw         "User".PASSWORD%type;
    v_nif        "User".NIF%TYPE;
    v_iban       COURIER.IBAN%type;
    v_pid        PHARMACY.EMAIL%type;
    v_countCheck number;
begin

    select count(u1.id)
    into v_countCheck
    from "User" u1
    inner join Courier c1 on c1.USERID = u1.ID
    where c1.PHARMACYID = phId and u1.id not in (select u.id
                        from COURIER c
                                 inner join PHARMACY p on p.ID = c.PHARMACYID
                                 inner join DELIVERYRUN d on d.COURIERID = c.USERID
                                 inner join "User" u on u.ID = c.USERID
                        where p.ID = phId);

    if v_countCheck > 0 then
        open v_cursor for
            select c.USERID, u1.NAME, u1.EMAIL, u1.PASSWORD, u1.NIF, c.IBAN, p.EMAIL
            from COURIER c
                     inner join PHARMACY p on p.ID = c.PHARMACYID
                     inner join "User" u1 on u1.ID = c.USERID
            where c.PHARMACYID = phId and u1.id not in (select u.id
                                from COURIER c
                                         inner join PHARMACY p on p.ID = c.PHARMACYID
                                         inner join DELIVERYRUN d on d.COURIERID = c.USERID
                                         inner join "User" u on u.ID = c.USERID
                                where p.ID = phId) fetch FIRST ROW ONLY;
    else
        open v_cursor for
            select c.USERID, u.NAME, u.EMAIL, u.PASSWORD, u.NIF, c.IBAN, p.EMAIL
            from COURIER c
                     inner join PHARMACY p on p.ID = c.PHARMACYID
                     inner join DELIVERYRUN d on d.COURIERID = c.USERID
                     inner join "User" u on u.ID = c.USERID
            where c.PHARMACYID = phId and (select count(dd.COURIERID)
                   from DELIVERYRUN dd
                   where dd.COURIERID = c.USERID) = (select MIN(count(dr.COURIERID)) as num
                                                     from COURIER co
                                                              inner join DELIVERYRUN dr on dr.COURIERID = co.USERID
                                                     GROUP BY dr.COURIERID) fetch FIRST ROW ONLY;

        for v_counter IN 0.. 0
            LOOP
                FETCH v_cursor INTO v_id,v_name,v_email,v_pw,v_nif,v_iban,v_pid;
                if v_cursor%notFound then
                    open v_cursor for
                        select c.USERID, u.NAME, u.EMAIL, u.PASSWORD, u.NIF, c.IBAN, p.EMAIL
                        from COURIER c
                                 inner join PHARMACY p on p.ID = c.PHARMACYID
                                 inner join "User" u on u.ID = c.USERID
                            fetch first row only;
                    return v_cursor;
                end if;
                EXIT;

            end loop;
    end if;

    return v_cursor;

end;
/

