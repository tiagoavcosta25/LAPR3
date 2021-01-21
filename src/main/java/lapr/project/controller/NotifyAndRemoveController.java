package lapr.project.controller;

import lapr.project.model.Order;
import lapr.project.model.Product;
import lapr.project.model.service.OrderService;
import lapr.project.utils.EmailSender;

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
            EmailSender.sendEmail(order.getClient().getEmail(), "Unsuccessful Order", "Dear costumer, I'm sorry to inform you but some of the products: \n"
                    + order.getProducts().toString() + "\n are out of Stock in every Pharmacy");
            return false;

        } else {
            if (lstProdcuts.isEmpty()) {
                return true;
            } else {
                for (Map.Entry<Product, Integer> entry : lstProdcuts.entrySet()) {
                    ctrl.getStockFromAnotherPharamacy(order, entry.getKey(), entry.getValue());
                }
                EmailSender.sendEmail(order.getClient().getEmail(), "Order In Transit", "Dear costumer, I'm sorry to inform you but the products: \n"
                        + lstProdcuts.toString() + "\n are out of Stock in our Pharmacy. We will send it to you as soon as possible from another one of our providers.");
                return false;
            }

        }
    }

}

