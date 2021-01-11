package lapr.project.controller;

import lapr.project.model.Product;
import lapr.project.data.registration.ProductRegistration;
import org.junit.Assert;
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

    @BeforeEach
    void setUp() {
        this.registerProductController = new RegisterProductController();
        this.mockProductRegistration = Mockito.mock(ProductRegistration.class);
        initMocks(this);
    }

    @Test
    void ensureProductRegistrationWorks() {
        System.out.println("registerProductToDB");
        Product p = new Product("Product 6969", "Description 1", 2.0f, 2.0f);

        when(mockProductRegistration.addProductToDB(p)).thenReturn(true);

        boolean result = registerProductController.registerProductToDB("Product 6969", "Description 1", 2.0f, 2.0f);
        Assert.assertTrue(result);

        result = registerProductController.registerProductToDB("", "Description 1", 2.0f, 2.0f);
        Assert.assertFalse(result);

        result = registerProductController.registerProductToDB("Product 6868", "Description 1", -2.0f, 2.0f);
        Assert.assertFalse(result);

    }
}