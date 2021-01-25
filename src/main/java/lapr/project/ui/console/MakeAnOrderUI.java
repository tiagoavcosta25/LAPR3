package lapr.project.ui.console;

import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.MakeAnOrderController;
import lapr.project.model.*;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class MakeAnOrderUI{

    public boolean runFile(String[] columns){
        MakeAnOrderController oCtrl = new MakeAnOrderController();

        ApplicationPOT.getInstance().setCurrentSession(new UserSession(columns[0].trim()));

        List<Pharmacy> lstPharmacies = oCtrl.getPharmacies();
        Pharmacy oPharmacy = null;

        for(Pharmacy p : lstPharmacies){
            if(p.hasEmail(columns[3].trim())){
                oPharmacy = p;
                break;
            }
        }

        List<Product> lstProducts = oCtrl.getAvailableProducts(oPharmacy);

        int startingCCIndex = -1;
        Product oProduct;
        for(int i = 4; i < columns.length; i += 2){
            oProduct = null;
            for(Product p : lstProducts){
                if(p.hasName(columns[i].trim())){
                    oProduct = p;
                    break;
                }
            }
            if(oProduct == null){
                startingCCIndex = i;
                break;
            }
            oCtrl.addProductToOrder(oProduct, Integer.parseInt(columns[i + 1].trim()));
        }

        List<CreditCard> lstCCs = oCtrl.getCreditCardsByClient();

        if(startingCCIndex != -1){
            CreditCard oCreditCard;
            for(int i = startingCCIndex; i < columns.length; i += 2){
                oCreditCard = null;
                for(CreditCard c : lstCCs){
                    if(c.hasNumber(Long.parseLong(columns[i].trim()))){
                        oCreditCard = c;
                        break;
                    }
                }
                oCtrl.addPayment(oCreditCard, Double.parseDouble(columns[i + 1].trim()));
            }

            oCtrl.newOrder(columns[1].trim(), Boolean.valueOf(columns[2].trim()));

            if (oCtrl.registerOrder()) {
                return true;
            }else return false;
        }
        return false;
    }

    public void run(){
        try{
            Scanner sc = new Scanner(System.in);
            MakeAnOrderController oCtrl = new MakeAnOrderController();

            boolean flag;
            boolean blnPaymentFlag;
            boolean blnHomeDelivery;

            do{
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

                if(oPharmacy.getName().equalsIgnoreCase("No name.")){
                    throw new Exception();
                }

                List<Product> lstProducts = oCtrl.getAvailableProducts(oPharmacy);
                Integer intProductId;

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

                    if(oProduct.getName().equalsIgnoreCase("") || intQuantity <= 0){
                        throw new Exception();
                    }
                    oCtrl.addProductToOrder(oProduct, intQuantity);
                    lstProducts.remove(oProduct);
                }while(intProductId != 0);

                System.out.print("Order´s Description: ");
                String strDescription = sc.nextLine();

                System.out.print("Do you want to pick it up on the pharmacy? (Y/N): ");
                String strCheck = sc.nextLine();

                if(strCheck.equalsIgnoreCase("N")){
                    blnHomeDelivery = true;
                } else{
                    blnHomeDelivery = false;
                }

                List<CreditCard> lstCC = oCtrl.getCreditCardsByClient();
                Long intCCNum;

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

                    if(oCreditCard.getCreditCardNr() == -1l || intAmount <= 0f){
                        throw new Exception();
                    }
                    blnPaymentFlag = oCtrl.addPayment(oCreditCard, intAmount);

                    if (blnPaymentFlag){
                        lstCC.remove(oCreditCard);
                    }
                }while(!blnPaymentFlag);

                Order oOrder = oCtrl.newOrder(strDescription, blnHomeDelivery);

                System.out.println(oOrder.toString());

                System.out.print("Do you want to confirm this order? (Y/N): ");
                strCheck = sc.nextLine();

                if(strCheck.equalsIgnoreCase("Y")){
                    if(oCtrl.registerOrder()){
                        System.out.println("Operation was successful. Order Registered.");
                        oCtrl.generateInvoice();
                        flag = true;
                    } else {
                        System.out.println("Something went wrong, try again. Order not Registered. If you need any help, please contact us using help@teamlisa.com.");
                        flag = false;
                    }
                } else{
                    flag = false;
                }
            } while(!flag);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
