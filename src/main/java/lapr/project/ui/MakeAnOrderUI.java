package lapr.project.ui;

import lapr.project.controller.MakeAnOrderController;
import lapr.project.model.Pharmacy;
import lapr.project.model.Product;

import java.util.List;
import java.util.Scanner;

public class MakeAnOrderUI {
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);
            MakeAnOrderController oCtrl = new MakeAnOrderController();


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
            Integer intProductId = -1;

            do{
                for(Product p : lstProducts){
                    System.out.println(p.toString());
                }
                System.out.println("-1 - to stop the product insertion");

                System.out.print("\nChoose the Product's Id: ");
                intProductId = Integer.parseInt(sc.nextLine());
                System.out.println();

                if(intProductId == -1){
                    break;
                }

                Product oProduct = new Product();
                Integer intQuantity = -1;
                for(Product p : lstProducts){
                    if (p.hasId(intProductId)){
                        oProduct = p;
                        break;
                    }
                }

                if(oProduct.getName().equalsIgnoreCase("") || intQuantity == -1){
                    throw new Exception();
                }
                oCtrl.addProductToOrder(oProduct, intQuantity);
            }while(intProductId != -1);

            System.out.print("Do you want to pick it up on the pharmacy? (Y/N): ");
            String strDeliveryCheck = sc.nextLine();

            if(strDeliveryCheck.equalsIgnoreCase("N")){
                System.out.print("Do you want it delivered to your home address? (Y/N): ");
                strDeliveryCheck = sc.nextLine();
                if(strDeliveryCheck.equalsIgnoreCase("N")){

                } else{

                }
            }

            //oCtrl.newOrder();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
