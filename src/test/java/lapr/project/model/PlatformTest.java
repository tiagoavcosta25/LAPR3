package lapr.project.model;

import lapr.project.data.registration.UserRegistration;
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
}