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
import java.util.Collections;
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
        when(m_mockScooterService.getScootersList("pharmacy@gmail.com")).thenReturn(new ArrayList<>());
        List<Scooter> result = m_ctrl.showScootersList("pharmacy@gmail.com");
        assertEquals(new ArrayList<>(),result);

        when(m_mockScooterService.getScootersList("")).thenThrow(new IllegalArgumentException());
        result = m_ctrl.showScootersList("");
        assertEquals(Collections.emptyList(),result);
    }

    @Test
    void getScooterInformation() {
        System.out.println("getScooterInformation");
        when(m_mockScooterService.getScooter(1)).thenReturn(new Scooter());
        Scooter result = m_ctrl.getScooterInformation(1);
        assertEquals(new Scooter(),result);
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
    void getScooterService() {
        ScooterService expected = m_mockScooterService;
        ScooterService real = m_ctrl.getScooterService();
        assertEquals(expected,real);
    }

    @Test
    void setScooterService() {
        ScooterService expected = new ScooterService();
        m_ctrl.setScooterService(expected);
        ScooterService real = m_ctrl.getScooterService();
        assertEquals(expected,real);
    }
}