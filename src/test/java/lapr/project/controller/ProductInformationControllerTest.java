package lapr.project.controller;

import lapr.project.model.Product;
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


class ProductInformationControllerTest {

    @InjectMocks
    private ProductInformationController productInformationController;

    @Mock
    private ProductService mockPServ;

    @BeforeEach
    void setUp() {
        this.productInformationController = new ProductInformationController();
        this.mockPServ = Mockito.mock(ProductService.class);
        initMocks(this);
    }

    @Test
    void ensureGetProductWorks() {
        System.out.println("getProduct");
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 2));
        Product expectedProduct = new Product();
        when(mockPServ.getProduct("1")).thenReturn(expectedProduct);

        Product result = productInformationController.getProduct("1");
        assertEquals(expectedProduct, result);

        expectedProduct = null;

        result = productInformationController.getProduct("-1");
        assertEquals(expectedProduct, result);

        ApplicationPOT.getInstance().clearCurrentSession();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 1));
        result = productInformationController.getProduct("-1");
        assertEquals(expectedProduct, result);

        ApplicationPOT.getInstance().clearCurrentSession();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 3));
        result = productInformationController.getProduct("-1");
        assertEquals(expectedProduct, result);

        ApplicationPOT.getInstance().clearCurrentSession();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 4));
        result = productInformationController.getProduct("-1");
        assertEquals(expectedProduct, result);
    }
    @Test
    void testGetProductService(){
        ProductInformationController ctrl = new ProductInformationController();
        ProductService expected = new ProductService();
        ctrl.setMoServ(expected);
        ProductService real = ctrl.getMoServ();
        assertEquals(expected, real);
    }
    @Test
    void testSetProductService(){
        ProductInformationController ctrl = new ProductInformationController();
        ProductService expected = new ProductService();
        ctrl.setMoServ(expected);
        ProductService real = ctrl.getMoServ();
        assertEquals(expected, real);
    }
}