package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.model.*;
import lapr.project.data.ScooterDB;
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
    private ScooterDB m_mockScooterDB;

    @Mock
    private PharmacyDB m_mockPharmacyDB;

    @BeforeEach
    void setUp() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email"));
        this.m_ctrl = new UpdateScooterController("","","");
        this.m_mockScooterDB = Mockito.mock(ScooterDB.class);
        this.m_mockPharmacyDB = Mockito.mock(PharmacyDB.class);
        initMocks(this);
    }

    @Test
    void getScootersList() {
        when(m_mockPharmacyDB.getPharmacyByManagerEmail("email")).thenReturn(new Pharmacy());
        when(m_mockScooterDB.getScootersList(-1)).thenReturn(new ArrayList<>());
        List<Scooter> result = m_ctrl.getScootersList();
        assertEquals(new ArrayList<>(),result);

        when(m_mockPharmacyDB.getPharmacyByManagerEmail("email")).thenReturn(null);
        when(m_mockScooterDB.getScootersList(-1)).thenReturn(null);
        List<Scooter> result1 = m_ctrl.getScootersList();
        assertEquals(new ArrayList<>(),result1);
    }

    @Test
    void updateScooter() {
        when(m_mockScooterDB.updateScooterFromDB(1,1.0f,"idle",1.0f,1.0f,
                1,1.0f)).thenReturn(true);
        boolean result = m_ctrl.updateScooter(1,1.0f,"idle",1.0f,1.0f,1,
                1.0f);
        assertTrue(result);
    }
}