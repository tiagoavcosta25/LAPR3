package lapr.project.ui.console;

import lapr.project.controller.MakeAnOrderController;
import lapr.project.model.*;
import lapr.project.ui.UI;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MakeAnOrderUI implements UI {

    private static final Logger LOGGER = Logger.getLogger(MakeAnOrderUI.class.getName());
    private static Scanner sc = new Scanner(System.in);
    private static MakeAnOrderController oCtrl = new MakeAnOrderController();

    public void run(){
        try{

            boolean flag;
            boolean blnHomeDelivery;

            do{
                Pharmacy oPharmacy = choosePharmacy();

                if(oPharmacy.getName().equalsIgnoreCase("No name.")){
                    throw new Exception();
                }

                chooseProducts(oPharmacy);

                System.out.print("Order´s Description: ");
                String strDescription = sc.nextLine();

                System.out.print("Do you want to pick it up on the pharmacy? (Y/N): ");
                String strCheck = sc.nextLine();

                if(strCheck.equalsIgnoreCase("N")){
                    blnHomeDelivery = true;
                } else{
                    blnHomeDelivery = false;
                }

                chooseCC();

                Order oOrder = oCtrl.newOrder(strDescription, blnHomeDelivery);

                System.out.println(oOrder.toString());

                System.out.print("Do you want to confirm this order? (Y/N): ");
                strCheck = sc.nextLine();

                if(strCheck.equalsIgnoreCase("Y")){
                    if(oCtrl.registerOrder()){
                        LOGGER.log(Level.INFO, "Order Registered with success.");
                        oCtrl.generateInvoice();
                        flag = true;
                    } else {
                        LOGGER.log(Level.INFO,"Something went wrong, try again. Order not Registered. If you need any help, please contact us using help@teamlisa.com.");
                        flag = false;
                    }
                } else{
                    flag = false;
                }
            } while(!flag);

        } catch (Exception e){
            LOGGER.log(Level.INFO,"Something went wrong, try again. Order not Registered. If you need any help, please contact us using help@teamlisa.com.");
        }
    }

    public static Pharmacy choosePharmacy(){
        List<Pharmacy> lstPharmacies = oCtrl.getPharmacies();

        for(Pharmacy p : lstPharmacies){
            System.out.println(p.toString());
        }

        System.out.print("\nChoose the Pharamcies's Id: ");
        Integer intPharamcyId = Integer.parseInt(sc.nextLine());
        System.out.println();

        Pharmacy oPharmacy = new Pharmacy();
        for(Pharmacy p : lstPharmacies){
            if (p.hasId(intPharamcyId)){
                oPharmacy = p;
                break;
            }
        }

        return oPharmacy;
    }

    public static void chooseProducts(Pharmacy oPharmacy){
        Integer intProductId;
        List<Product> lstProducts = oCtrl.getAvailableProducts(oPharmacy);
        do{
            for(Product p : lstProducts){
                System.out.println(p.toString());
            }
            System.out.println("0 - to stop the product insertion");

            System.out.print("\nChoose the Product's Id: ");
            intProductId = Integer.parseInt(sc.nextLine());
            System.out.println();

            if(intProductId == 0){
                break;
            }

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
        }while(intProductId != 0);
    }

    public static void chooseCC(){
        List<CreditCard> lstCC = oCtrl.getCreditCardsByClient();
        Long intCCNum;
        boolean blnPaymentFlag = false;

        do{
            for(CreditCard c : lstCC){
                System.out.println(c.toString());
            }

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

                if (blnPaymentFlag){
                    lstCC.remove(oCreditCard);
                }
            }
        }while(!blnPaymentFlag);
    }
}
