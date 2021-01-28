package lapr.project.model.service;

import lapr.project.data.InvoiceDB;
import lapr.project.model.*;
import lapr.project.utils.EmailSender;
import lapr.project.utils.WriteFile;

import java.util.Map;

/**
 * Invoice Service.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class InvoiceService {

    /**
     * Invoice DB Management.
     */
    private InvoiceDB moInvoiceDB;

    /**
     * Linebreak used in the invoice.
     */
    private static final String LINEBREAK = String.format("%n%n---------------------------------------");

    /**
     * Linebreak variant used in the invoice.
     */
    private static final String LINEBREAK2 = String.format("%n---------------------------------------");

    /**
     * Tab format used in the invoice.
     */
    private static final String TAB = String.format("%n\t\t\t");


    /**
     * Empty Contructor.
     */
    public InvoiceService() {
        this.moInvoiceDB = new InvoiceDB();
    }

    /**
     * Getter for Invoice Databse Connection Class.
     * @return Invoice Databse Connection Class.
     */
    public InvoiceDB getInvoiceDB() {
        return moInvoiceDB;
    }

    /**
     * Setter for the Invoice Databse Connection Class.
     * @param oInvoiceDB Invoice Databse Connection Class..
     */
    public void setInvoiceDB(InvoiceDB oInvoiceDB) {
        this.moInvoiceDB = oInvoiceDB;
    }

    /**
     * Calls a InvoiceDB method to get an Invoice from the Database based on its ID.
     * @param strId ID.
     * @return Invoice.
     */
    public Invoice getInvoice(int strId) {
        return this.moInvoiceDB.getInvoice(strId);
    }

    /**
     * Calls a InvoiceDB method to remove an Invoice from the Database based on its ID.
     * @param intId ID.
     * @return Invoice.
     */
    public boolean removeInvoice(int intId) {
        return this.moInvoiceDB.removeInvoice(intId);
    }

    /**
     * Calls a InvoiceDB method to register an Invoice on the Database.
     * @param oInvoice Invoice.
     * @return Invoice Id.
     */
    public int registerInvoice(Invoice oInvoice) {
        return this.moInvoiceDB.registerInvoice(oInvoice);
    }

    /**
     * Creates a new Instance of an Invoice.
     * @param oOrder Order.
     * @param mapPayments Map of Payments.
     * @return Invoice.
     */
    public Invoice newInvoice(Order oOrder, Map<CreditCard, Double> mapPayments) {
        return new Invoice(oOrder, mapPayments);
    }

    /**
     * Sends an email to the client with
     * @param oInvoice Invoice.
     * @return true if itsends the email, false otherwise.
     */
    public boolean sendInvoiceByEmail(Invoice oInvoice) {
        try{
            String strEmail = oInvoice.getOrder().getClient().getEmail();
            boolean isHomeDelivery = oInvoice.getOrder().isHomeDelivery();
            StringBuilder strBody = new StringBuilder(String.format("_______________________________________%n%n\t\t\tInvoice No. %d", oInvoice.getId()));
            strBody.append(String.format("%n\t\t\t\t%td-%<tb-%<tY", oInvoice.getInvoiceDate()));

            strBody.append(LINEBREAK);
            strBody.append(String.format("%n%n\t\t\tPharmacy: %s", oInvoice.getOrder().getPharmacy().getName()));
            strBody.append(String.format("%n%n\t\t\t%s", oInvoice.getOrder().getPharmacy().getEmail()));
            strBody.append(String.format("%s%s", TAB, oInvoice.getOrder().getPharmacy().getAddress().getStreetName()));
            strBody.append(String.format("%s%s", TAB, oInvoice.getOrder().getPharmacy().getAddress().getDoorNumber()));
            strBody.append(String.format("%s%s", TAB, oInvoice.getOrder().getPharmacy().getAddress().getPostalCode()));
            strBody.append(String.format("%s%s", TAB, oInvoice.getOrder().getPharmacy().getAddress().getLocality()));
            strBody.append(String.format("%s%s", TAB, oInvoice.getOrder().getPharmacy().getAddress().getCountry()));
            strBody.append(LINEBREAK);
            strBody.append(String.format("%n%n\t\t\tBill To:%n\t\t\t %s", oInvoice.getOrder().getClient().getName()));
            strBody.append(String.format("%s%s", TAB, oInvoice.getOrder().getClient().getEmail()));
            strBody.append(String.format("%s%s", TAB, oInvoice.getOrder().getClient().getAddress().getStreetName()));
            strBody.append(String.format("%s%s", TAB, oInvoice.getOrder().getClient().getAddress().getDoorNumber()));
            strBody.append(String.format("%s%s", TAB, oInvoice.getOrder().getClient().getAddress().getPostalCode()));
            strBody.append(String.format("%s%s", TAB, oInvoice.getOrder().getClient().getAddress().getLocality()));
            strBody.append(String.format("%s%s", TAB, oInvoice.getOrder().getClient().getAddress().getCountry()));
            strBody.append(LINEBREAK);
            if(isHomeDelivery){
                strBody.append(String.format("%n%n\tDelivery Type: Home Delivery"));
            } else{
                strBody.append(String.format("%n%n\tDelivery Type: Store Pickup"));
            }
            strBody.append(LINEBREAK);

            strBody.append(String.format("%n%n\t\t\tProducts Ordered"));
            strBody.append(LINEBREAK);

            int c = 1;
            for(Map.Entry<Product, Integer> e : oInvoice.getOrder().getProducts().entrySet()){
                String strProductInfo = String.format("%dx %s", e.getValue(), e.getKey().getName());
                double dblAmount = e.getKey().getUnitaryPrice() * (double)e.getValue();
                strBody.append(String.format("%n%n| %d | %-20.20s | %.2f€ |", c, strProductInfo, dblAmount));
                while(strProductInfo.length()>20){
                    strProductInfo = strProductInfo.substring(20);
                    System.out.printf("%n|   | %-20.20s |         |", strProductInfo);
                }
                strBody.append(LINEBREAK);
                c++;
            }

            strBody.append(String.format("%n%n\t\t\tPayments"));
            strBody.append(LINEBREAK);

            c = 1;
            for(Map.Entry<CreditCard, Double> e : oInvoice.getPayments().entrySet()){
                String strCCNum = Long.toString(e.getKey().getCreditCardNr());
                strBody.append(String.format("%n%n| %d | %-20.20s | %.2f€ |", c, strCCNum, e.getValue()));
                strBody.append(LINEBREAK);
                c++;
            }
            strBody.append(LINEBREAK);

            double dblFee;
            if(isHomeDelivery){
                dblFee = oInvoice.getOrder().getAdditionalFee();
            } else{
                dblFee = 0d;
            }
            strBody.append(String.format("%n|\t   Sub Total\t\t      %.2f€ |", oInvoice.getTotalPrice() - dblFee));
            strBody.append(LINEBREAK2);
            if(isHomeDelivery) {
                strBody.append(String.format("%n|\tHome Delivery Fee\t\t  %.2f€ |", dblFee));
                strBody.append(LINEBREAK2);
            }
            strBody.append(String.format("%n|\t   Total\t\t\t\t   %.2f€ |", oInvoice.getTotalPrice()));
            strBody.append(LINEBREAK2);
            strBody.append(String.format("%n\t\t\t\tTHANK YOU!"));
            strBody.append(LINEBREAK2);

            EmailSender.sendEmail(strEmail, "Invoice Number: " + oInvoice.getId(), strBody.toString());
            return WriteFile.write("Invoice_" + oInvoice.getId(), strBody.toString());
        } catch (Exception e){
            return false;
        }
    }
}
