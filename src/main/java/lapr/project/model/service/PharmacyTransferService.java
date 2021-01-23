package lapr.project.model.service;

import lapr.project.data.PharmacyTransferDB;
import lapr.project.model.*;
import lapr.project.utils.EmailSender;
import lapr.project.utils.WriteFile;

import java.io.File;
import java.io.FileWriter;

public class PharmacyTransferService {

    private PharmacyTransferDB moPharmacyTransferDB;

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

            EmailSender.sendEmail(strEmail, "Transfer Number: " + oPharmacyTransfer.getId(), strBody);
            EmailSender.sendEmail(oPharmacyTransfer.getNearbyPharmacy().getEmail(), "Transfer Number: " + oPharmacyTransfer.getId(), "Transfer Note Sent With Success!");

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

            EmailSender.sendEmail(strEmail, "Transfer Number: " + oPharmacyTransfer.getId(), strBody);
            EmailSender.sendEmail(oPharmacyTransfer.getOrder().getPharmacy().getEmail(), "Transfer Number: " + oPharmacyTransfer.getId(), "Delivery Note Sent With Success!");

            return WriteFile.write("DeliveryNote_" + oPharmacyTransfer.getId(), strBody);
        } catch (Exception e){
            return false;
        }
    }

    private String getBody(PharmacyTransfer oPharmacyTransfer, String strNote){
            String strBody = String.format("_______________________________________\n\n\t\t\tTransfer No. %d", oPharmacyTransfer.getId());
            strBody += String.format("\n\t\t\t\t%td-%<tb-%<tY", oPharmacyTransfer.getTransferDate());
            strBody += String.format("\n_______________________________________");

            strBody += String.format("\n\n---------------------------------------");
            strBody += String.format("\n\t\t\tSending Pharmacy:\n---------------------------------------\n\n\n\n\t\t\t%s", oPharmacyTransfer.getNearbyPharmacy().getName());
            strBody += String.format("\n\n\t\t\t%s", oPharmacyTransfer.getNearbyPharmacy().getEmail());
            strBody += String.format("\n\t\t\t%s", oPharmacyTransfer.getNearbyPharmacy().getAddress().getStreetName());
            strBody += String.format("\n\t\t\t%s", oPharmacyTransfer.getNearbyPharmacy().getAddress().getDoorNumber());
            strBody += String.format("\n\t\t\t%s", oPharmacyTransfer.getNearbyPharmacy().getAddress().getPostalCode());
            strBody += String.format("\n\t\t\t%s", oPharmacyTransfer.getNearbyPharmacy().getAddress().getLocality());
            strBody += String.format("\n\t\t\t%s", oPharmacyTransfer.getNearbyPharmacy().getAddress().getCountry());
            strBody += String.format("\n\n\n\n---------------------------------------");
            strBody += String.format("\n\t\t\tReceiving Pharmacy:\n---------------------------------------\n\n\n\n\t\t\t%s", oPharmacyTransfer.getOrder().getPharmacy().getName());
            strBody += String.format("\n\n\t\t\t%s", oPharmacyTransfer.getOrder().getPharmacy().getEmail());
            strBody += String.format("\n\t\t\t%s", oPharmacyTransfer.getOrder().getPharmacy().getAddress().getStreetName());
            strBody += String.format("\n\t\t\t%s", oPharmacyTransfer.getOrder().getPharmacy().getAddress().getDoorNumber());
            strBody += String.format("\n\t\t\t%s", oPharmacyTransfer.getOrder().getPharmacy().getAddress().getPostalCode());
            strBody += String.format("\n\t\t\t%s", oPharmacyTransfer.getOrder().getPharmacy().getAddress().getLocality());
            strBody += String.format("\n\t\t\t%s", oPharmacyTransfer.getOrder().getPharmacy().getAddress().getCountry());
            strBody += String.format("\n\n\n\n---------------------------------------");
            strBody += String.format("\n\t\t\tProduct Ordered");
            strBody += String.format("\n---------------------------------------\n\n\n\n%dx %s", oPharmacyTransfer.getQuantity(), oPharmacyTransfer.getProduct().getName());
            strBody += String.format("\n\n\n\n_______________________________________");
            strBody += String.format("\n\t\t\t\t%s", strNote);
            strBody += String.format("\n_______________________________________");
            strBody += String.format("\n\t\t\t\tTHANK YOU!");
            strBody += String.format("\n_______________________________________");
            return strBody;
    }
}
