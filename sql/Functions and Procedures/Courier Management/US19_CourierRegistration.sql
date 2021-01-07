CREATE OR REPLACE PROCEDURE addCourier(strName "User".NAME%type, strEmail "User".EMAIL%type, strPassword "User".PASSWORD%type,
                                        strNif "User".NIF%type, strIban COURIER.IBAN%type) IS
    courierValidation int;
    invalidInsertion exception;
    userId "User".id%type;
BEGIN

-- Validates existing Courier
SELECT COUNT(us.NAME) INTO courierValidation
FROM "User" us
WHERE us.NIF = strNif;

IF courierValidation > 0 THEN
RAISE invalidInsertion;
END IF;

SELECT COUNT(co.IBAN) INTO courierValidation
FROM COURIER co
WHERE co.IBAN = strIban;

IF courierValidation > 0 THEN
    RAISE invalidInsertion;
END IF;

SELECT COUNT(us.EMAIL) INTO courierValidation
FROM "User" us
WHERE us.EMAIL = strEmail;

IF courierValidation > 0 THEN
    RAISE invalidInsertion;
END IF;

-- Creates a new User
INSERT INTO "User"(EMAIL,PASSWORD,NIF,NAME)
VALUES (strEmail,strPassword,strNif,strName)
RETURNING id into userId;

-- Inserir Courier
INSERT INTO COURIER(USERID,IBAN)
VALUES (userId,strName,strNif,strIban);

EXCEPTION
    when invalidInsertion then
        raise_application_error(-20025, 'Courier already exists!');
end;