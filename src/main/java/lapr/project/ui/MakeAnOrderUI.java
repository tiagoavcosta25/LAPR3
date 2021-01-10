package lapr.project.ui;

import lapr.project.controller.MakeAnOrderController;
import lapr.project.model.Order;
import lapr.project.model.Pharmacy;
import lapr.project.model.Product;

import java.util.List;
import java.util.Scanner;

public class MakeAnOrderUI {
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);
            MakeAnOrderController oCtrl = new MakeAnOrderController();

            boolean flag = false;

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

                System.out.print("Order´s Description: ");
                String strDescription = sc.nextLine();

                System.out.print("Do you want to pick it up on the pharmacy? (Y/N): ");
                String strCheck = sc.nextLine();

                Order oOrder = new Order();

                if(strCheck.equalsIgnoreCase("N")){
                    System.out.print("Do you want it delivered to your home address? (Y/N): ");
                    strCheck = sc.nextLine();
                    if(strCheck.equalsIgnoreCase("N")){
                        System.out.print("\nDelivery Address\nLatitude: ");
                        Double dblLatitude = Double.parseDouble(sc.nextLine());
                        System.out.print("\nLongitude: ");
                        Double dblLongitude = Double.parseDouble(sc.nextLine());
                        System.out.print("\nStreet Name: ");
                        String strStreetName = sc.nextLine();
                        System.out.print("\nDoor Number: ");
                        String strDoorNumber = sc.nextLine();
                        System.out.print("\nPostal Code: ");
                        String strPostalCode = sc.nextLine();
                        System.out.print("\nLocality: ");
                        String strLocality = sc.nextLine();
                        System.out.print("\nCountry: ");
                        String strCountry = sc.nextLine();

                        oOrder = oCtrl.newOrder(strDescription, dblLatitude, dblLongitude, strStreetName, strDoorNumber,
                                strPostalCode, strLocality, strCountry);
                    } else{
                        oOrder = oCtrl.newOrder(strDescription, true);
                    }
                } else{
                    oOrder = oCtrl.newOrder(strDescription, false);
                }

                System.out.println(oOrder.toString());

                System.out.print("Do you want to confirm this order? (Y/N): ");
                strCheck = sc.nextLine();

                if(strCheck.equalsIgnoreCase("Y")){
                    if(oCtrl.registerOrder()){
                        System.out.println("Operation was successful. Order Registered.");
                        flag = true;
                    } else {
                        System.out.println("Something went wrong, try again. Order not Registered. If you need any help, please contact us using help@teamlisa.com.");
                        flag = false;
                    }
                } else{
                    flag = false;
                }
            } while(flag == false);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
