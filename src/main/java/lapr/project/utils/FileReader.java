package lapr.project.utils;
/*
import lapr.project.controller.*;
import lapr.project.model.CreditCard;
import lapr.project.model.Pharmacy;
import lapr.project.model.Product;
import lapr.project.model.UserSession;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReader {

    public static final String FILECLIENTS = "src/main/resources/files/clients.csv";
    public static final String FILEPATHS = "src/main/resources/files/paths.csv";
    public static final String FILESCOOTERS = "src/main/resources/files/escooters.csv";
    public static final String FILEREMOVESCOOTERS = "src/main/resources/files/removeScooters.csv";
    public static final String FILEDRONES = "src/main/resources/files/drones.csv";
    public static final String FILEREMOVEDRONES = "src/main/resources/files/removeDrones.csv";
    public static final String FILEPHARMACIES = "src/main/resources/files/pharmacies.csv";
    public static final String FILEPHARMACYPRODUCTS = "src/main/resources/files/pharmacyProducts.csv";
    public static final String FILEORDERS = "src/main/resources/files/orders.csv";
    public static final String FILECOURIERS = "src/main/resources/files/couriers.csv";
    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    public static void readFiles() {
        readGenericFile(FILECLIENTS);
        readGenericFile(FILEPATHS);
        readGenericFile(FILESCOOTERS);
        readGenericFile(FILEDRONES);
        readGenericFile(FILEORDERS);
        readGenericFile(FILEPHARMACIES);
        readGenericFile(FILEPHARMACYPRODUCTS);
        readGenericFile(FILECOURIERS);
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
                        case FILECLIENTS:
                            readClientFile(columns);
                            break;
                        case FILESCOOTERS:
                            readScooterFile(columns);
                            break;
                        case FILEREMOVESCOOTERS:
                            readRemoveScooterFile(columns);
                            break;
                        case FILEDRONES:
                            readDroneFile(columns);
                            break;
                        case FILEREMOVEDRONES:
                            readRemoveDroneFile(columns);
                            break;
                        case FILEPHARMACIES:
                            readPharmacyFile(columns);
                            break;
                        case FILEPHARMACYPRODUCTS:
                            readPharmacyProductFile(columns);
                            break;
                        case FILEORDERS:
                            readOrderFile(columns);
                            break;
                        case FILEPATHS:
                            readPathFile(columns);
                            break;
                        case FILECOURIERS:
                            readCourierFile(columns);
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

    private static void readClientFile(String[] columns) {
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

        if (ctrl.registerNewClient(columns[3].trim(),Integer.parseInt(columns[2].trim()),columns[0].trim(),columns[1].trim(),Double.parseDouble(columns[4].trim())
                ,Double.parseDouble(columns[5].trim()),Double.parseDouble(columns[6].trim()),columns[7].trim(),columns[8].trim(),columns[9].trim(),
                columns[10].trim(),columns[11].trim(), lst)) {
            LOGGER.log(Level.INFO, "Client registered with success!");
        } else LOGGER.log(Level.WARNING, "There was a problem registering a Client");
    }

    private static void readPathFile(String[] columns) {
        RegisterPathController ctrl = new RegisterPathController();

        if (ctrl.registerPath(Double.parseDouble(columns[0]),Double.parseDouble(columns[1]),Double.parseDouble(columns[2]),
                Double.parseDouble(columns[3]),columns[4], Double.parseDouble(columns[5]),Double.parseDouble(columns[6]),
                Double.parseDouble(columns[7]))) {
            LOGGER.log(Level.INFO,"Path was registered with success!");
        }else LOGGER.log(Level.WARNING,"There was a problem registering a Path");
    }


    private static void readScooterFile(String[] columns) {
        RegisterScooterController oCtrl = new RegisterScooterController();

        if (oCtrl.setPharmacy(columns[0])) {
            if (!oCtrl.setVehicleModel(columns[1])) {
                oCtrl.newVehicleModel(columns[1], Double.parseDouble(columns[2]), Double.parseDouble(columns[3]),
                        Double.parseDouble(columns[4]), Integer.parseInt(columns[5]), Double.parseDouble(columns[6]),
                        Double.parseDouble(columns[7]));
            }
            oCtrl.newScooter();
            if (oCtrl.registersScooter()) {
                LOGGER.log(Level.INFO, "Scooter was registered with success!");
            } else LOGGER.log(Level.WARNING, "There was a problem registering a Scooter");
        } else LOGGER.log(Level.WARNING, "There was a problem registering a Scooter");
    }

    private static void readRemoveScooterFile(String[] columns) {
        RemoveScooterController oCtrl = new RemoveScooterController();

        if (oCtrl.removeScooter(Integer.parseInt(columns[0]))) {
            LOGGER.log(Level.INFO, "Scooter was removed with success!");
        } else LOGGER.log(Level.WARNING, "There was a problem removing a Scooter");
    }

    private static void readDroneFile(String[] columns) {
        RegisterDroneController oCtrl = new RegisterDroneController();

        if (oCtrl.setPharmacy(columns[0])) {
            if (!oCtrl.setVehicleModel(columns[1])) {
                oCtrl.newVehicleModel(columns[1], Double.parseDouble(columns[2]), Double.parseDouble(columns[3]),
                        Double.parseDouble(columns[4]), Integer.parseInt(columns[5]), Double.parseDouble(columns[6]),
                        Double.parseDouble(columns[7]));
            }
            oCtrl.newDrone();
            if (oCtrl.registersDrone()) {
                LOGGER.log(Level.INFO, "Drone was registered with success!");
            } else LOGGER.log(Level.WARNING, "There was a problem registering a Drone");
        } else LOGGER.log(Level.WARNING, "There was a problem registering a Drone");
    }

    private static void readRemoveDroneFile(String[] columns) {
        RemoveDroneController oCtrl = new RemoveDroneController();

        if (oCtrl.removeDrone(Integer.parseInt(columns[0]))) {
            LOGGER.log(Level.INFO, "Drone was removed with success!");
        } else LOGGER.log(Level.WARNING, "There was a problem removing a Drone");
    }

    private static void readPharmacyFile(String[] columns) {
        RegisterPharmacyController oCtrl = new RegisterPharmacyController();
        oCtrl.newPharmacy(columns[0], columns[1], Double.parseDouble(columns[2]), Double.parseDouble(columns[3]),
                Double.parseDouble(columns[4]), columns[5], columns[6], columns[7], columns[8], columns[9]);

        if (oCtrl.registerPharmacy()) {
            LOGGER.log(Level.INFO,"Pharmacy was registered with success!");
        }else LOGGER.log(Level.WARNING,"There was a problem registering a Pharmacy.");
    }

    private static void readPharmacyProductFile(String[] columns) {
        AddPharmacyProductController oCtrl = new AddPharmacyProductController();

        List<Product> lstProducts = oCtrl.getProducts();
        Product oProduct = null;

        for(Product p : lstProducts){
            if(p.hasName(columns[1])){
                oProduct = p;
                break;
            }
        }

        if(oProduct != null){
            oCtrl.addPharmacyProduct(columns[0], oProduct, Integer.parseInt(columns[2]));

            if (oCtrl.registerPharmacyProduct()) {
                LOGGER.log(Level.INFO,"Product added to the Pharmacy with success!");
            }else LOGGER.log(Level.WARNING,"There was a problem adding the Product to the Pharmacy.");
        }else LOGGER.log(Level.WARNING,"There was a problem adding the Product to the Pharmacy.");
    }

    private static void readOrderFile(String[] columns) {
        MakeAnOrderController oCtrl = new MakeAnOrderController();

        ApplicationPOT.getInstance().setCurrentSession(new UserSession(columns[0]));

        List<Pharmacy> lstPharmacies = oCtrl.getPharmacies();
        Pharmacy oPharmacy = null;

        for(Pharmacy p : lstPharmacies){
            if(p.hasEmail(columns[3])){
                oPharmacy = p;
                break;
            }
        }

        List<Product> lstProducts = oCtrl.getAvailableProducts(oPharmacy);

        int startingCCIndex = -1;
        Product oProduct;
        for(int i = 4; i < columns.length; i += 2){
            oProduct = null;
            for(Product p : lstProducts){
                if(p.hasName(columns[i])){
                    oProduct = p;
                    break;
                }
            }
            if(oProduct == null){
                startingCCIndex = i;
                break;
            }
            oCtrl.addProductToOrder(oProduct, Integer.parseInt(columns[i + 1]));
        }

        List<CreditCard> lstCCs = oCtrl.getCreditCardsByClient();

        if(startingCCIndex != -1){
            CreditCard oCreditCard;
            for(int i = startingCCIndex; i < columns.length; i += 2){
                oCreditCard = null;
                for(CreditCard c : lstCCs){
                    if(c.hasNumber(Long.parseLong(columns[i]))){
                        oCreditCard = c;
                        break;
                    }
                }
                oCtrl.addPayment(oCreditCard, Double.parseDouble(columns[i + 1]));
            }

            oCtrl.newOrder(columns[1], Boolean.valueOf(columns[2]));

            if (oCtrl.registerOrder()) {
                LOGGER.log(Level.INFO,"Order was registered with success!");
            }else LOGGER.log(Level.WARNING,"There was a problem registering an Order.");
        }
    }

    public static void readCourierFile(String[] columns) {
        RegisterCourierController ctrl = new RegisterCourierController();
        if (ctrl.newCourier(columns[0],columns[1],Integer.parseInt(columns[2]),columns[3],columns[4])) {
            LOGGER.log(Level.INFO,"Courier was created with success!");
            if(ctrl.registersCourier()){
                LOGGER.log(Level.INFO,"Courier was registered with success!");
            }else LOGGER.log(Level.INFO,"There was a problem registering a Courier");
        }else LOGGER.log(Level.WARNING,"There was a problem creating a Courier");

    }
}*/
