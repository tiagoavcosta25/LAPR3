package lapr.project.model.service;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.CourierDB;
import lapr.project.model.Courier;
import lapr.project.model.PassGenerator;
import lapr.project.model.Pharmacy;
import lapr.project.utils.Constants;
import lapr.project.utils.DirectoryVerification;
import lapr.project.utils.EmailSender;
import lapr.project.utils.TimeCalculator;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;

public class CourierService {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(CourierService.class.getName());

    private CourierDB oCourierDB;

    public CourierService() {
        oCourierDB = new CourierDB();
    }

    public Courier newCourier(String strName, String strEmail, Integer strNIF, String strIBAN, Pharmacy oPharmacy) throws NoSuchAlgorithmException {
        PassGenerator pass = new PassGenerator();
        String password = pass.generatePassword();

        return new Courier(strName, strEmail, password, strNIF, strIBAN, oPharmacy);
    }

    public boolean registersCourier(Courier oCourier) {
        return oCourierDB.addCourierToDB(oCourier.getName(), oCourier.getEmail(), oCourier.getPw(), oCourier.getNif(), oCourier.getM_iban(), oCourier.getM_Pharmacy().getId());
    }

    public Courier getCourierByID(Integer intID) {
        return oCourierDB.getCourierByID(intID);
    }

    public Courier updateCourier(Courier courier, String strName, String strEmail, Integer intNif, String strIban, Pharmacy oPharmacy) {
        if (!strName.isEmpty()) courier.setName(strName);
        if (!strEmail.isEmpty()) courier.setEmail(strEmail);
        if (!(intNif == 0)) courier.setNif(intNif);
        if (!strIban.isEmpty()) courier.setM_iban(strIban);
        if (oPharmacy != null) courier.setM_Pharmacy(oPharmacy);
        return courier;
    }

    public boolean updateCourierDB(Courier oCourier) {
        return oCourierDB.updateCourierDB(oCourier);
    }

    public boolean removeCourier(Integer id) {
        try {
            return oCourierDB.removeCourier(id);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean parkScooter(int intIdScooter) {
        String estimateFileName = DirectoryVerification.verifyFileCreation(Constants.ESTIMATE_FILE_PATH,
                Constants.ESTIMATE_FILE_FILTER, 50);

        if(estimateFileName.equals(""))
            return false;

        double estimate = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(Constants.ESTIMATE_FILE_PATH + "/" + estimateFileName))) {
            String strCurrentLine;
            if ((strCurrentLine = br.readLine()) != null) {
                estimate = Double.parseDouble(strCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Integer> time = TimeCalculator.calculateTime(estimate);

        int hours = time.get(0);
        int minutes = time.get(1);
        int seconds = time.get(2);

        LocalDateTime lt = LocalDateTime.now();
        lt = lt.plusSeconds(seconds);
        lt = lt.plusMinutes(minutes);
        lt = lt.plusHours(hours);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        String formattedDateTime = lt.format(formatter); //

        boolean flag = oCourierDB.parkScooter(intIdScooter);

        if(flag) {
            EmailSender.sendEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail(),
                    "Scooter Parked", String.format("The scooter number %d was parked successfully!\n__________" +
                                    "_________________________________________________________\n\n" + "Estimated charging " +
                                    "time: %d hours, %d minutes %d seconds.\nEstimated time for full charge: %s.\n__________" +
                                    "_________________________________________________________\n\n" + "Thank you for " +
                                    "choosing us.\nKing regards,\nPharmacy Service G21.", intIdScooter, time.get(0), time.get(1),
                            time.get(2), formattedDateTime));

            File file = new File(Constants.ESTIMATE_FILE_PATH + "/" + estimateFileName);
            if (file.delete()) {
                file = new File(Constants.ESTIMATE_FILE_PATH + "/" + estimateFileName + Constants.ESTIMATE_FILE_FILTER);
                if (file.delete())
                    LOGGER.log(Level.INFO, "File handled successfully!");
            }
        }
        return flag;
    }

}
