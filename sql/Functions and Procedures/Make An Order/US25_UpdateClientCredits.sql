create or replace procedure updateClientCredits(p_email "User".EMAIL%type, p_credits CLIENT.CREDITS%type)
    is
    v_id CLIENT.USERID%type;
begin

    SELECT ID
    INTO v_id
    FROM "User"
    INNER JOIN CLIENT ON "User".ID = CLIENT.USERID
    WHERE EMAIL = p_email;

    UPDATE CLIENT
    SET CREDITS = p_credits
    WHERE USERID = v_id;

end;