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


class RegisterProductControllerTest {

    @InjectMocks
    private RegisterProductController registerProductController;

    @Mock
    private ProductService mockPServ;

    @BeforeEach
    void setUp() {
        this.registerProductController = new RegisterProductController();
        this.mockPServ = Mockito.mock(ProductService.class);
        initMocks(this);
    }

    @Test
    void ensureProductRegistrationWorks() {
        System.out.println("registerProduct");
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 2));
        when(mockPServ.registerProduct("Product 6969", "Description 1", 2.0d, 2.0d)).thenReturn(true);

        boolean result = registerProductController.registerProduct("Product 6969", "Description 1", 2.0d, 2.0d);
        assertTrue(result);

        result = registerProductController.registerProduct("", "Description 1", 2.0d, 2.0d);
        assertFalse(result);

        result = registerProductController.registerProduct("Product 6868", "Description 1", -2.0d, 2.0d);
        assertFalse(result);

        when(mockPServ.registerProduct("Product 6969", "Description 1", 2.0d, 2.0d)).thenReturn(false);
        result = registerProductController.registerProduct("Product 6969", "Description 1", 2.0d, 2.0d);
        assertFalse(result);

        ApplicationPOT.getInstance().clearCurrentSession();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 1));
        result = registerProductController.registerProduct("", "Description 1", 2.0d, 2.0d);
        assertFalse(result);

        ApplicationPOT.getInstance().clearCurrentSession();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 3));
        result = registerProductController.registerProduct("", "Description 1", 2.0d, 2.0d);
        assertFalse(result);

        ApplicationPOT.getInstance().clearCurrentSession();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 4));
        result = registerProductController.registerProduct("", "Description 1", 2.0d, 2.0d);
        assertFalse(result);
    }
    @Test
    void testGetProductService(){
        RegisterProductController ctrl = new RegisterProductController();
        ProductService expected = new ProductService();
        ctrl.setMoServ(expected);
        ProductService real = ctrl.getMoServ();
        assertEquals(expected, real);
    }
    @Test
    void testSetProductService(){
        RegisterProductController ctrl = new RegisterProductController();
        ProductService expected = new ProductService();
        ctrl.setMoServ(expected);
        ProductService real = ctrl.getMoServ();
        assertEquals(expected, real);
    }
}