package lapr.project.model.registration;

import lapr.project.controller.RegisterProductController;
import lapr.project.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ProductRegistrationTest {

    @Mock
    private ProductRegistration mockProductRegistration;

    private boolean expectedTrue;

    @BeforeEach
    void setUp() {
        this.expectedTrue = true;
        this.mockProductRegistration = Mockito.mock(ProductRegistration.class);
        initMocks(this);
    }

    @Test
    void ensureProductRegistrationWorks() {
        System.out.println("addProductToDB");
        Product p = new Product("Product 6969", "Description 1", 2.0f, 2.0f);

        when(mockProductRegistration.addProductToDB(p)).thenReturn(expectedTrue);
        boolean result = mockProductRegistration.addProductToDB(p);
        assertEquals(expectedTrue, result);
    }
}