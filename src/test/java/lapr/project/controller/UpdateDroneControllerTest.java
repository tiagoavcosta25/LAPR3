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

        when(m_mockDroneService.getDronesList("")).thenThrow(new IllegalArgumentException());
        result = m_ctrl.showDronesList("");
        assertNull(result);
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
}