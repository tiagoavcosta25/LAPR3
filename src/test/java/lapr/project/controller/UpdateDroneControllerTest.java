package lapr.project.controller;

import lapr.project.model.service.ClientService;
import lapr.project.model.service.DroneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

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

    @Test
    void validate() {
        when (m_mockDroneService.validate(101f,1,1f,232f,21d,
                12f,21f,"Das",3)).thenReturn(true);
        boolean real = m_ctrl.validate(101f,1,1f,232f,21d,
                12f,21f,"Das",3);
        assertTrue(real);

        when (m_mockDroneService.validate(101f,1,1f,232f,21d,
                12f,21f,"Das",3)).thenReturn(false);
        real = m_ctrl.validate(101f,1,1f,232f,21d,
                12f,21f,"Das",3);
        assertFalse(real);

    }

    @Test
    void updateDrone() {
        when (m_mockDroneService.updateDrone(101f,1,1f,232f,21d,
                12f,21f,"Das",3)).thenReturn(true);
        boolean real = m_ctrl.updateDrone(101f,1,1f,232f,21d,
                12f,21f,"Das",3);

        assertTrue(real);

        when (m_mockDroneService.updateDrone(101f,1,1f,232f,21d,
                12f,21f,"Das",3)).thenReturn(false);
        real = m_ctrl.updateDrone(101f,1,1f,232f,21d,
                12f,21f,"Das",3);

        assertFalse(real);
    }
}