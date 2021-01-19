package lapr.project.utils;

import lapr.project.controller.RegisterClientController;
import lapr.project.controller.RegisterPharmacyController;
import lapr.project.controller.RegisterScooterController;
import lapr.project.model.CreditCard;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReader {

    public static final String FILE_CLIENTS = "src/main/resources/files/clients.csv";
    public static final String FILE_SCOOTERS = "src/main/resources/files/escooters.csv";
    public static final String FILE_PHARMACIES = "src/main/resources/files/pharmacies.csv";
    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    public static void readFiles() {
        readGenericFile(FILE_CLIENTS);
    }

    public static void readGenericFile(String path) {
        Scanner sc = null;
        try {
            File file = new File(path);
            sc = new Scanner(file);
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.charAt(0) != '#') {
                    String[] columns = line.split(";");
                    switch (path) {
                        case FILE_CLIENTS:
                            readClientFile(columns);
                            break;
                        case FILE_SCOOTERS:
                            readScooterFile(columns);
                            break;
                        case FILE_PHARMACIES:
                            readPharmacyFile(columns);
                            break;
                    }
                }
            }
            LOGGER.log(Level.INFO, "File was read with success!");
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "There was a problem regarding the file!");
        } finally {
            sc.close();
        }
    }

    public static void readClientFile(String[] columns) {
        RegisterClientController ctrl = new RegisterClientController();
        List<CreditCard> lst = new ArrayList<>();
        try {
            for (int i = 12; i < columns.length - 2; i += 3) {
                lst.add(new CreditCard(Long.parseLong(columns[i]), new SimpleDateFormat("MM-yy").parse(columns[i + 1]),
                        Integer.parseInt(columns[i + 2])));
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "There was a problem in the Credit Card information!");
            return;
        }
        String email = columns[0].trim();
        String pw = columns[1].trim();
        Integer nif = Integer.parseInt(columns[2].trim());
        String name = columns[3].trim();
        Double latitude = Double.parseDouble(columns[4].trim());
        Double longitude = Double.parseDouble(columns[5].trim());
        Double altitude = Double.parseDouble(columns[6].trim());
        String streetName = columns[7].trim();
        String doorNumber = columns[8].trim();
        String postalCode = columns[9].trim();
        String locality = columns[10].trim();
        String country = columns[11].trim();

        if (ctrl.registerNewClient(name,nif,email,pw,latitude,longitude,altitude,streetName,doorNumber,postalCode,
                locality,country, lst)) {
            LOGGER.log(Level.INFO, "Client registered with success!");
        } else LOGGER.log(Level.WARNING, "There was a problem registering a Client");

    }


    public static void readScooterFile(String[] columns) {
        RegisterScooterController ctrl = new RegisterScooterController();
        //TODO: Registar scooter com base no ficheiro csv
    }

    public static void readPharmacyFile(String[] columns) {
        RegisterPharmacyController ctrl = new RegisterPharmacyController();
        //TODO: Registar pharmacy com base no ficheiro csv
    }


}
