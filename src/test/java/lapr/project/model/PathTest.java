package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {

    private Path path;

    private Path pathEqual;

    private Path pathDiff;

    PathTest() {
        path = new Path(2,2,2, 2,
                "Rua das Flores", 2, 2, 2);
        pathEqual = new Path(2,2,2, 2,
                "Rua das Flores1", 4, 3, 1);
        pathDiff = new Path(3,1,4, 5,
                "Rua das Flores", 2, 2, 2);
    }

    @Test
    void getLatitudeA() {
        double expResult = 2;
        double result = path.getLatitudeA();
        assertEquals(expResult, result);

        expResult = 3;
        assertNotEquals(expResult, result);
    }

    @Test
    void setLatitudeA() {
        double expResult = 2;
        double result = path.getLatitudeA();
        assertEquals(expResult, result);

        path.setLatitudeA(3);
        result = path.getLatitudeA();
        assertNotEquals(expResult, result);

        expResult = 3;
        assertEquals(expResult, result);
    }

    @Test
    void getLongitudeA() {
        double expResult = 2;
        double result = path.getLongitudeA();
        assertEquals(expResult, result);

        expResult = 3;
        assertNotEquals(expResult, result);
    }

    @Test
    void setLongitudeA() {
        double expResult = 2;
        double result = path.getLongitudeA();
        assertEquals(expResult, result);

        path.setLongitudeA(3);
        result = path.getLongitudeA();
        assertNotEquals(expResult, result);

        expResult = 3;
        assertEquals(expResult, result);
    }

    @Test
    void getLatitudeB() {
        double expResult = 2;
        double result = path.getLatitudeB();
        assertEquals(expResult, result);

        expResult = 3;
        assertNotEquals(expResult, result);
    }

    @Test
    void setLatitudeB() {
        double expResult = 2;
        double result = path.getLatitudeB();
        assertEquals(expResult, result);

        path.setLatitudeB(3);
        result = path.getLatitudeB();
        assertNotEquals(expResult, result);

        expResult = 3;
        assertEquals(expResult, result);
    }

    @Test
    void getLongitudeB() {
        double expResult = 2;
        double result = path.getLongitudeB();
        assertEquals(expResult, result);

        expResult = 3;
        assertNotEquals(expResult, result);
    }

    @Test
    void setLongitudeB() {
        double expResult = 2;
        double result = path.getLongitudeB();
        assertEquals(expResult, result);

        path.setLongitudeB(3);
        result = path.getLongitudeB();
        assertNotEquals(expResult, result);

        expResult = 3;
        assertEquals(expResult, result);
    }

    @Test
    void getName() {
        String expResult = "Rua das Flores";
        String result = path.getName();
        assertEquals(expResult, result);

        expResult = "";
        assertNotEquals(expResult, result);
    }

    @Test
    void setName() {
        String expResult = "Rua das Flores";
        String result = path.getName();
        assertEquals(expResult, result);

        path.setName("Path Test");
        result = path.getName();
        assertNotEquals(expResult, result);

        expResult = "Path Test";
        assertEquals(expResult, result);
    }

    @Test
    void getWindSpeed() {
        double expResult = 2;
        double result = path.getWindSpeed();
        assertEquals(expResult, result);

        expResult = 3;
        assertNotEquals(expResult, result);
    }

    @Test
    void setWindSpeed() {
        double expResult = 2;
        double result = path.getWindSpeed();
        assertEquals(expResult, result);

        path.setWindSpeed(3);
        result = path.getWindSpeed();
        assertNotEquals(expResult, result);

        expResult = 3;
        assertEquals(expResult, result);
    }

    @Test
    void getWindAngle() {
        double expResult = 2;
        double result = path.getWindAngle();
        assertEquals(expResult, result);

        expResult = 3;
        assertNotEquals(expResult, result);
    }

    @Test
    void setWindAngle() {
        double expResult = 2;
        double result = path.getWindAngle();
        assertEquals(expResult, result);

        path.setWindAngle(3);
        result = path.getWindAngle();
        assertNotEquals(expResult, result);

        expResult = 3;
        assertEquals(expResult, result);
    }

    @Test
    void getKineticFrictionCoefficient() {
        double expResult = 2;
        double result = path.getKineticFrictionCoefficient();
        assertEquals(expResult, result);

        expResult = 3;
        assertNotEquals(expResult, result);
    }

    @Test
    void setKineticFrictionCoefficient() {
        double expResult = 2;
        double result = path.getKineticFrictionCoefficient();
        assertEquals(expResult, result);

        path.setKineticFrictionCoefficient(3);
        result = path.getKineticFrictionCoefficient();
        assertNotEquals(expResult, result);

        expResult = 3;
        assertEquals(expResult, result);
    }

    @Test
    void testEquals() {
        assertEquals(path, path);
        assertNotEquals(path, pathDiff);
        assertNotEquals(null, path);
        assertNotEquals(path, null);

        assertEquals(path, pathEqual);

        pathEqual.setLatitudeA(1);
        assertNotEquals(path, pathEqual);


        pathEqual.setLatitudeA(2);
        pathEqual.setLongitudeA(1);
        assertNotEquals(path, pathEqual);

        pathEqual.setLongitudeA(2);
        pathEqual.setLatitudeB(1);
        assertNotEquals(path, pathEqual);

        pathEqual.setLatitudeB(2);
        pathEqual.setLongitudeB(1);
        assertNotEquals(path, pathEqual);
    }

    @Test
    void testToString() {
        String expResult = "";
        String result = path.toString();
        assertNotEquals(expResult, result);

        expResult = "Path{m_dblLatitudeA=2.0, m_dblLongitudeA=2.0, m_dblLatitudeB=2.0, m_dblLongitudeB=2.0, m_strName='Rua das Flores', m_dblWindSpeed=2.0, m_dblWindAngle=2.0, m_dblKineticFrictionCoefficient=2.0}";
        assertEquals(expResult, result);
    }
}