package lapr.project.controller;

import lapr.project.model.Product;
import lapr.project.model.registration.ProductRegistration;
import org.junit.jupiter.api.AfterEach;
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
    private ProductRegistration mockProductRegistration;

    private boolean expectedTrue;

    @BeforeEach
    void setUp() {
        this.expectedTrue = true;
        this.registerProductController = new RegisterProductController();
        this.mockProductRegistration = Mockito.mock(ProductRegistration.class);
        initMocks(this);
    }

    @Test
    void ensureProductRegistrationWorks() {
        System.out.println("registerProductToDB");
        Product p = new Product("Product 1", "Description 1", 2.0f, 2.0f);

        when(mockProductRegistration.addProductToDB(p)).thenReturn(expectedTrue);
        boolean result = registerProductController.registerProductToDB(p);
        assertEquals(expectedTrue, result);
    }
}