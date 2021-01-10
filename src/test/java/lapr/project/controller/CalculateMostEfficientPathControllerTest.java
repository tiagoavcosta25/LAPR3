package lapr.project.controller;

import lapr.project.data.registration.DeliveryRegistration;
import lapr.project.data.registration.OrderRegistration;
import lapr.project.model.Address;
import lapr.project.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CalculateMostEfficientPathControllerTest {
    @InjectMocks
    private CalculateMostEfficientPathController calculateMostEfficientPathController;

    @Mock
    private OrderRegistration mockOrderRegistrarion;
    @Mock
    private DeliveryRegistration mockDeliveryRegistrarion;
    private Order auxTrue;
    private List<Address> aux2True;
    private double expectedTrue;

    @BeforeEach
    void setUp() {
        this.auxTrue = new Order();
        this.aux2True = new ArrayList<>();
        this.expectedTrue = -1;
        this.calculateMostEfficientPathController = new CalculateMostEfficientPathController();
        this.mockOrderRegistrarion = Mockito.mock(OrderRegistration.class);
        this.mockDeliveryRegistrarion = Mockito.mock(DeliveryRegistration.class);
        initMocks(this);
    }

    @Test
    void ensureCalculateMostEfficientPathWorks() {
        System.out.println("getMostEfficientPath");
        String email = "email6@gmail.com";
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email6@gmail.com"));
        when(mockOrderRegistrarion.getOrderByCourier(email)).thenReturn(auxTrue);
        when(mockDeliveryRegistrarion.getAddressesByDeliveryRunId(email)).thenReturn(aux2True);
        double result = calculateMostEfficientPathController.getShortestPath();
        assertEquals(expectedTrue, result);
    }
}
