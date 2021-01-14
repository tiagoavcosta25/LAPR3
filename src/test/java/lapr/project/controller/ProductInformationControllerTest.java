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
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 2));
        this.productInformationController = new ProductInformationController();
        this.mockPServ = Mockito.mock(ProductService.class);
        initMocks(this);
    }

    @Test
    void ensureGetProductWorks() {
        System.out.println("getProduct");
        Product expectedProduct = new Product();
        when(mockPServ.getProduct(1)).thenReturn(expectedProduct);

        Product result = productInformationController.getProduct(1);
        assertEquals(expectedProduct, result);

        expectedProduct = null;

        result = productInformationController.getProduct(-1);
        assertEquals(expectedProduct, result);
    }
}