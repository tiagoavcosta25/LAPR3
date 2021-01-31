package lapr.project.ui.console;

import lapr.project.controller.MakeAnOrderController;
import lapr.project.model.*;
import lapr.project.ui.Menu;
import lapr.project.ui.UI;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MakeAnOrderUI implements UI {

    private static final Logger LOGGER = Logger.getLogger(MakeAnOrderUI.class.getName());
    private static Scanner sc = new Scanner(System.in);

    public void run(){
        try{
            MakeAnOrderController oCtrl = new MakeAnOrderController();
            boolean flag;
            boolean blnHomeDelivery;

            do{

                chooseProducts(oCtrl);
                Menu.clear();

                if(oCtrl.getMapProducts().isEmpty()){throw new Exception();}

                System.out.print("Order´s Description: ");
                String strDescription = sc.nextLine();
                Menu.clear();

                System.out.print("Do you want the order delivered to your home with an additional fee? (Y/N): ");
                String strCheck = sc.nextLine();
                Menu.clear();

                if(strCheck.equalsIgnoreCase("Y")){
                    blnHomeDelivery = true;
                } else{
                    blnHomeDelivery = false;
                }

                Order oOrder = oCtrl.newOrder(strDescription, blnHomeDelivery);
                strCheck = confirmOrder(oOrder);

                if(strCheck.equalsIgnoreCase("Y")){
                    chooseCC(oCtrl);
                    Menu.clear();
                    if(oCtrl.getMapPayments().isEmpty()){throw new Exception();}

                    if(oCtrl.registerOrder()){
                        LOGGER.log(Level.INFO, "Order Registered with success.");
                        flag = true;
                    } else {
                        LOGGER.log(Level.WARNING,"Something went wrong, try again. Order not Registered. If you need any help, please contact us using help@teamlisa.com.");
                        flag = false;
                    }
                } else{
                    flag = true;
                }
            } while(!flag);

        } catch (Exception e){
            LOGGER.log(Level.WARNING,"Something went wrong, try again. Order not Registered. If you need any help, please contact us using help@teamlisa.com.");
        }
    }

    private String confirmOrder(Order oOrder) {

        System.out.printf("Order:%n%n-Pharmacy: %s%n-Description: %s%n-Total: %.2f€", oOrder.getPharmacy().getName(),
                oOrder.getDescription(), oOrder.getAmount() + oOrder.getAdditionalFee());

        System.out.print("\n\nDo you want to confirm this order? (Y/N): ");
        String strCheck = sc.nextLine();
        Menu.clear();
        return strCheck;
    }

    public static void chooseProducts(MakeAnOrderController oCtrl){
        Integer intProductId;
        List<Product> lstProducts = oCtrl.getAvailableProducts();
        do{
            if(lstProducts.isEmpty()){break;}
            for(Product p : lstProducts){
                System.out.printf("[%d] %s (%.2f€)%n", p.getId(), p.getName(), p.getUnitaryPrice());
            }
            System.out.println("\n[0] to stop the product insertion");

            System.out.print("\nChoose the Product's Id: ");
            intProductId = Integer.parseInt(sc.nextLine());
            System.out.println();

            if(intProductId != 0){
                Product oProduct = new Product();
                Integer intQuantity = 0;
                for(Product p : lstProducts){
                    if (p.hasId(intProductId)){
                        oProduct = p;
                        break;
                    }
                }

                System.out.print("\nChoose the Product's Quantity: ");
                intQuantity = Integer.parseInt(sc.nextLine());
                System.out.println();

                if(!oProduct.getName().equalsIgnoreCase("") && intQuantity > 0){
                    oCtrl.addProductToOrder(oProduct, intQuantity);
                    lstProducts.remove(oProduct);
                }
                Menu.clear();
            }
        }while(intProductId != 0);
    }

    public static void chooseCC(MakeAnOrderController oCtrl){
        List<CreditCard> lstCC = oCtrl.getCreditCardsByClient();
        Long intCCNum;
        int blnPaymentFlag = -1;

        do{
            System.out.printf("Total Amount to Pay: %.2f€%nTotal Left To Pay: %.2f€%n-----------------------------------------------------%n%nCredit Cards:%n",
                    oCtrl.getExpectedPayment(), oCtrl.getExpectedPayment() - oCtrl.getCurrentPayment());
            for(CreditCard c : lstCC){
                System.out.printf("[%d]%n", c.getCreditCardNr());
            }
            System.out.println("\n[0] to stop the credit card insertion");

            System.out.print("\nChoose the Credit Cards's Number: ");
            intCCNum = Long.parseLong(sc.nextLine());
            System.out.println();

            CreditCard oCreditCard = new CreditCard();
            double intAmount;
            for(CreditCard c : lstCC){
                if (c.hasNumber(intCCNum)){
                    oCreditCard = c;
                    break;
                }
            }

            System.out.print("\nChoose the amount of money you want to pay using that card: ");
            intAmount = Integer.parseInt(sc.nextLine());
            System.out.println();

            if(oCreditCard.getCreditCardNr() != -1l && intAmount > 0f){
                blnPaymentFlag = oCtrl.addPayment(oCreditCard, intAmount);

                if (blnPaymentFlag == 1){
                    break;
                } else if(blnPaymentFlag == 0){
                    lstCC.remove(oCreditCard);
                } else{
                    LOGGER.log(Level.WARNING,"The Amount you tried to pay was more than the amount owed. Try again.");
                }
            }
            Menu.clear();
        }while(blnPaymentFlag != 1);
    }
}
