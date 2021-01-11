package lapr.project.controller;

import lapr.project.data.registration.PharmacyRegistration;
import lapr.project.model.*;
import lapr.project.data.registration.ScooterRegistration;
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

class UpdateScooterControllerTest {

    @InjectMocks
    private UpdateScooterController m_ctrl;


    @Mock
    private ScooterRegistration m_mockScooterRegistration;

    @Mock
    private PharmacyRegistration m_mockPharmacyRegistration;

    @BeforeEach
    void setUp() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email"));
        this.m_ctrl = new UpdateScooterController();
        this.m_mockScooterRegistration = Mockito.mock(ScooterRegistration.class);
        this.m_mockPharmacyRegistration = Mockito.mock(PharmacyRegistration.class);
        initMocks(this);
    }

    @Test
    void getScootersList() {
        when(m_mockPharmacyRegistration.getPharmacyByManagerEmail("email")).thenReturn(new Pharmacy());
        when(m_mockScooterRegistration.getScootersList(-1)).thenReturn(new ArrayList<>());
        List<Scooter> result = m_ctrl.getScootersList();
        assertEquals(new ArrayList<>(),result);
    }

    @Test
    void updateScooter() {
        when(m_mockScooterRegistration.updateScooterFromDB(1,1.0f,"idle",1.0f,1.0f,
                1,1.0f)).thenReturn(true);
        boolean result = m_ctrl.updateScooter(1,1.0f,"idle",1.0f,1.0f,1,
                1.0f);
        assertTrue(result);
    }
}