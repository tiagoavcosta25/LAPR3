package lapr.project.controller;

import lapr.project.model.UserSession;
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
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 2));
        when(mockPServ.updateProduct("1", "Product 6969", "Description 1", 2.0d, 2.0d)).thenReturn(true);

        boolean result = updateProductController.updateProduct("1", "Product 6969", "Description 1", 2.0d, 2.0d);
        assertTrue(result);

        result = updateProductController.updateProduct("-1", "Product 6969", "Description 1", 2.0d, 2.0d);
        assertFalse(result);

        result = updateProductController.updateProduct("1", "", "Description 1", 2.0d, 2.0d);
        assertFalse(result);

        when(mockPServ.updateProduct("1", "Product 6969", "Description 1", 2.0d, 2.0d)).thenReturn(false);
        result = updateProductController.updateProduct("1", "Product 6969", "Description 1", 2.0d, 2.0d);
        assertFalse(result);

        ApplicationPOT.getInstance().clearCurrentSession();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 1));
        result = updateProductController.updateProduct("1", "Product 6969", "Description 1", 2.0d, 2.0d);
        assertFalse(result);

        ApplicationPOT.getInstance().clearCurrentSession();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 3));
        result = updateProductController.updateProduct("1", "Product 6969", "Description 1", 2.0d, 2.0d);
        assertFalse(result);

        ApplicationPOT.getInstance().clearCurrentSession();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 4));
        result = updateProductController.updateProduct("1", "Product 6969", "Description 1", 2.0d, 2.0d);
        assertFalse(result);
    }
    @Test
    void testGetProductService(){
        UpdateProductController ctrl = new UpdateProductController();
        ProductService expected = new ProductService();
        ctrl.setMoServ(expected);
        ProductService real = ctrl.getMoServ();
        assertEquals(expected, real);
    }
    @Test
    void testSetProductService(){
        UpdateProductController ctrl = new UpdateProductController();
        ProductService expected = new ProductService();
        ctrl.setMoServ(expected);
        ProductService real = ctrl.getMoServ();
        assertEquals(expected, real);
    }
}