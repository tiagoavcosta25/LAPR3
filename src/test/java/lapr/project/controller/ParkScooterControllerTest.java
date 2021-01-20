package lapr.project.controller;

import lapr.project.model.UserSession;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkScooterControllerTest {
    ParkScooterController oParkScooterController;

    public ParkScooterControllerTest() {
        this.oParkScooterController = new ParkScooterController();
    }
    //TODO é preciso dar fix, este método envolve os ficheiros recebidos
/*
    @Test
    void parkScooter() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email123@gmail.com", 3));
        boolean result = oParkScooterController.parkScooter(10);
        assertTrue(result);
    }*/

    @Test
    void parkScooter2() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email123@gmail.com", 2));
        boolean result = oParkScooterController.parkScooter(10);
        assertFalse(result);
    }
}