package lapr.project.model.service;

import lapr.project.data.ProductDB;
import lapr.project.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ProductServiceTest {

    @InjectMocks
    private ProductService pServ;

    @Mock
    private ProductDB mockPDB;

    @BeforeEach
    void setUp() {
        this.pServ = new ProductService();
        this.mockPDB = Mockito.mock(ProductDB.class);
        initMocks(this);
    }

    @Test
    void ensureProductRegistrationWorks() {
        System.out.println("registerProduct");
        Product p = new Product("Product 6969", "Description 1", 2.0f, 2.0f);

        when(mockPDB.addProductToDB(p)).thenReturn(true);

        boolean result = pServ.registerProduct("Product 6969", "Description 1", 2.0f, 2.0f);
        assertTrue(result);

        result = pServ.registerProduct("", "Description 1", 2.0f, 2.0f);
        assertFalse(result);

        result = pServ.registerProduct("Product 6868", "Description 1", -2.0f, 2.0f);
        assertFalse(result);

        when(mockPDB.addProductToDB(p)).thenReturn(false);
        result = pServ.registerProduct("Product 6969", "Description 1", 2.0f, 2.0f);
        assertFalse(result);
    }

    @Test
    void ensureUpdateProductWorks() {
        System.out.println("updateProduct");
        when(mockPDB.updateProductFromDB(1, "Product 6969", "Description 1", 2.0f, 2.0f)).thenReturn(true);

        boolean result = pServ.updateProduct(1, "Product 6969", "Description 1", 2.0f, 2.0f);
        assertTrue(result);

        result = pServ.updateProduct(-1, "Product 6969", "Description 1", 2.0f, 2.0f);
        assertFalse(result);

        result = pServ.updateProduct(1, "", "Description 1", 2.0f, 2.0f);
        assertFalse(result);

        when(mockPDB.updateProductFromDB(1, "Product 6969", "Description 1", 2.0f, 2.0f)).thenReturn(false);
        result = pServ.updateProduct(1, "Product 6969", "Description 1", 2.0f, 2.0f);
        assertFalse(result);
    }

    @Test
    void ensureRemoveProductWorks() {
        System.out.println("removeProduct");
        when(mockPDB.removeProductFromDB(1)).thenReturn(true);
        Boolean result = pServ.removeProduct(1);
        assertTrue(result);

        result = pServ.removeProduct(-1);
        assertFalse(result);

        result = pServ.removeProduct(0);
        assertFalse(result);

        when(mockPDB.removeProductFromDB(1)).thenReturn(false);
        result = mockPDB.removeProductFromDB(1);
        assertFalse(result);
    }

    @Test
    void ensureGetProduct() {
        System.out.println("getProduct");
        Product expectedProduct = new Product();
        when(mockPDB.getProductFromBD(1)).thenReturn(expectedProduct);

        Product result = pServ.getProduct(1);
        assertEquals(expectedProduct, result);

        expectedProduct = null;

        result = pServ.getProduct(-1);
        assertEquals(expectedProduct, result);
    }
}