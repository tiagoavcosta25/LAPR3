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


}