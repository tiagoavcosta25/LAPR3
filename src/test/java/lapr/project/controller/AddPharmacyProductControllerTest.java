package lapr.project.controller;

import lapr.project.data.registration.ClientRegistration;
import lapr.project.data.registration.OrderRegistration;
import lapr.project.data.registration.PharmacyRegistration;
import lapr.project.data.registration.ProductRegistration;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class AddPharmacyProductControllerTest {

    @InjectMocks
    private AddPharmacyProductController addPharmacyProductController;

    @Mock
    private PharmacyRegistration mockPharmacyRegistration;

    @Mock
    private ProductRegistration mockProductRegistration;

    private boolean expectedValue;

    @BeforeEach
    void setUp() {
        this.expectedValue = true;
        this.addPharmacyProductController = new AddPharmacyProductController();
        this.mockPharmacyRegistration = Mockito.mock(PharmacyRegistration.class);
        this.mockProductRegistration = Mockito.mock(ProductRegistration.class);
        initMocks(this);
    }

    @Test
    void addPharmacyProduct() {
        System.out.println("addPharmacyProduct");

        boolean expected = true;
        boolean real = addPharmacyProductController.addPharmacyProduct(new Product(), 1);
        assertEquals(expected, real);
    }
    @Test
    void registerPharmacyProduct() {
        System.out.println("registerPharmacyProduct");
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockPharmacyRegistration.getPharmacyByManagerEmail("email3@gmail.com")).thenReturn(new Pharmacy());
        when(mockPharmacyRegistration.registerPharmacyProduct(new Pharmacy(), new Product(), 1)).thenReturn(expectedValue);

        addPharmacyProductController.setProduct(new Product());
        addPharmacyProductController.setStock(1);
        boolean result = addPharmacyProductController.registerPharmacyProduct();
        assertEquals(expectedValue, result);

        expectedValue = false;
        when(mockPharmacyRegistration.registerPharmacyProduct(new Pharmacy(), new Product(), 1)).thenReturn(expectedValue);
        result = addPharmacyProductController.registerPharmacyProduct();
        assertEquals(expectedValue, result);
    }

    @Test
    void getProducts() {
        System.out.println("getProducts");

        List<Product> expectedListProducts = new ArrayList<>(Arrays.asList(new Product()));

        when(mockProductRegistration.getProducts()).thenReturn(expectedListProducts);

        List<Product>  result = addPharmacyProductController.getProducts();
        assertEquals(expectedListProducts, result);
    }
}