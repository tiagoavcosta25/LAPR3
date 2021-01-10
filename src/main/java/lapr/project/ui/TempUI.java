package lapr.project.ui;

import lapr.project.model.Address;
import lapr.project.model.Path;
import lapr.project.data.registration.DeliveryRegistration;

import java.util.List;

public class TempUI {
    public static void main(String[] args) {
        DeliveryRegistration dr = new DeliveryRegistration("jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_G21", "g21rumoAo20");
        List<Path> list =  dr.getAllPaths();
        for(Path p : list) System.out.println(p.getName());

        List<Address> list2 = dr.getAllAddresses();
        for(Address a : list2) System.out.println(a.getStreetName());


        dr.createGraph();
        System.out.println(dr.getM_graph().toString());
    }
}
