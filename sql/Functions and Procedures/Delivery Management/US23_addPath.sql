CREATE OR REPLACE PROCEDURE addPath(dblLatitudeA IN PATH.LATITUDEA%TYPE, dblLongitudeA IN PATH.LONGITUDEA%TYPE,
dblLatitudeB IN PATH.LATITUDEB%TYPE, dblLongitudeB IN PATH.LONGITUDEB%TYPE, strName IN PATH.NAME%TYPE,
dblWindspeed IN PATH.WINDSPEED%TYPE, dblWindAngle IN PATH.WINDANGLE%TYPE,
dblKineticFrictionCoefficient IN PATH.KINETICFRICTIONCOEFFICIENT%TYPE) IS
BEGIN

    INSERT INTO PATH(LATITUDEA, LONGITUDEA, LATITUDEB, LONGITUDEB, NAME, WINDSPEED, WINDANGLE,
                     KINETICFRICTIONCOEFFICIENT)
    VALUES (dblLatitudeA, dblLongitudeA, dblLatitudeB, dblLongitudeB, strName, dblWindspeed, dblWindAngle,
            dblKineticFrictionCoefficient);

end;
/

