package lapr.project.model.service;

import lapr.project.data.PharmacyTransferDB;
import lapr.project.model.*;
import lapr.project.utils.EmailSender;
import lapr.project.utils.WriteFile;

public class PharmacyTransferService {

    private static final String TRANSFERNUMBER = "Transfer Number: ";
    private static final String TAB = String.format("%n\t\t\t");
    private static final String LINEBREAKER = String.format("%n_______________________________________");

    private PharmacyTransferDB moPharmacyTransferDB;

    public PharmacyTransferDB getPharmacyTransferDB() {
        return moPharmacyTransferDB;
    }

    public void setPharmacyTransferDB(PharmacyTransferDB oPharmacyTransferDB) {
        this.moPharmacyTransferDB = oPharmacyTransferDB;
    }

    public PharmacyTransferService() {
        this.moPharmacyTransferDB = new PharmacyTransferDB();
    }

    public PharmacyTransfer getPharmacyTransfer(int strId) {
        return this.moPharmacyTransferDB.getPharmacyTransfer(strId);
    }

    public boolean removePharmacyTransfer(int intId) {
        return this.moPharmacyTransferDB.removePharmacyTransfer(intId);
    }

    public boolean registerPharmacyTransfer(PharmacyTransfer oPharmacyTransfer) {
        return this.moPharmacyTransferDB.registerPharmacyTransfer(oPharmacyTransfer);
    }

    public PharmacyTransfer newPharmacyTransfer(Order oOrder, Product oProduct, Integer intQuantity, Pharmacy oPharmacy) {
        return new PharmacyTransfer(oOrder, oProduct, intQuantity, oPharmacy);
    }

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

    public boolean updateStockFromTransfer(int intPharmacyTransferId) {
        return this.moPharmacyTransferDB.updateStockFromTransfer(intPharmacyTransferId);
    }

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

    private String getBody(PharmacyTransfer oPharmacyTransfer, String strNote){
            StringBuilder strBody = new StringBuilder(String.format("_______________________________________%n%n\t\t\tTransfer No. %d", oPharmacyTransfer.getId()));
            strBody.append(String.format("%n\t\t\t\t%td-%<tb-%<tY\n", oPharmacyTransfer.getTransferDate()));
            strBody.append(LINEBREAKER);

            strBody.append(String.format("%n%n---------------------------------------"));
            strBody.append(String.format("%n\t\t\tSending Pharmacy:%n---------------------------------------%n%n%n%n\t\t\t%s", oPharmacyTransfer.getNearbyPharmacy().getName()));
            strBody.append(String.format("%s%s\n", TAB, oPharmacyTransfer.getNearbyPharmacy().getEmail()));
            strBody.append(String.format("%s%s\n", TAB, oPharmacyTransfer.getNearbyPharmacy().getAddress().getStreetName()));
            strBody.append(String.format("%s%s\n", TAB, oPharmacyTransfer.getNearbyPharmacy().getAddress().getDoorNumber()));
            strBody.append(String.format("%s%s\n", TAB, oPharmacyTransfer.getNearbyPharmacy().getAddress().getPostalCode()));
            strBody.append(String.format("%s%s\n", TAB, oPharmacyTransfer.getNearbyPharmacy().getAddress().getLocality()));
            strBody.append(String.format("%s%s\n", TAB, oPharmacyTransfer.getNearbyPharmacy().getAddress().getCountry()));
            strBody.append(String.format("%n%n%n%n---------------------------------------"));
            strBody.append(String.format("%n\t\t\tReceiving Pharmacy:%n---------------------------------------%n%n%n%n\t\t\t%s", oPharmacyTransfer.getOrder().getPharmacy().getName()));
            strBody.append(String.format("%s%s\n", TAB, oPharmacyTransfer.getOrder().getPharmacy().getEmail()));
            strBody.append(String.format("%s%s\n", TAB, oPharmacyTransfer.getOrder().getPharmacy().getAddress().getStreetName()));
            strBody.append(String.format("%s%s\n", TAB, oPharmacyTransfer.getOrder().getPharmacy().getAddress().getDoorNumber()));
            strBody.append(String.format("%s%s\n", TAB, oPharmacyTransfer.getOrder().getPharmacy().getAddress().getPostalCode()));
            strBody.append(String.format("%s%s\n", TAB, oPharmacyTransfer.getOrder().getPharmacy().getAddress().getLocality()));
            strBody.append(String.format("%s%s\n", TAB, oPharmacyTransfer.getOrder().getPharmacy().getAddress().getCountry()));
            strBody.append(String.format("%n%n%n%n---------------------------------------"));
            strBody.append(String.format("%n\t\t\tProduct Ordered"));
            strBody.append(String.format("%n---------------------------------------%n%n%n%n%dx %s", oPharmacyTransfer.getQuantity(), oPharmacyTransfer.getProduct().getName()));
            strBody.append(String.format("%n%n%n%n_______________________________________"));
            strBody.append(String.format("%s%s\n", TAB, strNote));
            strBody.append(LINEBREAKER);
            strBody.append(String.format("%n\t\t\t\tTHANK YOU!\n"));
            strBody.append(LINEBREAKER);
            return strBody.toString();
    }
}
