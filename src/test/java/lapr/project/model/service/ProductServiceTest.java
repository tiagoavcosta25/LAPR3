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

    private float fltUnitaryPrice;

    private float fltUnitaryWeight;

    private Product expectedProduct;

    @BeforeEach
    void setUp() {

        intId = 1;
        strName = "Name";
        strDescription = "Description";
        fltUnitaryPrice = 1;
        fltUnitaryWeight = 1;
        expectedProduct = new Product(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);

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
        boolean result = pServ.removeProduct(1);
        assertTrue(result);

        result = pServ.removeProduct(-1);
        assertFalse(result);

        result = pServ.removeProduct(0);
        assertFalse(result);

        when(mockPDB.removeProductFromDB(1)).thenReturn(false);
        result = pServ.removeProduct(1);
        assertFalse(result);
    }

    @Test
    void ensureGetProduct() {
        System.out.println("getProduct");
        when(mockPDB.getProductFromBD(1)).thenReturn(expectedProduct);

        Product result = pServ.getProduct(1);
        assertEquals(expectedProduct, result);

        expectedProduct = null;

        result = pServ.getProduct(-1);
        assertEquals(expectedProduct, result);
    }

    @Test
    void validateInputWithId() {
        assertTrue(pServ.validateInputWithId(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        intId = 0;
        assertFalse(pServ.validateInputWithId(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        intId = -1;
        assertFalse(pServ.validateInputWithId(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        intId = 0;
        strName = "Name";
        strDescription = "";
        assertFalse(pServ.validateInputWithId(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        strDescription = "Description";
        fltUnitaryPrice = -1;
        assertFalse(pServ.validateInputWithId(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        fltUnitaryPrice = 0;
        assertFalse(pServ.validateInputWithId(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        fltUnitaryPrice = 1;
        fltUnitaryWeight = -1;
        assertFalse(pServ.validateInputWithId(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        fltUnitaryWeight = 0;
        assertFalse(pServ.validateInputWithId(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        strName = "";
        strDescription = "";
        fltUnitaryPrice = 0;
        assertFalse(pServ.validateInputWithId(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));
}

    @Test
    void validateInput() {
        assertTrue(pServ.validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        strName = "";
        assertFalse(pServ.validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        strName = "Name";
        strDescription = "";
        assertFalse(pServ.validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        strDescription = "Description";
        fltUnitaryPrice = -1;
        assertFalse(pServ.validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        fltUnitaryPrice = 0;
        assertFalse(pServ.validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        fltUnitaryPrice = 1;
        fltUnitaryWeight = -1;
        assertFalse(pServ.validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        fltUnitaryWeight = 0;
        assertFalse(pServ.validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        strName = "";
        strDescription = "";
        fltUnitaryPrice = 0;
        assertFalse(pServ.validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));
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