package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.UserSession;
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
    private ScooterDB m_mockScooterDB;

    @Mock
    private PharmacyDB m_mockPharmacyDB;

    @BeforeEach
    void setUp() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email"));
        this.m_ctrl = new RemoveScooterController();
        this.m_mockScooterDB = Mockito.mock(ScooterDB.class);
        this.m_mockPharmacyDB = Mockito.mock(PharmacyDB.class);
        initMocks(this);
    }

    @Test
    void showScootersList() {
        when(m_mockPharmacyDB.getPharmacyByManagerEmail("email")).thenReturn(new Pharmacy());
        when(m_mockScooterDB.getScootersList(-1)).thenReturn(new ArrayList<>());
        List<Scooter> result = m_ctrl.showScootersList();
        assertEquals(new ArrayList<>(),result);

        when(m_mockPharmacyDB.getPharmacyByManagerEmail("email")).thenReturn(null);
        when(m_mockScooterDB.getScootersList(-1)).thenReturn(null);
        List<Scooter> result1 = m_ctrl.showScootersList();
        assertEquals(null,result1);
    }

    @Test
    void removeScooter() {
        when(m_mockScooterDB.removeScooterFromDB(-1)).thenReturn(true);
        boolean real = m_ctrl.removeScooter(-1);
        assertTrue(real);
    }
}