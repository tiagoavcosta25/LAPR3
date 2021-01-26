package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.data.ProductDB;
import lapr.project.model.*;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class AddPharmacyProductControllerTest {

    @InjectMocks
    private AddPharmacyProductController addPharmacyProductController;

    @Mock
    private PharmacyService mockPharmacyService;

    @Mock
    private ProductService mockProductService;

    private boolean expectedValue;

    @BeforeEach
    void setUp() {
        this.expectedValue = true;
        this.addPharmacyProductController = new AddPharmacyProductController();
        this.mockPharmacyService = Mockito.mock(PharmacyService.class);
        this.mockProductService = Mockito.mock(ProductService.class);
        initMocks(this);
    }

    @Test
    void addPharmacyProduct() {
        System.out.println("addPharmacyProduct");

        when(mockPharmacyService.getPharmacy("email3@gmail.com")).thenReturn(new Pharmacy());
        boolean real = addPharmacyProductController.addPharmacyProduct("email3@gmail.com", new Product(), 1);
        assertTrue(real);

        when(mockPharmacyService.getPharmacy(null)).thenThrow(new IllegalArgumentException());
        real = addPharmacyProductController.addPharmacyProduct("email3@gmail.com", null, 0);
        assertFalse(real);
        real = addPharmacyProductController.addPharmacyProduct("email3@gmail.com", new Product(), -1);
        assertFalse(real);

        real = addPharmacyProductController.addPharmacyProduct("", new Product(), -1);
        assertFalse(real);

        real = addPharmacyProductController.addPharmacyProduct("", new Product(), 0);
        assertFalse(real);

        real = addPharmacyProductController.addPharmacyProduct("", null, -1);
        assertFalse(real);

        real = addPharmacyProductController.addPharmacyProduct("email3@gmail.com", null, -1);
        assertFalse(real);

        real = addPharmacyProductController.addPharmacyProduct("", null, 0);
        assertFalse(real);

        real = addPharmacyProductController.addPharmacyProduct("", new Product(), -1);
        assertFalse(real);
    }
    @Test
    void registerPharmacyProduct() {
        System.out.println("registerPharmacyProduct");
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockPharmacyService.getPharmacyByManagerEmail("email3@gmail.com")).thenReturn(new Pharmacy());
        when(mockPharmacyService.registerPharmacyProduct(new Pharmacy(), new Product(), 1)).thenReturn(expectedValue);

        addPharmacyProductController.setProduct(new Product());
        addPharmacyProductController.setPharmacy(new Pharmacy());
        addPharmacyProductController.setStock(1);
        boolean result = addPharmacyProductController.registerPharmacyProduct();
        assertEquals(expectedValue, result);

        expectedValue = false;
        when(mockPharmacyService.registerPharmacyProduct(new Pharmacy(), new Product(), 1)).thenReturn(expectedValue);
        result = addPharmacyProductController.registerPharmacyProduct();
        assertEquals(expectedValue, result);
    }

    @Test
    void getProducts() {
        System.out.println("getProducts");

        List<Product> expectedListProducts = new ArrayList<>(Arrays.asList(new Product()));

        when(mockProductService.getProducts()).thenReturn(expectedListProducts);

        List<Product>  result = addPharmacyProductController.getProducts();
        assertEquals(expectedListProducts, result);
    }
}