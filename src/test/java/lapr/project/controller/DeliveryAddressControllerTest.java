package lapr.project.controller;

import lapr.project.data.registration.CourierRegistration;
import lapr.project.model.Address;
import lapr.project.model.UserSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class DeliveryAddressControllerTest {

    @InjectMocks
    private DeliveryAddressController m_ctrl;

    @Mock
    private CourierRegistration m_mockCourierRegistration;

    DeliveryAddressControllerTest() {

    }

    @BeforeEach
    void setUp() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("test@gmail.com"));
        this.m_ctrl = new DeliveryAddressController();
        this.m_mockCourierRegistration = Mockito.mock(CourierRegistration.class);
        initMocks(this);
    }


    @Test
    void getDeliveryAddress() {
        String email = "test@gmail.com";
        when(m_mockCourierRegistration.getDeliveryAddress(email)).thenReturn(new Address());
        Address result = m_ctrl.getDeliveryAddress();
        assertEquals(new Address(),result);
    }

}