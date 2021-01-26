package lapr.project.controller;

import lapr.project.model.Order;
import lapr.project.model.Product;
import lapr.project.model.service.OrderService;
import lapr.project.utils.EmailSender;
import lapr.project.utils.WriteFile;

import java.util.Map;

/**
 * Notify and Remove Controller.
 * <p>
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 *
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class NotifyAndRemoveController {

    /**
     * Order Service class
     */
    private OrderService moOrderService;

    /**
     * MakeAPharmacyTransferController class
     */
    MakeAPharmacyTransferController ctrl;

    /**
     * A constructor of NotifyAndRemoveController that initiates the Order Service
     * and MakeAPharmacyTransferController.
     */
    public NotifyAndRemoveController() {
        this.moOrderService = new OrderService();
        this.ctrl = new MakeAPharmacyTransferController();
    }

    /**
     * Recieves an order instance. The method, gets the list of products that the Pharmacy does not have
     * and its quantity. If the list is null then there is no products in any pharmacy and the methods returns false.
     * If the list is empty then then the pharmacy has the products and right quatity and returns true.
     * If the list contains any products, then it asks a pharmacy for that product and returns false.
     *
     * @param order Order instance
     * @return true or false depending on the situation
     */
    public boolean notifyAndRemove(Order order) {
        Map<Product, Integer> lstProdcuts = moOrderService.notifyAndRemove(order);
        if (lstProdcuts == null) {
            String strBody = "Dear costumer, I'm sorry to inform you but some of the products: \n"
                    + order.getProducts().toString() + "\n are out of Stock in every Pharmacy";

            EmailSender.sendEmail(order.getClient().getEmail(), "Unsuccessful Order", strBody);

            WriteFile.write("UnsuccessfulOrder_" + order.getId(), strBody);
            return false;

        } else {
            if (lstProdcuts.isEmpty()) {
                return true;
            } else {
                for (Map.Entry<Product, Integer> entry : lstProdcuts.entrySet()) {
                    ctrl.getStockFromAnotherPharamacy(order, entry.getKey(), entry.getValue());
                }

                String strBody = "Dear costumer, I'm sorry to inform you but the products: \n"
                        + lstProdcuts.toString() + "\n are out of Stock in our Pharmacy. We will send it to you as soon as possible from another one of our providers.";

                EmailSender.sendEmail(order.getClient().getEmail(), "Order In Transit", strBody);

                WriteFile.write("OrderInTransit_" + order.getId(), strBody);
                return false;
            }

        }
    }

    /**
     * Returns the Order Service
     *
     * @return Order Service
     */
    public OrderService getOrderService() {
        return moOrderService;
    }

    /**
     * Sets the Order Service
     *
     * @param oOrderService Order Service instance
     */
    public void setOrderService(OrderService oOrderService) {
        this.moOrderService = oOrderService;
    }

}

