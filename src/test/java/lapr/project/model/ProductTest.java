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
        Product product = new Product(1, "Produto 1", "Descrição 1", 2.0f, 2.0f);
        Product product2 = new Product("Produto 1", "Descrição 1", 2.0f, 2.0f);
        Product productCopy = new Product(1, "Produto 1", "Descrição 1", 2.0f, 2.0f);
        Product productDif = new Product(2, "Produto 2", "Descrição 2", 3.0f, 3.0f);
        assertNotNull(product2);
        assertNotEquals(null, product);
        assertEquals(product, productCopy);
        assertEquals(product, product);
        assertNotEquals(product, new Order());
        assertNotEquals(product, productDif);
        assertTrue(product.hashCode() == productCopy.hashCode());
        assertFalse(product.hashCode() == productDif.hashCode());
        assertEquals(1, product.getId());
        assertEquals("Produto 1", product.getName());
        assertEquals("Descrição 1", product.getDescription());
        assertEquals(2.0f, product.getUnitaryPrice());
        assertEquals(2.0f, product.getUnitaryWeight());
    }
}