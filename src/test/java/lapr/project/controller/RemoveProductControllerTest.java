package lapr.project.controller;

import lapr.project.data.registration.ProductRegistration;
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
    private ProductRegistration mockProductRegistration;

    @BeforeEach
    void setUp() {
        this.removeProductController = new RemoveProductController();
        this.mockProductRegistration = Mockito.mock(ProductRegistration.class);
        initMocks(this);
    }

    @Test
    void ensureProductRegistrationWorks() {
        System.out.println("registerProductToDB");
        when(mockProductRegistration.removeProductFromDB(1)).thenReturn(true);
        Boolean result = removeProductController.removeProductFromDB(1);
        assertTrue(result);

        result = removeProductController.removeProductFromDB(-1);
        assertFalse(result);
    }
}