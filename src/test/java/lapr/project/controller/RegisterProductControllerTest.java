package lapr.project.controller;

import lapr.project.model.Product;
import lapr.project.model.service.ProductService;
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
    private ProductService mockPServ;

    @BeforeEach
    void setUp() {
        this.registerProductController = new RegisterProductController();
        this.mockPServ = Mockito.mock(ProductService.class);
        initMocks(this);
    }

    @Test
    void ensureProductRegistrationWorks() {
        System.out.println("registerProduct");
        Product p = new Product("Product 6969", "Description 1", 2.0f, 2.0f);

        when(mockPServ.registerProduct("Product 6969", "Description 1", 2.0f, 2.0f)).thenReturn(true);

        boolean result = registerProductController.registerProduct("Product 6969", "Description 1", 2.0f, 2.0f);
        assertTrue(result);

        result = registerProductController.registerProduct("", "Description 1", 2.0f, 2.0f);
        assertFalse(result);

        result = registerProductController.registerProduct("Product 6868", "Description 1", -2.0f, 2.0f);
        assertFalse(result);

        when(mockPServ.registerProduct("Product 6969", "Description 1", 2.0f, 2.0f)).thenReturn(false);
        result = registerProductController.registerProduct("Product 6969", "Description 1", 2.0f, 2.0f);
        assertFalse(result);

    }
}