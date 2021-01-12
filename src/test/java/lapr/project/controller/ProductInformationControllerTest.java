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


class ProductInformationControllerTest {

    @InjectMocks
    private ProductInformationController productInformationController;

    @Mock
    private ProductDB mockProductDB;

    private Product expectedProduct;

    @BeforeEach
    void setUp() {
        this.expectedProduct = new Product();
        this.productInformationController = new ProductInformationController("","","");
        this.mockProductDB = Mockito.mock(ProductDB.class);
        initMocks(this);
    }

    @Test
    void ensureProductRegistrationWorks() {
        System.out.println("registerProductToDB");
        when(mockProductDB.getProductFromBD(1)).thenReturn(expectedProduct);

        Product result = productInformationController.getProductFromDB(1);
        assertEquals(expectedProduct, result);

        expectedProduct = null;

        result = productInformationController.getProductFromDB(-1);
        assertEquals(expectedProduct, result);
    }

    @Test
    void verifyProductIdTest() {
        System.out.println("verifyProductId");

        boolean result = this.productInformationController.verifyProductId(1);
        assertTrue(result);

        result = this.productInformationController.verifyProductId(0);
        assertFalse(result);

        result = this.productInformationController.verifyProductId(-1);
        assertFalse(result);
    }
}