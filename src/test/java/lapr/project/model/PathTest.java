package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {

    private Path path;

    PathTest() {
        path = new Path(2,4,"Rua das Flores");
    }

    @Test
    void getIdAddressA() {
        Integer expected = 2;
        Integer real = path.getIdAddressA();
        assertEquals(expected,real);
    }

    @Test
    void getIdAddressB() {
        Integer expected = 4;
        Integer real = path.getIdAddressB();
        assertEquals(expected,real);
    }

    @Test
    void getName() {
        String expected = "Rua das Flores";
        String real = path.getName();
        assertEquals(expected,real);
    }

    @Test
    void setIdAdressA() {
        Integer expected = 100;
        path.setIdAddressA(100);
        Integer real = path.getIdAddressA();
        assertEquals(expected,real);
    }

    @Test
    void setIdAddressB() {
        Integer expected = 50;
        path.setIdAddressB(50);
        Integer real = path.getIdAddressB();
        assertEquals(expected,real);
    }

    @Test
    void setName() {
        String expected = "New Path Name";
        path.setName("New Path Name");
        String real = path.getName();
        assertEquals(expected,real);
    }

    @Test
    void testEquals() {
        Path oPath = new Path(2,4,"Rua das Flores");
        boolean real = path.equals(oPath);
        assertTrue(real);

        assertEquals(path, path);

        Path oPath1 = new Path(3,5,"");
        boolean real1 = path.equals(oPath1);
        assertFalse(real1);

        String c = "";
        assertNotEquals(c, path);

        Path oPath2 = null;
        assertNotEquals(oPath2, path);

        Path oPath3 = new Path(2,5,"Rua das Flores");
        boolean real3 = path.equals(oPath3);
        assertFalse(real3);

        Path oPath4 = new Path(5,4,"Rua das Flores");
        boolean real4 = path.equals(oPath4);
        assertFalse(real4);

        Path oPath5 = new Path(2,4,"");
        boolean real5 = path.equals(oPath5);
        assertFalse(real5);
    }

    @Test
    void testHashCode() {
        Path oPath = new Path();
        int expected = 30;
        int real = oPath.hashCode();
        assertEquals(expected, real);
    }


    @Test
    void testToString() {
        Path oPath= new Path();
        String expected = "Path{" +
                "m_intIdAddressA=" + oPath.getIdAddressA() +
                ", m_intIdAddressB=" + oPath.getIdAddressB() +
                ", m_strName='" + oPath.getName() + '\'' +
                '}';
        String real = oPath.toString();
        assertEquals(expected, real);
    }
}