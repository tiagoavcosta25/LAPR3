package lapr.project.utils;

import lapr.project.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationProductTest {

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
    }

    @Test
    void validateInputWithId() {
        assertEquals(expectedProduct, ValidationProduct.validateInputWithId(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        expectedProduct.setId(2);
        assertNotEquals(expectedProduct.getId(), ValidationProduct.validateInputWithId(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight).getId());

        assertFalse(ValidationProduct.validateInputWithId(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight).hasId(2));

        assertTrue(ValidationProduct.validateInputWithId(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight).hasId(1));

    }

    @Test
    void validateInput() {
        assertEquals(expectedProduct, ValidationProduct.validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        expectedProduct = null;
        strName = "";
        assertEquals(expectedProduct, ValidationProduct.validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        strName = "Name";
        strDescription = "";
        assertEquals(expectedProduct, ValidationProduct.validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        strDescription = "Description";
        fltUnitaryPrice = -1;
        assertEquals(expectedProduct, ValidationProduct.validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        fltUnitaryPrice = 0;
        assertEquals(expectedProduct, ValidationProduct.validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        fltUnitaryPrice = 1;
        fltUnitaryWeight = -1;
        assertEquals(expectedProduct, ValidationProduct.validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        fltUnitaryWeight = 0;
        assertEquals(expectedProduct, ValidationProduct.validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

        strName = "";
        strDescription = "";
        fltUnitaryPrice = 0;
        assertEquals(expectedProduct, ValidationProduct.validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));
    }

        @Test
    void validateId() {
        assertTrue(ValidationProduct.validateId(intId));

        intId = -1;
        assertFalse(ValidationProduct.validateId(intId));

        intId = 0;
        assertFalse(ValidationProduct.validateId(intId));
    }
}