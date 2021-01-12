package lapr.project.controller;

import lapr.project.data.ProductDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


class UpdateProductControllerTest {

    @InjectMocks
    private UpdateProductController updateProductController;

    @Mock
    private ProductDB mockProductDB;

    @BeforeEach
    void setUp() {
        this.updateProductController = new UpdateProductController("","","");
        this.mockProductDB = Mockito.mock(ProductDB.class);
        initMocks(this);
    }

    @Test
    void ensureUpdateProductWorks() {
        System.out.println("updateProductFromDB");
        when(mockProductDB.updateProductFromDB(1, "Product 6969", "Description 1", 2.0f, 2.0f)).thenReturn(true);

        boolean result = updateProductController.updateProduct(1, "Product 6969", "Description 1", 2.0f, 2.0f);
        assertTrue(result);

        result = updateProductController.updateProduct(-1, "Product 6969", "Description 1", 2.0f, 2.0f);
        assertFalse(result);

        result = updateProductController.updateProduct(1, "", "Description 1", 2.0f, 2.0f);
        assertFalse(result);

        when(mockProductDB.updateProductFromDB(1, "Product 6969", "Description 1", 2.0f, 2.0f)).thenReturn(false);
        result = updateProductController.updateProduct(1, "Product 6969", "Description 1", 2.0f, 2.0f);
        assertFalse(result);
    }
}