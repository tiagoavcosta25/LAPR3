package lapr.project.controller;


import javafx.util.Pair;
import lapr.project.model.*;
import lapr.project.model.service.DeliveryRunService;
import lapr.project.model.service.GraphService;
import lapr.project.model.service.OrderService;
import lapr.project.model.service.PharmacyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RegisterDeliveryRunControllerTest {

    @InjectMocks
    private RegisterDeliveryRunController m_ctrl;

    @Mock
    private DeliveryRunService m_mockDeliveryRunService;
    @Mock
    private PharmacyService m_mockPharmacyService;
    @Mock
    private OrderService m_mockOrderService;
    @Mock
    private GraphService m_mockGraphService;

    RegisterDeliveryRunControllerTest() {

    }

    @BeforeEach
    void setUp() {
        this.m_ctrl = new RegisterDeliveryRunController();
        this.m_mockDeliveryRunService = Mockito.mock(DeliveryRunService.class);
        this.m_mockOrderService = Mockito.mock(OrderService.class);
        this.m_mockPharmacyService = Mockito.mock(PharmacyService.class);
        this.m_mockGraphService = Mockito.mock(GraphService.class);
        initMocks(this);
    }

    @Test
    void registerDeliveryRun() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 2));
        when(m_mockPharmacyService.getSuitableCourier()).thenReturn(new Courier());
        when(m_mockDeliveryRunService.newDeliveryRun(null,new ArrayList<>(),new Drone())).thenReturn(new DeliveryRun());
        when(m_mockGraphService.calculateBestVehicleAndBestPath(new ArrayList<>())).thenReturn(new Pair<Pair<VehicleModel,Double>, List<Address>>
                (new Pair<>(new VehicleModel(),0d),new ArrayList<>()));
        when(m_mockDeliveryRunService.getMostChargedDrone(new VehicleModel())).thenReturn(new Drone());
        when(m_mockDeliveryRunService.addNewDeliveryRun(new DeliveryRun())).thenReturn(true);
        boolean real = m_ctrl.registerDeliveryRun(new ArrayList<>());
        assertTrue(real);

        when(m_mockGraphService.calculateBestVehicleAndBestPath(new ArrayList<>())).thenReturn(new Pair<Pair<VehicleModel,Double>, List<Address>>
                (new Pair<>(new VehicleModel(1,"",2,3,4,new Battery(),
                        VehicleType.SCOOTER),0d),new ArrayList<>()));
        when(m_mockDeliveryRunService.getMostChargedScooter(new VehicleModel(1,"",2,3,4,new Battery(),
                VehicleType.SCOOTER))).thenReturn(new Scooter());
        when(m_mockDeliveryRunService.newDeliveryRun(new Courier(),new ArrayList<>(),new Scooter())).thenReturn(new DeliveryRun());

        real = m_ctrl.registerDeliveryRun(new ArrayList<>());
        assertTrue(real);

        when(m_mockDeliveryRunService.addNewDeliveryRun(new DeliveryRun())).thenReturn(false);
        real = m_ctrl.registerDeliveryRun(new ArrayList<>());
        assertFalse(real);

        ApplicationPOT.getInstance().setCurrentSession(new UserSession("",1));
        real = m_ctrl.registerDeliveryRun(new ArrayList<>());
        assertFalse(real);


    }
}