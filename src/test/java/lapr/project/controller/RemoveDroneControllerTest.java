package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.UserSession;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RemoveDroneControllerTest {

    @InjectMocks
    private RemoveDroneController m_ctrl;

    @Mock
    private DroneService m_mockDroneService;

    @Mock
    private PharmacyService m_mockPharmacyService;

    @BeforeEach
    void setUp() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email"));
        this.m_ctrl = new RemoveDroneController();
        this.m_mockDroneService = Mockito.mock(DroneService.class);
        this.m_mockPharmacyService = Mockito.mock(PharmacyService.class);
        initMocks(this);
    }

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
        when(m_mockDroneService.getDronesList(1)).thenReturn(new ArrayList<>());
        List<Drone> result = m_ctrl.showDronesList(1);
        assertEquals(new ArrayList<>(),result);

        when(m_mockDroneService.getDronesList(1)).thenReturn(null);
        result = m_ctrl.showDronesList(1);
        assertNull(result);

        List<Drone> aux = new ArrayList<>();
        aux.add(new Drone());
        when(m_mockDroneService.getDronesList(1)).thenReturn(aux);
        result = m_ctrl.showDronesList(1);
        assertEquals(aux,result);

        when(m_mockDroneService.getDronesList(-2)).thenThrow(new IllegalArgumentException());
        result = m_ctrl.showDronesList(-2);
        assertNull(result);
    }

    @Test
    void removeDrone() {
        System.out.println("removeDrone");
        when(m_mockDroneService.removeDroneFromDB(1)).thenReturn(true);
        Boolean result = m_ctrl.removeDrone(1);
        assertTrue(result);

        result = m_ctrl.removeDrone(-1);
        assertFalse(result);

        result = m_ctrl.removeDrone(0);
        assertFalse(result);

        when(m_mockDroneService.removeDroneFromDB(1)).thenReturn(false);
        result = m_ctrl.removeDrone(1);
        assertFalse(result);
    }

}