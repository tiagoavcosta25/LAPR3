package lapr.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testClass() {
        System.out.println("equals");
        Product product = new Product(1, "Produto 1", "Descrição 1", 2.0d, 2.0d);
        Product product2 = new Product("Produto 1", "Descrição 1", 2.0d, 2.0d);
        Product productCopy = new Product(1, "Produto 1", "Descrição 1", 2.0d, 2.0d);
        Product productDif = new Product(2, "Produto 2", "Descrição 2", 3.0d, 3.0d);
        assertNotNull(product2);
        assertNotEquals(null, product);
        assertNotEquals(product, null);
        assertEquals(product, productCopy);
        assertEquals(product, product);
        assertNotEquals(product, new Order());
        assertNotEquals(product, productDif);
        assertTrue(product.hashCode() == productCopy.hashCode());
        assertFalse(product.hashCode() == productDif.hashCode());
        assertEquals(1, product.getId());
        assertEquals("Produto 1", product.getName());
        assertEquals("Descrição 1", product.getDescription());
        assertEquals(2.0d, product.getUnitaryPrice());
        assertEquals(2.0d, product.getUnitaryWeight());

        product.setName("");
        assertNotEquals(product, productCopy);

        product.setName("Produto 1");
        product.setDescription("");
        assertNotEquals(product, productCopy);

        product.setDescription("Descrição 1");
        product.setUnitaryPrice(0d);
        assertNotEquals(product, productCopy);

        product.setUnitaryPrice(2.0d);
        product.setUnitaryWeight(0d);
        assertNotEquals(product, productCopy);


        int expResult = -1;
        int result = product.compareTo(productDif);
        assertEquals(expResult, result);

        expResult = 1;
        result = productDif.compareTo(product);
        assertEquals(expResult, result);

        expResult = 0;
        result = product.compareTo(productCopy);
        assertEquals(expResult, result);

        String expResultString = "Product{m_intId=1, m_strName='Produto 1', m_strDescription='Descrição 1', m_fltUnitaryPrice=2.0, m_fltUnitaryWeight=0.0}";
        String resultString = product.toString();
        assertEquals(expResultString, resultString);

        boolean resultbool = product.hasId(1);
        assertTrue(resultbool);

        resultbool = product.hasId(2);
        assertFalse(resultbool);
    }
}