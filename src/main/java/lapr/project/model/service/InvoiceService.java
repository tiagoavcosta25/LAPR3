package lapr.project.model.service;

import lapr.project.data.InvoiceDB;
import lapr.project.model.*;
import lapr.project.utils.EmailSender;

import java.sql.Date;
import java.util.Map;

public class InvoiceService {

    private InvoiceDB m_oInvoiceDB;

    public InvoiceService() {
        this.m_oInvoiceDB = new InvoiceDB();
    }

    public Invoice getInvoice(int strId) {
        return this.m_oInvoiceDB.getInvoice(strId);
    }

    public boolean removeInvoice(int intId) {
        return this.m_oInvoiceDB.removeInvoice(intId);
    }

    public boolean registerInvoice(Invoice oInvoice) {
        return this.m_oInvoiceDB .registerInvoice(oInvoice);
    }

    public Invoice newInvoice(Order oOrder, Map<CreditCard, Float> mapPayments) {
        return new Invoice(oOrder, mapPayments);
    }

    public boolean sendInvoiceByEmail(Invoice oInvoice) {
        try{
            String strEmail = oInvoice.getOrder().getClient().getEmail();
            boolean isHomeDelivery = oInvoice.getOrder().isHomeDelivery();
            String strBody = String.format("_______________________________________\n\n\t\t\tInvoice No. %d", oInvoice.getId());
            strBody += String.format("\n\t\t\t\t%td-%<tb-%<tY", oInvoice.getInvoiceDate());

            strBody += String.format("\n\n---------------------------------------");
            strBody += String.format("\n\n\t\t\tPharmacy: %s", oInvoice.getOrder().getPharmacy().getName());
            strBody += String.format("\n\n\t\t\t%s", oInvoice.getOrder().getPharmacy().getEmail());
            strBody += String.format("\n\t\t\t%s", oInvoice.getOrder().getPharmacy().getAddress().getStreetName());
            strBody += String.format("\n\t\t\t%s", oInvoice.getOrder().getPharmacy().getAddress().getDoorNumber());
            strBody += String.format("\n\t\t\t%s", oInvoice.getOrder().getPharmacy().getAddress().getPostalCode());
            strBody += String.format("\n\t\t\t%s", oInvoice.getOrder().getPharmacy().getAddress().getLocality());
            strBody += String.format("\n\t\t\t%s", oInvoice.getOrder().getPharmacy().getAddress().getCountry());
            strBody += String.format("\n\n---------------------------------------");
            strBody += String.format("\n\n\t\t\tBill To:\n\t\t\t %s", oInvoice.getOrder().getClient().getName());
            strBody += String.format("\n\t\t\t%s", oInvoice.getOrder().getClient().getEmail());
            strBody += String.format("\n\t\t\t%s", oInvoice.getOrder().getClient().getAddress().getStreetName());
            strBody += String.format("\n\t\t\t%s", oInvoice.getOrder().getClient().getAddress().getDoorNumber());
            strBody += String.format("\n\t\t\t%s", oInvoice.getOrder().getClient().getAddress().getPostalCode());
            strBody += String.format("\n\t\t\t%s", oInvoice.getOrder().getClient().getAddress().getLocality());
            strBody += String.format("\n\t\t\t%s", oInvoice.getOrder().getClient().getAddress().getCountry());
            strBody += String.format("\n\n---------------------------------------");
            if(isHomeDelivery){
                strBody += String.format("\n\n\tDelivery Type: Home Delivery");
            } else{
                strBody += String.format("\n\n\tDelivery Type: Store Pickup");
            }
            strBody += String.format("\n\n---------------------------------------");
            //strBody += String.format("\n| ID |\t\t\tDESC\t\t|\tAMT\t  |\n---------------------------------------");

            strBody += String.format("\n\n\t\t\tProducts Ordered");
            strBody += String.format("\n\n---------------------------------------");

            int c = 1;
            for(Map.Entry<Product, Integer> e : oInvoice.getOrder().getProducts().entrySet()){
                String strProductInfo = String.format("%dx %s", e.getValue(), e.getKey().getName());
                float fltAmount = e.getKey().getUnitaryPrice() * (float)e.getValue();
                strBody += String.format("\n\n| %d | %-20.20s | %.2f€ |", c, strProductInfo, fltAmount);
                while(strProductInfo.length()>20){
                    strProductInfo = strProductInfo.substring(20);
                    System.out.printf("\n|   | %-20.20s |         |", strProductInfo);
                }
                strBody += String.format("\n\n---------------------------------------");
                c++;
            }

            strBody += String.format("\n\n\t\t\tPayments");
            strBody += String.format("\n\n---------------------------------------");

            c = 1;
            for(Map.Entry<CreditCard, Float> e : oInvoice.getPayments().entrySet()){
                String strCCNum = Long.toString(e.getKey().getCreditCardNr());
                strBody += String.format("\n\n| %d | %-20.20s | %.2f€ |", c, strCCNum, e.getValue());
                strBody += String.format("\n\n---------------------------------------");
                c++;
            }
            strBody += String.format("\n\n_______________________________________");

            float fltFee;
            if(isHomeDelivery){
                fltFee = oInvoice.getOrder().getAdditionalFee();
            } else{
                fltFee = 0f;
            }
            strBody += String.format("\n|\t   Sub Total\t\t      %.2f€ |", oInvoice.getTotalPrice() - fltFee);
            strBody += String.format("\n---------------------------------------");
            if(isHomeDelivery) {
                strBody += String.format("\n|\t   Home Delivery Fee\t\t\t\t  %.2f€ |", fltFee);
                strBody += String.format("\n---------------------------------------");
            }
            strBody += String.format("\n|\t   Total\t\t\t\t   %.2f€ |", oInvoice.getTotalPrice());
            strBody += String.format("\n---------------------------------------");
            strBody += String.format("\n\t\t\t\tTHANK YOU!");
            strBody += String.format("\n_______________________________________");

            EmailSender.emailSender(strEmail, "Invoice Number: " + oInvoice.getId(), strBody);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
