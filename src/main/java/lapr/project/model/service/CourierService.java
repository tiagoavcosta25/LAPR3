package lapr.project.model.service;

import lapr.project.data.CourierDB;
import lapr.project.model.Courier;
import lapr.project.model.PassGenerator;
import lapr.project.model.Pharmacy;

import java.security.NoSuchAlgorithmException;

public class CourierService {

    private CourierDB oCourierDB;

    public CourierService() {
        oCourierDB = new CourierDB();
    }

    public Courier newCourier(String strName, String strEmail, Integer strNIF, String strIBAN, Pharmacy oPharmacy) throws NoSuchAlgorithmException {
        PassGenerator pass = new PassGenerator();
        String password = pass.generatePassword();

        return new Courier(strName, strEmail, password, strNIF, strIBAN,oPharmacy);
    }

    public boolean registersCourier(Courier oCourier) {
        return oCourierDB.addCourierToDB(oCourier.getName(), oCourier.getEmail(), oCourier.getPw(), oCourier.getNif(), oCourier.getM_iban(), oCourier.getM_Pharmacy().getId());
    }

    public Courier getCourierByID(Integer intID) {
        return oCourierDB.getCourierByID(intID);
    }

    public Courier updateCourier(Courier courier, String strName, String strEmail, Integer intNif, String strIban, Pharmacy oPharmacy) {
        if(!strName.isEmpty()) courier.setName(strName);
        if(!strEmail.isEmpty()) courier.setEmail(strEmail);
        if(!(intNif == 0)) courier.setNif(intNif);
        if(!strIban.isEmpty()) courier.setM_iban(strIban);
        if(oPharmacy == null) courier.setM_Pharmacy(oPharmacy);
        return courier;
    }

    public boolean updateCourierDB(Courier oCourier) {
        return oCourierDB.updateCourierDB(oCourier);
    }
}
