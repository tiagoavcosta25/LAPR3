package lapr.project.model.service;

import lapr.project.data.CourierDB;
import lapr.project.data.OrderDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class CourierServiceTest {

    @InjectMocks
    private CourierService courierService;

    @Mock
    private CourierDB mockCourierDB;

    private Courier expectedCourier;

    private boolean assertTrue;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException {
        this.assertTrue = true;
        this.expectedCourier = new Courier("Name", "email4@gmail.com","pwd",250161761,"PT98003506514853185258910", new Pharmacy());
        this.courierService = new CourierService();
        this.mockCourierDB = Mockito.mock(CourierDB.class);
        initMocks(this);
    }

    @Test
    void newCourier() throws NoSuchAlgorithmException {
        System.out.println("newCourier");
        Courier result = courierService.newCourier("Name", "email4@gmail.com", 250161761,"PT98003506514853185258910", new Pharmacy());
        assertEquals(expectedCourier, result);
    }

    @Test
    void registersCourier() {
        System.out.println("registerOrder");
        when(mockCourierDB.addCourierToDB("Name", "email4@gmail.com","9003d1df22eb4d3820015070385194c8",250161761,"PT98003506514853185258910", -1)).thenReturn(true);
        boolean result = courierService.registersCourier(expectedCourier);
        assertTrue(result);

        when(mockCourierDB.addCourierToDB("Name", "email4@gmail.com","9003d1df22eb4d3820015070385194c8",250161761,"PT98003506514853185258910", -1)).thenReturn(false);
        result = courierService.registersCourier(expectedCourier);
        assertFalse(result);
    }

    @Test
    void getCourierByID() {
        when(mockCourierDB.getCourierByID(-1)).thenReturn(new Courier());
        Courier c = courierService.getCourierByID(-1);
        assertEquals(new Courier(),c);
    }

    @Test
    void updateCourier() {
        Courier c = courierService.updateCourier(new Courier(),"123","email@gmail.com",123456789,
                "PT98003506514853185258910",new Pharmacy());
        assertNotEquals(c,new Courier());

    }

    @Test
    void  updateCourierDB() {
        when(mockCourierDB.updateCourierDB(new Courier())).thenReturn(assertTrue);
        boolean result = courierService.updateCourierDB(new Courier());
        assertEquals(assertTrue,result);

        when(mockCourierDB.updateCourierDB(new Courier())).thenReturn(false);
        result = courierService.updateCourierDB(new Courier());

        assertFalse(result);
    }
}