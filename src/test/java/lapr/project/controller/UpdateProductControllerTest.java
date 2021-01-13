package lapr.project.controller;

import lapr.project.model.service.ProductService;
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
    private ProductService mockPServ;

    @BeforeEach
    void setUp() {
        this.updateProductController = new UpdateProductController();
        this.mockPServ = Mockito.mock(ProductService.class);
        initMocks(this);
    }

    @Test
    void ensureUpdateProductWorks() {
        System.out.println("updateProduct");
        when(mockPServ.updateProduct(1, "Product 6969", "Description 1", 2.0f, 2.0f)).thenReturn(true);

        boolean result = updateProductController.updateProduct(1, "Product 6969", "Description 1", 2.0f, 2.0f);
        assertTrue(result);

        result = updateProductController.updateProduct(-1, "Product 6969", "Description 1", 2.0f, 2.0f);
        assertFalse(result);

        result = updateProductController.updateProduct(1, "", "Description 1", 2.0f, 2.0f);
        assertFalse(result);

        when(mockPServ.updateProduct(1, "Product 6969", "Description 1", 2.0f, 2.0f)).thenReturn(false);
        result = updateProductController.updateProduct(1, "Product 6969", "Description 1", 2.0f, 2.0f);
        assertFalse(result);
    }
}