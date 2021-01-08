package lapr.project.ui;

import javafx.util.Pair;
import lapr.project.model.Address;
import lapr.project.model.registration.DeliveryRegistration;

public class CalculateMostEfficientPathUI {
    public static void main(String[] args) {
        DeliveryRegistration dr = new DeliveryRegistration("jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_G21", "g21rumoAo20");
        Pair<Address,Address> pair = dr.getStartingAndDeliveryAddressByOrder(1);
        System.out.println(pair.getKey());
        System.out.println(pair.getValue());
    }
}
