package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RouteTest {


    @Test
    void getVehicleModel() {
        Route r = new Route();
        VehicleModel real = r.getVehicleModel();
        assertNull(real);
    }

    @Test
    void setVehicleModel() {
        Route r = new Route(VehicleType.SCOOTER, new ArrayList<>());
        VehicleModel real = r.getVehicleModel();
        assertNull(real);
    }

    @Test
    void getVehicleType() {
        Route r = new Route();
        VehicleType real = r.getVehicleType();
        VehicleType expected = VehicleType.NOTDEFINED;
        assertEquals(expected, real);
    }

    @Test
    void setVehicleType() {
        Route r = new Route();
        r.setVehicleType(VehicleType.SCOOTER);
        VehicleType real = r.getVehicleType();
        VehicleType expected = VehicleType.SCOOTER;
        assertEquals(expected, real);
    }

    @Test
    void getAddressList() {
        Route r = new Route();
        List<Address> real = r.getAddressList();
        List<Address> expected = new ArrayList<>();
        assertEquals(expected, real);

        Address a = new Address();
        Address b = new Address();
        a.setLongitude(1d);
        b.setLongitude(2d);
        expected.add(a);
        expected.add(b);
        real = r.getAddressList();
        assertNotEquals(expected, real);
        r.setAddressList(expected);
        real = r.getAddressList();
        assertEquals(expected, real);
    }

    @Test
    void setAddressList() {
        Route r = new Route();
        List<Address> expected = new ArrayList<>();
        r.setAddressList(expected);
        List<Address> real = r.getAddressList();
        assertEquals(expected, real);
    }

    @Test
    void getPathList() {
        Route r = new Route();
        List<Path> expected = new ArrayList<>();
        r.setPathList(expected);
        List<Path> real = r.getPathList();
        assertEquals(expected, real);

        Path p = new Path();
        expected.add(p);
        real = r.getPathList();
        assertNotEquals(expected, real);

        r.setPathList(expected);
        real = r.getPathList();
        assertEquals(expected, real);

    }

    @Test
    void setPathList() {
        Route r = new Route();
        List<Path> expected = new ArrayList<>();
        r.setPathList(expected);
        List<Path> real = r.getPathList();
        assertEquals(expected, real);
    }

    @Test
    void getEnergyList() {
        Route r = new Route();
        List<Double> expected = new ArrayList<>();
        r.setEnergyList(expected);
        List<Double> real = r.getEnergyList();
        assertEquals(expected, real);
    }

    @Test
    void setEnergyList() {
        Route r = new Route();
        List<Double> expected = new ArrayList<>();
        r.setEnergyList(expected);
        List<Double> real = r.getEnergyList();
        assertEquals(expected, real);
    }

    @Test
    void getTimeList() {
        Route r = new Route();
        List<Double> expected = new ArrayList<>();
        r.setTimeList(expected);
        List<Double> real = r.getTimeList();
        assertEquals(expected, real);
    }

    @Test
    void setTimeList() {
        Route r = new Route();
        List<Double> expected = new ArrayList<>();
        r.setTimeList(expected);
        List<Double> real = r.getTimeList();
        assertEquals(expected, real);
    }

    @Test
    void getChargeStops() {
        Route r = new Route();
        List<Address> expected = new ArrayList<>();
        r.setChargeStops(expected);
        List<Address> real = r.getChargeStops();
        assertEquals(expected, real);
    }

    @Test
    void setChargeStops() {
        Route r = new Route();
        List<Address> expected = new ArrayList<>();
        r.setChargeStops(expected);
        List<Address> real = r.getChargeStops();
        assertEquals(expected, real);
    }

    @Test
    void getTotalTime() {
        Route r = new Route();
        Double expected = Double.MAX_VALUE;
        Double real = r.getTotalTime();
        assertEquals(expected, real);

        List<Double> lstTime = new ArrayList<>(Arrays.asList(3d, 5d));
        r.setTimeList(lstTime);
        List<Address> lstAddresses = new ArrayList<>(Arrays.asList(new Address(), new Address()));
        r.setAddressList(lstAddresses);
        expected = 8d;
        real = r.getTotalTime();
        assertEquals(expected, real);
    }

    @Test
    void getTotalEnergy() {
        Route r = new Route();
        Double expected = Double.MAX_VALUE;
        Double real = r.getTotalEnergy();
        assertEquals(expected, real);

        List<Double> lstEnergy = new ArrayList<>(Arrays.asList(3d, 5d));
        r.setEnergyList(lstEnergy);
        List<Address> lstAddresses = new ArrayList<>(Arrays.asList(new Address(), new Address()));
        r.setAddressList(lstAddresses);
        expected = 8d;
        real = r.getTotalEnergy();
        assertEquals(expected, real);
    }

    @Test
    void getTotalDistance() {

        Route r = new Route();
        Double expected = Double.MAX_VALUE;
        Double real = r.getTotalDistance();
        assertEquals(expected, real);

        List<Address> lstAddresses = new ArrayList<>(Arrays.asList(new Address(19d, 19d, 19d,
                "", "", "", "", ""), new Address(11d, 11d,
                11d, "", "", "", "", "")));
        r.setAddressList(lstAddresses);
        expected = 1236156.9692256802d;
        real = r.getTotalDistance();
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
        Route r = new Route();
        Route r2 = new Route(VehicleType.SCOOTER, new ArrayList<>());

        assertEquals(r, r);
        assertNotEquals(r, r2);
        assertNotEquals(null, r);
        assertNotEquals(r, null);

        Route rEqual = new Route();
        assertEquals(r, rEqual);
        assertNotEquals(r, new Product());

        rEqual.setVehicleType(VehicleType.DRONE);
        assertNotEquals(r, rEqual);

        rEqual = new Route();
        rEqual.setVehicleModel(new VehicleModel("1", 1d, 1d ,1d,
                new Battery(), VehicleType.DRONE));
        assertNotEquals(r, rEqual);

        rEqual = new Route();
        rEqual.setAddressList(new ArrayList<>(Arrays.asList(new Address(19d, 19d, 19d,
                "", "", "", "", ""), new Address(11d, 11d,
                11d, "", "", "", "", ""))));
        assertNotEquals(r, rEqual);

        rEqual = new Route();
        rEqual.setPathList(new ArrayList<>(Arrays.asList(new Path(19d, 19d, 11d,
                11d, "", 19d, 19d, 19d, VehicleType.DRONE),
                new Path())));
        assertNotEquals(r, rEqual);

        rEqual = new Route();
        rEqual.setTimeList(new ArrayList<>(Arrays.asList(3d, 5d)));
        assertNotEquals(r, rEqual);

        rEqual = new Route();
        rEqual.setEnergyList(new ArrayList<>(Arrays.asList(3d, 5d)));
        assertNotEquals(r, rEqual);

        rEqual = new Route();
        rEqual.setChargeStops(new ArrayList<>(Arrays.asList(new Address(19d, 19d, 19d,
                "", "", "", "", ""), new Address(11d, 11d,
                11d, "", "", "", "", ""))));
        assertNotEquals(r, rEqual);

    }

    @Test
    void testHashCode() {
        Route oRoute = new Route();
        int real = oRoute.hashCode();
        int expected = oRoute.hashCode();;
        System.out.println(expected);
        assertEquals(expected, real);

        expected = 0;
        assertNotEquals(expected, real);
    }

    @Test
    void testToString() {
        String expResult = "";
        Route oRoute = new Route(VehicleType.SCOOTER, new ArrayList<>(Arrays.asList(new Address(19d, 19d, 19d,
                "", "", "", "", ""), new Address(11d, 11d,
                11d, "", "", "", "", ""))));
        String result = oRoute.toString();
        assertEquals(expResult, result);

        oRoute.setTimeList(new ArrayList<>(Arrays.asList(3d, 5d)));
        oRoute.setEnergyList(new ArrayList<>(Arrays.asList(3d, 5d)));
        oRoute.setVehicleModel(new VehicleModel());
        oRoute.setPathList(new ArrayList<>(Arrays.asList(new Path(19d, 19d, 11d,
                        11d, "", 19d, 19d, 19d, VehicleType.DRONE))));
        result = oRoute.toString().substring(0, 33);
        expResult = "\nBest vehicle model for delivery:";
        assertEquals(expResult, result);

        oRoute.setChargeStops(new ArrayList(Arrays.asList(new Address(19d, 19d, 19d,
                "", "", "", "", ""), new Address(11d, 11d,
                11d, "", "", "", "", ""))));
        result = oRoute.toString().substring(0, 33);
        expResult = "\nBest vehicle model for delivery:";
        assertEquals(expResult, result);
    }

    @Test
    void testToString2() {
        String expResult = "";
        Route route = new Route();
        String result = route.toString();
        System.out.println(result);
        expResult = "\nBest vehicle model for delivery:";
        assertNotEquals(expResult, result);
        expResult = "";
        assertEquals(expResult, result);

        route.setVehicleModel(new VehicleModel());
        result = route.toString();
        expResult = route.toString();
        assertEquals(expResult, result);
    }
}