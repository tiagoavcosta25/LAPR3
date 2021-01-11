package lapr.project.controller;

import lapr.project.data.registration.PharmacyRegistration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class RegisterPharmacyControllerTest {
    @InjectMocks
    private RegisterPharmacyController registerPharmacyController;

    @Mock
    private PharmacyRegistration mockPharmacyRegistration;

    private boolean expectedTrue;
    @BeforeEach
    void setUp() {
        this.expectedTrue = true;
        this.registerPharmacyController = new RegisterPharmacyController();
        this.mockPharmacyRegistration = Mockito.mock(PharmacyRegistration.class);
        initMocks(this);
    }

    @Test
    void newPharmacy() {
        this.registerPharmacyController.newPharmacy("Joel","joel123@gmail.com","12345",123456789,"Farmacia 1",41.014152,-8.218524,"Rua2","2ºesq","4460-222","Porto", "Portugal");

    }

    @Test
    void registerPharmacy() {
    }
}