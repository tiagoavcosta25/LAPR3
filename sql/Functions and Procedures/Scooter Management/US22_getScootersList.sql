create or replace function getScootersList(p_pharmacyId IN PHARMACY.ID%TYPE)
    return sys_refcursor is
    v_scooters sys_refcursor;
    scooter_not_found exception;
begin

    OPEN v_scooters FOR
        SELECT *
        FROM SCOOTER
        WHERE SCOOTER.PHARMACYID = p_pharmacyId;

    if v_scooters is null then
        raise scooter_not_found;
    end if;

    return v_scooters;

EXCEPTION
    when scooter_not_found then
        raise_application_error(-20812, 'Scooters Not Found!');
        return null;

end;