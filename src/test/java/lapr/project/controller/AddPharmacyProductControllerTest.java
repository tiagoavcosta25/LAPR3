package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.data.ProductDB;
import lapr.project.model.*;
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
    private PharmacyDB mockPharmacyDB;

    @Mock
    private ProductDB mockProductDB;

    private boolean expectedValue;

    @BeforeEach
    void setUp() {
        this.expectedValue = true;
        this.addPharmacyProductController = new AddPharmacyProductController();
        this.mockPharmacyDB = Mockito.mock(PharmacyDB.class);
        this.mockProductDB = Mockito.mock(ProductDB.class);
        initMocks(this);
    }

    @Test
    void addPharmacyProduct() {
        System.out.println("addPharmacyProduct");

        boolean expected = true;
        boolean real = addPharmacyProductController.addPharmacyProduct(new Product(), 1);
        assertEquals(expected, real);

        expected = true;
        real = addPharmacyProductController.addPharmacyProduct(new Product(), 0);
        assertEquals(expected, real);

        expected = false;
        real = addPharmacyProductController.addPharmacyProduct(new Product(), -1);
        assertEquals(expected, real);

        expected = false;
        real = addPharmacyProductController.addPharmacyProduct(null, -1);
        assertEquals(expected, real);

        expected = false;
        real = addPharmacyProductController.addPharmacyProduct(null, 0);
        assertEquals(expected, real);
    }
    @Test
    void registerPharmacyProduct() {
        System.out.println("registerPharmacyProduct");
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockPharmacyDB.getPharmacyByManagerEmail("email3@gmail.com")).thenReturn(new Pharmacy());
        when(mockPharmacyDB.registerPharmacyProduct(new Pharmacy(), new Product(), 1)).thenReturn(expectedValue);

        addPharmacyProductController.setProduct(new Product());
        addPharmacyProductController.setStock(1);
        boolean result = addPharmacyProductController.registerPharmacyProduct();
        assertEquals(expectedValue, result);

        expectedValue = false;
        when(mockPharmacyDB.registerPharmacyProduct(new Pharmacy(), new Product(), 1)).thenReturn(expectedValue);
        result = addPharmacyProductController.registerPharmacyProduct();
        assertEquals(expectedValue, result);
    }

    @Test
    void getProducts() {
        System.out.println("getProducts");

        List<Product> expectedListProducts = new ArrayList<>(Arrays.asList(new Product()));

        when(mockProductDB.getProducts()).thenReturn(expectedListProducts);

        List<Product>  result = addPharmacyProductController.getProducts();
        assertEquals(expectedListProducts, result);
    }
}