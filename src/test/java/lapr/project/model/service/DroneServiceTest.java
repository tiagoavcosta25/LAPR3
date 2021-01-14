package lapr.project.model.service;

import lapr.project.data.DroneDB;
import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class DroneServiceTest {

    @InjectMocks
    private DroneService m_oDroneService;

    @Mock
    private DroneDB m_mockDroneDB;

    private Drone expectedDrone;

    @BeforeEach
    void setUp() {
        this.expectedDrone = new Drone(250f, 30f, 1f, "No Charging",
                100f, 20, 20f, new Pharmacy());
        this.m_oDroneService = new DroneService();
        this.m_mockDroneDB = Mockito.mock(DroneDB.class);
        initMocks(this);
    }

    @Test
    void validate() {
        boolean expected = false;
        boolean real = m_oDroneService.validate(-2f,1,1f,21f,21d,
                12f,121f,"aas",152);
        assertEquals(expected,real);

        expected = false;
        real = m_oDroneService.validate(2f,-1,1f,21f,21d,
                12f,21f,"asa",12);
        assertEquals(expected,real);


        expected = false;
        real = m_oDroneService.validate(2f,1,-1f,21f,21d,
                12f,21f,"asa",12);
        assertEquals(expected,real);

        expected = false;
        real = m_oDroneService.validate(2f,1,1f,-1f,21d,
                12f,21f,"asa",12);
        assertEquals(expected,real);

        expected = false;
        real = m_oDroneService.validate(2f,1,1f,2f,-1d,
                12f,21f,"asa",12);
        assertEquals(expected,real);

        expected = false;
        real = m_oDroneService.validate(2f,1,1f,23f,21d,
                -1f,21f,"asa",12);
        assertEquals(expected,real);

        expected = false;
        real = m_oDroneService.validate(2f,1,1f,32f,21d,
                12f,-1f,"asa",12);
        assertEquals(expected,real);

        expected = false;
        real = m_oDroneService.validate(2f,1,1f,232f,21d,
                12f,21f,"",12);
        assertEquals(expected,real);

        real = m_oDroneService.validate(2f,1,1f,232f,21d,
                12f,21f,"Das",-1);
        assertEquals(expected,real);

        expected = true;
        real = m_oDroneService.validate(2f,1,1f,232f,21d,
                12f,21f,"Das",113);
        assertEquals(expected,real);

        expected = false;
        real = m_oDroneService.validate(101f,1,1f,232f,21d,
                12f,21f,"Das",3);
        assertEquals(expected,real);

        expected = true;
        real = m_oDroneService.validate(100f,1,1f,232f,21d,
                12f,21f,"Das",3);
        assertEquals(expected,real);


        real = m_oDroneService.validate(0f,1,1f,232f,21d,
                12f,21f,"Das",3);
        assertEquals(expected,real);

        expected = false;
        real = m_oDroneService.validate(32f,0,1f,232f,21d,
                12f,21f,"Das",3);
        assertEquals(expected,real);

        real = m_oDroneService.validate(32f,1,0f,232f,21d,
                12f,21f,"Das",3);
        assertEquals(expected,real);

        real = m_oDroneService.validate(32f,1,1f,0f,21d,
                12f,21f,"Das",3);
        assertEquals(expected,real);

        real = m_oDroneService.validate(32f,1,1f,232f,0d,
                12f,21f,"Das",3);
        assertEquals(expected,real);

        real = m_oDroneService.validate(32f,1,1f,232f,21d,
                0f,21f,"Das",3);
        assertEquals(expected,real);

        real = m_oDroneService.validate(32f,1,1f,232f,21d,
                12f,0f,"Das",3);
        assertEquals(expected,real);

        real = m_oDroneService.validate(32f,1,1f,232f,21d,
                12f,21f,"Das",0);
        assertEquals(expected,real);


    }

    @Test
    void updateDrone() {
        when(m_mockDroneDB.updateDrone(1f,1,1f,1f,1d,
                1f,1f,"As",2)).thenReturn(true);
        boolean result = m_oDroneService.updateDrone(1f,1,1f,1f,1d,
                1f,1f,"As",2);
        assertTrue(result);

        when(m_mockDroneDB.updateDrone(1f,1,1f,1f,1d,
                1f,1f,"As",2)).thenReturn(false);
        result = m_oDroneService.updateDrone(1f,1,1f,1f,1d,
                1f,1f,"As",2);

        assertFalse(result);
    }

    @Test
    void newDrone() {
        System.out.println("newScooter");
        Drone result = m_oDroneService.newDrone(100f, "No Charging",
                250f, 30f,20, 20f, 1f, new Pharmacy());
        assertEquals(expectedDrone, result);
    }

    @Test
    void registerDrone() {
        System.out.println("registerDrone");
        when(m_mockDroneDB.registerDrone(expectedDrone)).thenReturn(true);
        boolean result = m_oDroneService.registerDrone(expectedDrone);
        assertTrue(result);

        when(m_mockDroneDB.registerDrone(null)).thenReturn(false);
        result = m_oDroneService.registerDrone(null);
        assertFalse(result);
    }
}