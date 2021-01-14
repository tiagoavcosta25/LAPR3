package lapr.project.controller;

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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ScooterInformationControllerTest {

    @InjectMocks
    private ScooterInformationController m_ctrl;

    @Mock
    private ScooterService m_mockScooterService;

    @Mock
    private PharmacyService m_mockPharmacyService;


    @BeforeEach
    void setUp() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email"));
        this.m_ctrl = new ScooterInformationController();
        this.m_mockScooterService = Mockito.mock(ScooterService.class);
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
    void showScootersList() {
        System.out.println("showScootersList");
        when(m_mockScooterService.getScootersList(1)).thenReturn(new ArrayList<>());
        List<Scooter> result = m_ctrl.showScootersList(1);
        assertEquals(new ArrayList<>(),result);

        when(m_mockScooterService.getScootersList(-2)).thenThrow(new IllegalArgumentException());
        result = m_ctrl.showScootersList(-2);
        assertNull(result);
    }

    @Test
    void getScooterInformation() {
        System.out.println("getScooterInformation");
        when(m_mockScooterService.getScooter(1)).thenReturn(new Scooter());
        Scooter result = m_ctrl.getScooterInformation(1);
        assertEquals(new Scooter(),result);
    }
}