package lapr.project.model.service;

import lapr.project.data.PharmacyTransferDB;
import lapr.project.model.*;
import lapr.project.utils.EmailSender;
import lapr.project.utils.WriteFile;

/**
 * Pharmacy Transfer Service.
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

public class PharmacyTransferService {

    /**
     * Constant used in the email.
     */
    private static final String TRANSFERNUMBER = "Transfer Number: ";

    /**
     * Tab format used in the email.
     */
    private static final String TAB = String.format("%n\t\t\t");

    /**
    * Linebreak variant used in the email.
    */
    private static final String LINEBREAKER = String.format("%n_______________________________________");

    /**
     * Pharmacy Transfer Database.
     */
    private PharmacyTransferDB moPharmacyTransferDB;

    /**
     * Returns Pharmacy Transfer Database.
     * @return Pharmacy Transfer Database.
     */
    public PharmacyTransferDB getPharmacyTransferDB() {
        return moPharmacyTransferDB;
    }

    /**
     * Modifies Pharmacy Transfer Database.
     * @param oPharmacyTransferDB Pharmacy Transfer Database.
     */
    public void setPharmacyTransferDB(PharmacyTransferDB oPharmacyTransferDB) {
        this.moPharmacyTransferDB = oPharmacyTransferDB;
    }

    /**
     * An empty constructor of Pharmacy Tranfer Service.
     */
    public PharmacyTransferService() {
        this.moPharmacyTransferDB = new PharmacyTransferDB();
    }

    /**
     * Returns Pharmacy Transfer.
     * @param strId Pharmacy Transfer ID.
     * @return Pharmacy Transfer.
     */
    public PharmacyTransfer getPharmacyTransfer(int strId) {
        return this.moPharmacyTransferDB.getPharmacyTransfer(strId);
    }

    /**
     * Removes Pharmacy Transfer.
     * @param intId Pharmacy Transfer ID.
     * @return true if the Pharmacy Transfer is removed. False if otherwise.
     */
    public boolean removePharmacyTransfer(int intId) {
        return this.moPharmacyTransferDB.removePharmacyTransfer(intId);
    }

    /**
     * Registers Pharmacy Transfer.
     * @param oPharmacyTransfer Pharmacy Transfer.
     * @return true if the Pharmacy Transfer is registered. False if otherwise.
     */
    public boolean registerPharmacyTransfer(PharmacyTransfer oPharmacyTransfer) {
        return this.moPharmacyTransferDB.registerPharmacyTransfer(oPharmacyTransfer);
    }

    /**
     * Creates a new Pharmacy Transfer Instance.
     * @param oOrder Order.
     * @param oProduct Order's Product.
     * @param intQuantity Product's Quantity.
     * @param oPharmacy Pharmacy.
     * @return New Pharmacy Transfer Instance.
     */
    public PharmacyTransfer newPharmacyTransfer(Order oOrder, Product oProduct, Integer intQuantity, Pharmacy oPharmacy) {
        return new PharmacyTransfer(oOrder, oProduct, intQuantity, oPharmacy);
    }

    /**
     * Sends Email with Transfer Note.
     * @param oPharmacyTransfer Pharmacy Transfer.
     * @return true if the emai is sent. False if otherwise.
     */
    public boolean sendEmailWithTransferNote(PharmacyTransfer oPharmacyTransfer) {
        try{
            String strEmail = oPharmacyTransfer.getOrder().getPharmacy().getEmail();
            String strBody = getBody(oPharmacyTransfer, "Product Sent!");

            EmailSender.sendEmail(strEmail, TRANSFERNUMBER + oPharmacyTransfer.getId(), strBody);
            EmailSender.sendEmail(oPharmacyTransfer.getNearbyPharmacy().getEmail(), TRANSFERNUMBER + oPharmacyTransfer.getId(), "Transfer Note Sent With Success!");

            return WriteFile.write("TransferNote_" + oPharmacyTransfer.getId(), strBody);
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Updates the Pharmacy Stock From Transfer.
     * @param intPharmacyTransferId Pharmacy Transfer ID.
     * @return true if the Stock is updated. False if otherwise.
     */
    public boolean updateStockFromTransfer(int intPharmacyTransferId) {
        return this.moPharmacyTransferDB.updateStockFromTransfer(intPharmacyTransferId);
    }

    /**
     * Sends Email with Delivery Note.
     * @param oPharmacyTransfer Pharmacy Transfer.
     * @return true if the emai is sent. False if otherwise.
     */
    public boolean sendEmailWithDeliveryNote(PharmacyTransfer oPharmacyTransfer) {
        try{
            String strEmail = oPharmacyTransfer.getNearbyPharmacy().getEmail();
            String strBody = getBody(oPharmacyTransfer, "Product Delivered!");

            EmailSender.sendEmail(strEmail, TRANSFERNUMBER + oPharmacyTransfer.getId(), strBody);
            EmailSender.sendEmail(oPharmacyTransfer.getOrder().getPharmacy().getEmail(), TRANSFERNUMBER + oPharmacyTransfer.getId(), "Delivery Note Sent With Success!");

            return WriteFile.write("DeliveryNote_" + oPharmacyTransfer.getId(), strBody);
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Returns the Body's Email.
     * @param oPharmacyTransfer Pharmacy Transfer.
     * @param strNote Transfer Note.
     * @return Body's Email.
     */
    private String getBody(PharmacyTransfer oPharmacyTransfer, String strNote){
            StringBuilder strBody = new StringBuilder(String.format("_______________________________________%n%n\t\t\tTransfer No. %d", oPharmacyTransfer.getId()));
            strBody.append(String.format("%n\t\t\t\t%td-%<tb-%<tY\n", oPharmacyTransfer.getTransferDate()));
            strBody.append(LINEBREAKER);

            strBody.append(String.format("%n%n---------------------------------------"));
            strBody.append(String.format("%n\t\t\tSending Pharmacy:%n---------------------------------------%n%n%n%n\t\t\t%s", oPharmacyTransfer.getNearbyPharmacy().getName()));
            strBody.append(String.format("%s%s", TAB, oPharmacyTransfer.getNearbyPharmacy().getEmail()));
            strBody.append(String.format("%s%s", TAB, oPharmacyTransfer.getNearbyPharmacy().getAddress().getStreetName()));
            strBody.append(String.format("%s%s", TAB, oPharmacyTransfer.getNearbyPharmacy().getAddress().getDoorNumber()));
            strBody.append(String.format("%s%s", TAB, oPharmacyTransfer.getNearbyPharmacy().getAddress().getPostalCode()));
            strBody.append(String.format("%s%s", TAB, oPharmacyTransfer.getNearbyPharmacy().getAddress().getLocality()));
            strBody.append(String.format("%s%s", TAB, oPharmacyTransfer.getNearbyPharmacy().getAddress().getCountry()));
            strBody.append(String.format("%n%n%n%n---------------------------------------"));
            strBody.append(String.format("%n\t\t\tReceiving Pharmacy:%n---------------------------------------%n%n%n%n\t\t\t%s", oPharmacyTransfer.getOrder().getPharmacy().getName()));
            strBody.append(String.format("%s%s", TAB, oPharmacyTransfer.getOrder().getPharmacy().getEmail()));
            strBody.append(String.format("%s%s", TAB, oPharmacyTransfer.getOrder().getPharmacy().getAddress().getStreetName()));
            strBody.append(String.format("%s%s", TAB, oPharmacyTransfer.getOrder().getPharmacy().getAddress().getDoorNumber()));
            strBody.append(String.format("%s%s", TAB, oPharmacyTransfer.getOrder().getPharmacy().getAddress().getPostalCode()));
            strBody.append(String.format("%s%s", TAB, oPharmacyTransfer.getOrder().getPharmacy().getAddress().getLocality()));
            strBody.append(String.format("%s%s", TAB, oPharmacyTransfer.getOrder().getPharmacy().getAddress().getCountry()));
            strBody.append(String.format("%n%n%n%n---------------------------------------"));
            strBody.append(String.format("%n\t\t\tProduct Ordered"));
            strBody.append(String.format("%n---------------------------------------%n%n%n%n%dx %s", oPharmacyTransfer.getQuantity(), oPharmacyTransfer.getProduct().getName()));
            strBody.append(String.format("%n%n%n%n_______________________________________"));
            strBody.append(String.format("%s%s%n", TAB, strNote));
            strBody.append(LINEBREAKER);
            strBody.append(String.format("%n\t\t\t\tTHANK YOU!%n"));
            strBody.append(LINEBREAKER);
            return strBody.toString();
    }
}
