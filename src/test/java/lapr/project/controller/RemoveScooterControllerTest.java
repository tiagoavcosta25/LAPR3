package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.UserSession;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ScooterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RemoveScooterControllerTest {

    @InjectMocks
    private RemoveScooterController m_ctrl;

    @Mock
    private ScooterService m_mockScooterService;

    @Mock
    private PharmacyService m_mockPharmacyService;

    @BeforeEach
    void setUp() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email"));
        this.m_ctrl = new RemoveScooterController();
        this.m_mockScooterService = Mockito.mock(ScooterService.class);
        this.m_mockPharmacyService = Mockito.mock(PharmacyService.class);
        initMocks(this);
    }

    @Test
    void showPharmacies() {
        System.out.println("showPharmacies");

        when(m_mockPharmacyService.getPharmacies()).thenReturn(new ArrayList<>());
        List<Pharmacy> result = m_ctrl.showPharmacies();
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void showScootersList() {
        System.out.println("showScootersList");
        when(m_mockScooterService.getScootersList(-1)).thenReturn(new ArrayList<>());
        List<Scooter> result = m_ctrl.showScootersList(1);
        assertEquals(new ArrayList<>(),result);

        when(m_mockScooterService.getScootersList(-1)).thenReturn(null);
        List<Scooter> result1 = m_ctrl.showScootersList(-1);
        assertEquals(null,result1);
    }

    @Test
    void removeScooter() {
        System.out.println("removeScooter");
        when(m_mockScooterService.removeScooterFromDB(1)).thenReturn(true);
        Boolean result = m_ctrl.removeScooter(1);
        assertTrue(result);

        result = m_ctrl.removeScooter(-1);
        assertFalse(result);

        result = m_ctrl.removeScooter(0);
        assertFalse(result);

        when(m_mockScooterService.removeScooterFromDB(1)).thenReturn(false);
        result = m_ctrl.removeScooter(1);
        assertFalse(result);
    }
}