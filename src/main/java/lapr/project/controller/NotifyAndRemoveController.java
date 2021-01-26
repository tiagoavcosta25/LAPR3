package lapr.project.controller;

import lapr.project.model.Order;
import lapr.project.model.Product;
import lapr.project.model.service.OrderService;
import lapr.project.utils.EmailSender;
import lapr.project.utils.WriteFile;

import java.util.Map;

/**
 * Register Courier Controller.
 * <p>
 * Group: Team Lisa [G-037]
 * ______________________________________________________
 *
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 */
public class NotifyAndRemoveController {

    /**
     * Courier Management class
     */
    private OrderService moOrderService;
    MakeAPharmacyTransferController ctrl;



    /**
     * An empty constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public NotifyAndRemoveController() {
        this.moOrderService = new OrderService();
        this.ctrl = new MakeAPharmacyTransferController();
    }

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

    public OrderService getOrderService() {
        return moOrderService;
    }

    public void setOrderService(OrderService oOrderService) {
        this.moOrderService = oOrderService;
    }

}

