package lapr.project.ui;

import lapr.project.model.registration.DeliveryRegistration;

public class MaxPayloadUI {
    public static void main(String[] args) {
        DeliveryRegistration reg = new DeliveryRegistration("jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_G21", "g21rumoAo20");
        String email = "email6@gmail.com";
        reg.getMaxPayload(email);
    }
}
