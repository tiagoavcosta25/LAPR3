package lapr.project.controller;

import lapr.project.model.Product;
import lapr.project.data.ProductDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


class RegisterProductControllerTest {

    @InjectMocks
    private RegisterProductController registerProductController;

    @Mock
    private ProductDB mockProductDB;

    @BeforeEach
    void setUp() {
        this.registerProductController = new RegisterProductController();
        this.mockProductDB = Mockito.mock(ProductDB.class);
        initMocks(this);
    }

    @Test
    void ensureProductRegistrationWorks() {
        System.out.println("registerProductToDB");
        Product p = new Product("Product 6969", "Description 1", 2.0f, 2.0f);

        when(mockProductDB.addProductToDB(p)).thenReturn(true);

        boolean result = registerProductController.registerProductToDB("Product 6969", "Description 1", 2.0f, 2.0f);
        assertTrue(result);

        result = registerProductController.registerProductToDB("", "Description 1", 2.0f, 2.0f);
        assertFalse(result);

        result = registerProductController.registerProductToDB("Product 6868", "Description 1", -2.0f, 2.0f);
        assertFalse(result);

        when(mockProductDB.addProductToDB(p)).thenReturn(false);
        result = registerProductController.registerProductToDB("Product 6969", "Description 1", 2.0f, 2.0f);
        assertFalse(result);

    }
}