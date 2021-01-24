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


class RemoveProductControllerTest {

    @InjectMocks
    private RemoveProductController removeProductController;

    @Mock
    private ProductService mockPServ;

    @BeforeEach
    void setUp() {
        this.removeProductController = new RemoveProductController();
        this.mockPServ = Mockito.mock(ProductService.class);
        initMocks(this);
    }

    @Test
    void ensureRemoveProductWorks() {
        System.out.println("removeProduct");
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 2));
        when(mockPServ.removeProduct("1")).thenReturn(true);
        Boolean result = removeProductController.removeProductFromDB("1");
        assertTrue(result);

        result = removeProductController.removeProductFromDB("-1");
        assertFalse(result);

        result = removeProductController.removeProductFromDB("0");
        assertFalse(result);

        when(mockPServ.removeProduct("1")).thenReturn(false);
        result = removeProductController.removeProductFromDB("1");
        assertFalse(result);

        ApplicationPOT.getInstance().clearCurrentSession();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 1));
        result = removeProductController.removeProductFromDB("1");
        assertFalse(result);

        ApplicationPOT.getInstance().clearCurrentSession();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 3));
        result = removeProductController.removeProductFromDB("1");
        assertFalse(result);

        ApplicationPOT.getInstance().clearCurrentSession();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 4));
        result = removeProductController.removeProductFromDB("1");
        assertFalse(result);
    }
}