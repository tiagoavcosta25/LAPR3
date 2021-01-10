package lapr.project.controller;

import lapr.project.model.registration.OrderRegistration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.MockitoAnnotations.initMocks;

public class KnowDeliveryControllerTest {
    @InjectMocks
    private KnowDeliveryController knowDeliveryController;

    @Mock
    private OrderRegistration mockOrderRegistration;

    private boolean expectedTrue;

    @BeforeEach
    void setUp() {
        this.expectedTrue = true;
        this.knowDeliveryController = new KnowDeliveryController();
        this.mockOrderRegistration = Mockito.mock(OrderRegistration.class);
        initMocks(this);
    }

    @Test
    void ensureKnowDeliveryWorks() {

    }
}
