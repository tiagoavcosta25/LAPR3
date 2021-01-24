package lapr.project.model.service;

import lapr.project.data.InvoiceDB;
import lapr.project.model.*;
import lapr.project.utils.EmailSender;
import lapr.project.utils.WriteFile;

import java.util.Map;

public class InvoiceService {

    private InvoiceDB moInvoiceDB;
    private static String LINEBREAK = String.format("%n%n---------------------------------------");;

    public InvoiceService() {
        this.moInvoiceDB = new InvoiceDB();
    }

    public Invoice getInvoice(int strId) {
        return this.moInvoiceDB.getInvoice(strId);
    }

    public boolean removeInvoice(int intId) {
        return this.moInvoiceDB.removeInvoice(intId);
    }

    public boolean registerInvoice(Invoice oInvoice) {
        return this.moInvoiceDB.registerInvoice(oInvoice);
    }

    public Invoice newInvoice(Order oOrder, Map<CreditCard, Double> mapPayments) {
        return new Invoice(oOrder, mapPayments);
    }

    public boolean sendInvoiceByEmail(Invoice oInvoice) {
        try{
            String strEmail = oInvoice.getOrder().getClient().getEmail();
            boolean isHomeDelivery = oInvoice.getOrder().isHomeDelivery();
            String strBody = String.format("_______________________________________%n%n\t\t\tInvoice No. %d", oInvoice.getId());
            strBody += String.format("%n\t\t\t\t%td-%<tb-%<tY", oInvoice.getInvoiceDate());

            strBody += LINEBREAK;
            strBody += String.format("%n%n\t\t\tPharmacy: %s", oInvoice.getOrder().getPharmacy().getName());
            strBody += String.format("%n%n\t\t\t%s", oInvoice.getOrder().getPharmacy().getEmail());
            strBody += String.format("%n\t\t\t%s", oInvoice.getOrder().getPharmacy().getAddress().getStreetName());
            strBody += String.format("%n\t\t\t%s", oInvoice.getOrder().getPharmacy().getAddress().getDoorNumber());
            strBody += String.format("%n\t\t\t%s", oInvoice.getOrder().getPharmacy().getAddress().getPostalCode());
            strBody += String.format("%n\t\t\t%s", oInvoice.getOrder().getPharmacy().getAddress().getLocality());
            strBody += String.format("%n\t\t\t%s", oInvoice.getOrder().getPharmacy().getAddress().getCountry());
            strBody += LINEBREAK;
            strBody += String.format("%n%n\t\t\tBill To:%n\t\t\t %s", oInvoice.getOrder().getClient().getName());
            strBody += String.format("%n\t\t\t%s", oInvoice.getOrder().getClient().getEmail());
            strBody += String.format("%n\t\t\t%s", oInvoice.getOrder().getClient().getAddress().getStreetName());
            strBody += String.format("%n\t\t\t%s", oInvoice.getOrder().getClient().getAddress().getDoorNumber());
            strBody += String.format("%n\t\t\t%s", oInvoice.getOrder().getClient().getAddress().getPostalCode());
            strBody += String.format("%n\t\t\t%s", oInvoice.getOrder().getClient().getAddress().getLocality());
            strBody += String.format("%n\t\t\t%s", oInvoice.getOrder().getClient().getAddress().getCountry());
            strBody += LINEBREAK;
            if(isHomeDelivery){
                strBody += String.format("%n%n\tDelivery Type: Home Delivery");
            } else{
                strBody += String.format("%n%n\tDelivery Type: Store Pickup");
            }
            strBody += LINEBREAK;
            //strBody += String.format("%n| ID |\t\t\tDESC\t\t|\tAMT\t  |\n---------------------------------------");

            strBody += String.format("%n%n\t\t\tProducts Ordered");
            strBody += LINEBREAK;

            int c = 1;
            for(Map.Entry<Product, Integer> e : oInvoice.getOrder().getProducts().entrySet()){
                String strProductInfo = String.format("%dx %s", e.getValue(), e.getKey().getName());
                double dblAmount = e.getKey().getUnitaryPrice() * (double)e.getValue();
                strBody += String.format("%n%n| %d | %-20.20s | %.2f€ |", c, strProductInfo, dblAmount);
                while(strProductInfo.length()>20){
                    strProductInfo = strProductInfo.substring(20);
                    System.out.printf("%n|   | %-20.20s |         |", strProductInfo);
                }
                strBody += LINEBREAK;
                c++;
            }

            strBody += String.format("%n%n\t\t\tPayments");
            strBody += LINEBREAK;

            c = 1;
            for(Map.Entry<CreditCard, Double> e : oInvoice.getPayments().entrySet()){
                String strCCNum = Long.toString(e.getKey().getCreditCardNr());
                strBody += String.format("%n%n| %d | %-20.20s | %.2f€ |", c, strCCNum, e.getValue());
                strBody += LINEBREAK;
                c++;
            }
            strBody += String.format("%n%n_______________________________________");

            double dblFee;
            if(isHomeDelivery){
                dblFee = oInvoice.getOrder().getAdditionalFee();
            } else{
                dblFee = 0d;
            }
            strBody += String.format("%n|\t   Sub Total\t\t      %.2f€ |", oInvoice.getTotalPrice() - dblFee);
            strBody += String.format("%n---------------------------------------");
            if(isHomeDelivery) {
                strBody += String.format("%n|\t   Home Delivery Fee\t\t\t\t  %.2f€ |", dblFee);
                strBody += String.format("%n---------------------------------------");
            }
            strBody += String.format("%n|\t   Total\t\t\t\t   %.2f€ |", oInvoice.getTotalPrice());
            strBody += String.format("%n---------------------------------------");
            strBody += String.format("%n\t\t\t\tTHANK YOU!");
            strBody += String.format("%n_______________________________________");

            EmailSender.sendEmail(strEmail, "Invoice Number: " + oInvoice.getId(), strBody);
            return WriteFile.write("Invoice_" + oInvoice.getId(), strBody);
        } catch (Exception e){
            return false;
        }
    }
}
