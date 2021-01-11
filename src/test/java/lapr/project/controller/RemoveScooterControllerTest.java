package lapr.project.controller;

import lapr.project.data.registration.PharmacyRegistration;
import lapr.project.data.registration.ScooterRegistration;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
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
    private ScooterRegistration m_mockScooterRegistration;

    @Mock
    private PharmacyRegistration m_mockPharmacyRegistration;

    @BeforeEach
    void setUp() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email"));
        this.m_ctrl = new RemoveScooterController();
        this.m_mockScooterRegistration = Mockito.mock(ScooterRegistration.class);
        this.m_mockPharmacyRegistration = Mockito.mock(PharmacyRegistration.class);
        initMocks(this);
    }

    @Test
    void showScootersList() {
        when(m_mockPharmacyRegistration.getPharmacyByManagerEmail("email")).thenReturn(new Pharmacy());
        when(m_mockScooterRegistration.getScootersList(-1)).thenReturn(new ArrayList<>());
        List<Scooter> result = m_ctrl.showScootersList();
        assertEquals(new ArrayList<>(),result);
    }

    @Test
    void removeScooter() {
        when(m_mockScooterRegistration.removeScooterFromDB(-1)).thenReturn(true);
        boolean real = m_ctrl.removeScooter(-1);
        assertTrue(real);
    }
}