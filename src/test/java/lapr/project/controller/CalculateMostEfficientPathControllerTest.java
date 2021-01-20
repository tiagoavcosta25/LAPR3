package lapr.project.controller;

import javafx.util.Pair;
import lapr.project.data.DeliveryDB;
import lapr.project.data.DeliveryRunDB;
import lapr.project.model.Address;
import lapr.project.model.UserSession;
import lapr.project.model.service.DeliveryRunService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class CalculateMostEfficientPathControllerTest {
    @InjectMocks
    private CalculateMostEfficientPathController calculateMostEfficientPathController;

    @Mock
    private DeliveryRunService mockDeliveryRunService;

    private Pair<LinkedList<Address>, Double> expectedTrue;
    private List<Address> expectedList;
    private Address pharmacyAddress;

    @BeforeEach
    void setUp() {
        this.calculateMostEfficientPathController = new CalculateMostEfficientPathController();
        this.mockDeliveryRunService = Mockito.mock(DeliveryRunService.class);
        initMocks(this);
    }

    @Test
    void getShortestPath() {
        this.expectedTrue = new Pair<>(null, 10.0);
        addAddress();
        String email = "email6@gmail.com";
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email6@gmail.com"));
        when(mockDeliveryRunService.getAddressesByDeliveryRunId(email)).thenReturn(expectedList);
        this.pharmacyAddress = this.expectedList.get(this.expectedList.size() - 1);
        when(mockDeliveryRunService.calculateMostEfficientPath(this.pharmacyAddress, this.pharmacyAddress, this.expectedList)).thenReturn(expectedTrue);
        double result = this.calculateMostEfficientPathController.getShortestPath();
        assertEquals(expectedTrue.getValue(), result);
    }

    @Test
    void getShortestPath2() {
        String email = "email6@gmail.com";
        double expResult = -1;
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email6@gmail.com"));
        when(mockDeliveryRunService.getAddressesByDeliveryRunId(email)).thenReturn(expectedList);
        this.pharmacyAddress = new Address();
        when(mockDeliveryRunService.calculateMostEfficientPath(this.pharmacyAddress, this.pharmacyAddress, this.expectedList)).thenReturn(null);
        double result = this.calculateMostEfficientPathController.getShortestPath();
        assertEquals(expResult, result);
    }

    @Test
    void getShortestPath3() {
        String email = "email6@gmail.com";
        double expResult = -1;
        addAddress();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email6@gmail.com"));
        when(mockDeliveryRunService.getAddressesByDeliveryRunId(email)).thenReturn(expectedList);
        this.pharmacyAddress = this.expectedList.get(this.expectedList.size() - 1);
        when(mockDeliveryRunService.calculateMostEfficientPath(this.pharmacyAddress, this.pharmacyAddress, this.expectedList)).thenReturn(null);
        double result = this.calculateMostEfficientPathController.getShortestPath();
        assertEquals(expResult, result);
    }

    private void addAddress() {
        this.expectedList = new ArrayList<>();
        expectedList.add(new Address(41.1111112, -8.6114856,10d, "1", "789", "4747-857", "loc1", "Portugal"));
        expectedList.add(new Address(41.1414512, -8.5672412, 10d,"1", "123", "4747-857", "loc1", "Portugal"));
        expectedList.add(new Address(41.1516599, -8.5217484,10d, "1", "456", "4747-857", "loc1", "Portugal"));
        expectedList.add(new Address(41.1116911, -8.5874815,10d, "1", "858", "4747-857", "loc1", "Portugal"));
        expectedList.add(new Address(41.1825991, -8.6154487, 10d,"1", "128", "4747-857", "loc1", "Portugal"));
        expectedList.add(new Address(41.1155582, -8.6187858, 10d,"1", "169", "4747-857", "loc1", "Portugal"));
        expectedList.add(new Address(41.1784687, -8.6111664, 10d,"431", "R. Dr. António Bernardino de Almeida", "4200-072", "Porto", "Portugal"));

    }
}