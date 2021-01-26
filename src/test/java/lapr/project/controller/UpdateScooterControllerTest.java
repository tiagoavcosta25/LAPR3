package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ProductService;
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

class UpdateScooterControllerTest {

    @InjectMocks
    private UpdateScooterController m_ctrl;

    @Mock
    private ScooterService m_mockScooterService;

    @Mock
    private PharmacyService m_mockPharmacyService;

    @BeforeEach
    void setUp() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email"));
        this.m_ctrl = new UpdateScooterController();
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
        assertEquals(result, new ArrayList<>());
    }
    @Test
    void showScootersList2() {
        System.out.println("showScootersList2");
        List<Scooter> lst = new ArrayList<>();
        lst.add(new Scooter());
        when(m_mockScooterService.getScootersList("pharmacy@gmail.com")).thenReturn(lst);
        List<Scooter> result = m_ctrl.showScootersList("pharmacy@gmail.com");
        assertEquals(lst,result);
    }

    @Test
    void updateScooter() {
        System.out.println("updateScooter");
        when(m_mockScooterService.updateScooterFromDB(1, 100d, "Test",
                2.0d, 2.0d, 30d, 100, 20d,
                10d)).thenReturn(true);

        boolean result = m_ctrl.updateScooter(1, 100d, "Test",
                2.0d, 2.0d, 30d, 100, 20d,
                10d);
        assertTrue(result);

        result = m_ctrl.updateScooter(-1, 100d, "Test",
                2.0d, 2.0d, 30d, 100, 20d,
                10d);
        assertFalse(result);

        when(m_mockScooterService.updateScooterFromDB(1, 100d, "Test",
                2.0d, 2.0d, 30d, 100, 20d,
                10d)).thenReturn(false);
        result = m_ctrl.updateScooter(1, 100d, "Test",
                2.0d, 2.0d, 30d, 100, 20d,
                10d);
        assertFalse(result);
    }
    @Test
    void testGetPharmacyService(){
        UpdateScooterController ctrl = new UpdateScooterController();
        PharmacyService expected = new PharmacyService();
        ctrl.setMoPharmacyService(expected);
        PharmacyService real = ctrl.getMoPharmacyService();
        assertEquals(expected, real);
    }

    @Test
    void testSetPharmacyService(){
        UpdateScooterController ctrl = new UpdateScooterController();
        PharmacyService expected = new PharmacyService();
        ctrl.setMoPharmacyService(expected);
        PharmacyService real = ctrl.getMoPharmacyService();
        assertEquals(expected, real);
    }
    @Test
    void testGetScooterService(){
        UpdateScooterController ctrl = new UpdateScooterController();
        ScooterService expected = new ScooterService();
        ctrl.setMoScooterService(expected);
        ScooterService real = ctrl.getMoScooterService();
        assertEquals(expected, real);
    }
}