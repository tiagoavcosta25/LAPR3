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

    private int intId;

    private String strName;

    private String strDescription;

    private Double dblUnitaryPrice;

    private Double dblUnitaryWeight;

    private Product expectedProduct;

    @BeforeEach
    void setUp() {

        intId = 1;
        strName = "Name";
        strDescription = "Description";
        dblUnitaryPrice = 1d;
        dblUnitaryWeight = 1d;
        expectedProduct = new Product(intId, strName, strDescription, dblUnitaryPrice, dblUnitaryWeight);

        this.pServ = new ProductService();
        this.mockPDB = Mockito.mock(ProductDB.class);
        initMocks(this);
    }

    @Test
    void ensureProductRegistrationWorks() {
        System.out.println("registerProduct");
        Product p = new Product("Product 6969", "Description 1", 2.0d, 2.0d);

        when(mockPDB.addProductToDB(p)).thenReturn(true);

        boolean result = pServ.registerProduct("Product 6969", "Description 1", 2.0d, 2.0d);
        assertTrue(result);

        result = pServ.registerProduct("", "Description 1", 2.0d, 2.0d);
        assertFalse(result);

        result = pServ.registerProduct("Product 6868", "Description 1", -2.0d, 2.0d);
        assertFalse(result);

        when(mockPDB.addProductToDB(p)).thenReturn(false);
        result = pServ.registerProduct("Product 6969", "Description 1", 2.0d, 2.0d);
        assertFalse(result);
    }

    @Test
    void ensureUpdateProductWorks() {
        System.out.println("updateProduct");
        when(mockPDB.updateProductFromDB("1", "Product 6969", "Description 1", 2.0d, 2.0d)).thenReturn(true);

        boolean result = pServ.updateProduct("1", "Product 6969", "Description 1", 2.0d, 2.0d);
        assertFalse(result);

        result = pServ.updateProduct("-1", "Product 6969", "Description 1", 2.0d, 2.0d);
        assertFalse(result);

        result = pServ.updateProduct("1", "", "Description 1", 2.0d, 2.0d);
        assertFalse(result);

        when(mockPDB.updateProductFromDB("1", "Product 6969", "Description 1", 2.0d, 2.0d)).thenReturn(false);
        result = pServ.updateProduct("1", "Product 6969", "Description 1", 2.0d, 2.0d);
        assertFalse(result);
    }

    @Test
    void ensureRemoveProductWorks() {
        System.out.println("removeProduct");
        when(mockPDB.removeProductFromDB("1")).thenReturn(true);
        boolean result = pServ.removeProduct("1");
        assertTrue(result);

        result = pServ.removeProduct("-1");
        assertFalse(result);

        result = pServ.removeProduct("0");
        assertFalse(result);

        when(mockPDB.removeProductFromDB("1")).thenReturn(false);
        result = pServ.removeProduct("1");
        assertFalse(result);
    }

    @Test
    void ensureGetProduct() {
        System.out.println("getProduct");
        when(mockPDB.getProductFromBD("1")).thenReturn(expectedProduct);

        Product result = pServ.getProduct("1");
        assertEquals(expectedProduct, result);

        expectedProduct = null;

        result = pServ.getProduct("-1");
        assertEquals(expectedProduct, result);
    }

    @Test
    void validateInput() {
        assertTrue(pServ.validateInput(strName, strDescription, dblUnitaryPrice, dblUnitaryWeight));

        strName = "";
        assertFalse(pServ.validateInput(strName, strDescription, dblUnitaryPrice, dblUnitaryWeight));

        strName = "Name";
        strDescription = "";
        assertFalse(pServ.validateInput(strName, strDescription, dblUnitaryPrice, dblUnitaryWeight));

        strDescription = "Description";
        dblUnitaryPrice = -1d;
        assertFalse(pServ.validateInput(strName, strDescription, dblUnitaryPrice, dblUnitaryWeight));

        dblUnitaryPrice = 0d;
        assertFalse(pServ.validateInput(strName, strDescription, dblUnitaryPrice, dblUnitaryWeight));

        dblUnitaryPrice = 1d;
        dblUnitaryWeight = -1d;
        assertFalse(pServ.validateInput(strName, strDescription, dblUnitaryPrice, dblUnitaryWeight));

        dblUnitaryWeight = 0d;
        assertFalse(pServ.validateInput(strName, strDescription, dblUnitaryPrice, dblUnitaryWeight));

        strName = "";
        strDescription = "";
        dblUnitaryPrice = 0d;
        assertFalse(pServ.validateInput(strName, strDescription, dblUnitaryPrice, dblUnitaryWeight));
    }

    @Test
    void validateId() {
        assertTrue(pServ.validateId(intId));

        intId = -1;
        assertFalse(pServ.validateId(intId));

        intId = 0;
        assertFalse(pServ.validateId(intId));
    }
}