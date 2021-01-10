CREATE OR REPLACE PROCEDURE addPath(intAddressIdA PATH.ADDRESSIDA%TYPE, intAddressIdB PATH.ADDRESSIDB%TYPE, strName PATH.NAME%TYPE) IS
BEGIN

    INSERT INTO PATH(ADDRESSIDA, ADDRESSIDB, NAME)
    VALUES (intAddressIdA, intAddressIdB, NAME);

end;
/
