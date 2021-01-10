package lapr.project.ui;
import lapr.project.model.Order;
import lapr.project.data.registration.OrderRegistration;

public class KnowDeliveryUI {    public static void main(String[] args) {
    OrderRegistration or = new OrderRegistration("jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_G21", "g21rumoAo20");
    Order o = or.getOrderByCourier("user6@gmail.com");
    System.out.println(o);
}
}
