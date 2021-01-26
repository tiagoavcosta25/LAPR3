package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.service.ClientService;
import lapr.project.model.service.DroneService;
import lapr.project.model.service.PharmacyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class UpdateDroneControllerTest {

    UpdateDroneControllerTest() {
    }

    @BeforeEach
    void setUp() {
        this.m_ctrl = new UpdateDroneController();
        this.m_mockDroneService = Mockito.mock(DroneService.class);
        initMocks(this);
    }

    @InjectMocks
    private UpdateDroneController m_ctrl;

    @Mock
    private DroneService m_mockDroneService;

    @Mock
    private PharmacyService m_mockPharmacyService;

    @Test
    void showPharmacies() {
        System.out.println("showPharmacies");

        List<Pharmacy> expectedListPharmacies = new ArrayList<>(Arrays.asList(new Pharmacy()));

        when(m_mockPharmacyService.getPharmacies()).thenReturn(expectedListPharmacies);

        List<Pharmacy> result = m_ctrl.showPharmacies();
        assertEquals(expectedListPharmacies, result);
    }

    @Test
    void showDronesList() {
        System.out.println("showDronesList");
        when(m_mockDroneService.getDronesList("pharmacy@gmail.com")).thenReturn(new ArrayList<>());
        List<Drone> result = m_ctrl.showDronesList("pharmacy@gmail.com");
        assertEquals(new ArrayList<>(),result);

        when(m_mockDroneService.getDronesList("pharmacy@gmail.com")).thenReturn(null);
        result = m_ctrl.showDronesList("pharmacy@gmail.com");
        assertNull(result);

        List<Drone> lst = new ArrayList<>();
        lst.add(new Drone());
        when(m_mockDroneService.getDronesList("pharmacy@gmail.com")).thenReturn(lst);
        result = m_ctrl.showDronesList("pharmacy@gmail.com");
        assertEquals(lst,result);

        when(m_mockDroneService.getDronesList("")).thenThrow(new IllegalArgumentException());
        result = m_ctrl.showDronesList("");
        assertEquals(new ArrayList<>(),result);
    }

    @Test
    void updateDrone() {
        System.out.println("updateScooter");
        when(m_mockDroneService.updateDroneFromDB(1, 100d, "Test",
                2.0d, 2.0d, 30d, 100, 20d,
                10d)).thenReturn(true);

        boolean result = m_ctrl.updateDrone(1, 100d, "Test",
                2.0d, 2.0d, 30d, 100, 20d,
                10d);
        assertTrue(result);

        result = m_ctrl.updateDrone(-1, 100d, "Test",
                2.0d, 2.0d, 30d, 100, 20d,
                10d);
        assertFalse(result);

        when(m_mockDroneService.updateDroneFromDB(1, 100d, "Test",
                2.0d, 2.0d, 30d, 100, 20d,
                10d)).thenReturn(false);
        result = m_ctrl.updateDrone(1, 100d, "Test",
                2.0d, 2.0d, 30d, 100, 20d,
                10d);
        assertFalse(result);
    }

    @Test
    void getPharmacyService() {
        PharmacyService expected = m_mockPharmacyService;
        PharmacyService real = m_ctrl.getPharmacyService();
        assertEquals(expected,real);
    }

    @Test
    void setPharmacyService() {
        PharmacyService expected = new PharmacyService();
        m_ctrl.setPharmacyService(expected);
        PharmacyService real = m_ctrl.getPharmacyService();
        assertEquals(expected,real);
    }

    @Test
    void getDroneService() {
        DroneService expected = m_mockDroneService;
        DroneService real = m_ctrl.getDroneService();
        assertEquals(expected,real);
    }

    @Test
    void setDroneService() {
        DroneService expected = new DroneService();
        m_ctrl.setDroneService(expected);
        DroneService real = m_ctrl.getDroneService();
        assertEquals(expected,real);
    }
}