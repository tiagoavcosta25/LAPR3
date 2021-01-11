package lapr.project.controller;

import lapr.project.data.registration.ProductRegistration;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


class UpdateProductControllerTest {

    @InjectMocks
    private UpdateProductController updateProductController;

    @Mock
    private ProductRegistration mockProductRegistration;

    @BeforeEach
    void setUp() {
        this.updateProductController = new UpdateProductController();
        this.mockProductRegistration = Mockito.mock(ProductRegistration.class);
        initMocks(this);
    }

    @Test
    void ensureProductRegistrationWorks() {
        System.out.println("registerProductToDB");
        when(mockProductRegistration.updateProductFromDB(1, "Product 6969", "Description 1", 2.0f, 2.0f)).thenReturn(true);
        boolean result = updateProductController.updateProduct(1, "Product 6969", "Description 1", 2.0f, 2.0f);
        Assert.assertTrue(result);
    }
}