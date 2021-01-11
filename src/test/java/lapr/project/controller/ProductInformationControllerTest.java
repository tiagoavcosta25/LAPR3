package lapr.project.controller;

import lapr.project.model.Product;
import lapr.project.data.registration.ProductRegistration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


class ProductInformationControllerTest {

    @InjectMocks
    private ProductInformationController productInformationController;

    @Mock
    private ProductRegistration mockProductRegistration;

    private Product expectedProduct;

    @BeforeEach
    void setUp() {
        this.expectedProduct = new Product();
        this.productInformationController = new ProductInformationController();
        this.mockProductRegistration = Mockito.mock(ProductRegistration.class);
        initMocks(this);
    }

    @Test
    void ensureProductRegistrationWorks() {
        System.out.println("registerProductToDB");
        when(mockProductRegistration.getProductFromBD(1)).thenReturn(expectedProduct);
        Product result = productInformationController.getProductFromDB(1);
        assertEquals(expectedProduct, result);
    }
}