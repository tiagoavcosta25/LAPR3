package lapr.project.model;

import lapr.project.data.registration.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlatformTest {

    Platform platform = new Platform();

    @Test
    void getUserReg() {
        UserRegistration result = platform.getUserReg();
        assertNotEquals(null, result);
        assertNotEquals(result, null);
    }

    @Test
    void getClientReg() {
        ClientRegistration result = platform.getClientReg();
        assertNotEquals(null, result);
        assertNotEquals(result, null);
    }

    @Test
    void getCourReg() {
        CourierRegistration result = platform.getCourReg();
        assertNotEquals(null, result);
        assertNotEquals(result, null);
    }

    @Test
    void getOrderReg() {
        OrderRegistration result = platform.getOrderReg();
        assertNotEquals(null, result);
        assertNotEquals(result, null);
    }

    @Test
    void getInvoiceReg() {
        InvoiceRegistration result = platform.getInvoiceReg();
        assertNotEquals(null, result);
        assertNotEquals(result, null);
    }

    @Test
    void getScooterReg() {
        ScooterRegistration result = platform.getScooterReg();
        assertNotEquals(null, result);
        assertNotEquals(result, null);
    }

    @Test
    void getDelReg() {
        DeliveryRegistration result = platform.getDelReg();
        assertNotEquals(null, result);
        assertNotEquals(result, null);
    }

    @Test
    void getProductReg() {
        ProductRegistration result = platform.getProductReg();
        assertNotEquals(null, result);
        assertNotEquals(result, null);
    }


}