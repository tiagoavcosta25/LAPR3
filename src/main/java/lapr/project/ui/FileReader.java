package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.model.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReader {

    public static final String FILECLIENTS = "src/main/resources/files/input/clients.csv";
    public static final String FILEPATHS = "src/main/resources/files/input/paths.csv";
    public static final String FILESCOOTERS = "src/main/resources/files/input/escooters.csv";
    public static final String FILEREMOVESCOOTERS = "src/main/resources/files/input/removeScooters.csv";
    public static final String FILEDRONES = "src/main/resources/files/input/drones.csv";
    public static final String FILEREMOVEDRONES = "src/main/resources/files/input/removeDrones.csv";
    public static final String FILEPHARMACIES = "src/main/resources/files/input/pharmacies.csv";
    public static final String FILEPRODUCTS = "src/main/resources/files/input/products.csv";
    public static final String FILEPHARMACYPRODUCTS = "src/main/resources/files/input/pharmacyProducts.csv";
    public static final String FILEPARKS = "src/main/resources/files/input/parks.csv";
    public static final String FILEORDERS = "src/main/resources/files/input/orders.csv";
    public static final String FILECOURIERS = "src/main/resources/files/input/couriers.csv";
    public static final String FILEDELIVERYRUNS = "src/main/resources/files/input/deliveryruns.csv";
    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    public static void readFiles() {
        readGenericFile(FILECLIENTS);
        readGenericFile(FILEPHARMACIES);
        readGenericFile(FILEPRODUCTS);
        readGenericFile(FILEPHARMACYPRODUCTS);
        readGenericFile(FILEPARKS);
        readGenericFile(FILECOURIERS);
        readGenericFile(FILESCOOTERS);
        readGenericFile(FILEDRONES);
        readGenericFile(FILEPATHS);
        ApplicationPOT.getInstance().getWorldMap().createGraph();
        readGenericFile(FILEORDERS);
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
                        case FILEPRODUCTS:
                            readProductFile(columns);
                            break;
                        case FILEPHARMACYPRODUCTS:
                            readPharmacyProductFile(columns);
                            break;
                        case FILEPARKS:
                            readParkFile(columns);
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
                        case FILEDELIVERYRUNS:
                            readDeliveryRunFile(columns);
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
        VehicleType oType = null;
        if (columns[8].equals("Scooter")) oType = VehicleType.SCOOTER;
        if (columns[8].equals("Drone")) oType = VehicleType.DRONE;

        if (ctrl.registerPath(Double.parseDouble(columns[0]),Double.parseDouble(columns[1]),Double.parseDouble(columns[2]),
                Double.parseDouble(columns[3]),columns[4], Double.parseDouble(columns[5]),Double.parseDouble(columns[6]),
                Double.parseDouble(columns[7]),oType)) {
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
        oCtrl.newPharmacy(columns[0].trim(), columns[1].trim(), Double.parseDouble(columns[2].trim()), Double.parseDouble(columns[3].trim()),
                Double.parseDouble(columns[4].trim()), columns[5].trim(), columns[6].trim(), columns[7].trim(), columns[8].trim(), columns[9].trim());

        if (oCtrl.registerPharmacy()) {
            LOGGER.log(Level.INFO,"Pharmacy was registered with success!");
        }else LOGGER.log(Level.WARNING,"There was a problem registering a Pharmacy.");
    }

    private static void readProductFile(String[] columns) {
        RegisterProductController oCtrl = new RegisterProductController();
        LogoutController oLogoutCtrl = new LogoutController();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("admin@gmail.com", 2));

        if (oCtrl.registerProduct(columns[0],columns[1],Double.parseDouble(columns[2]),Double.parseDouble(columns[3]))) {
            LOGGER.log(Level.INFO, "Product was successfully registered!");
        }else LOGGER.log(Level.WARNING,"There was a problem registering the Product");
        oLogoutCtrl.logout();
    }

    private static void readPharmacyProductFile(String[] columns) {
        AddPharmacyProductController oCtrl = new AddPharmacyProductController();

        List<Product> lstProducts = oCtrl.getProducts();
        Product oProduct = null;

        for(Product p : lstProducts){
            if(p.hasName(columns[1].trim())){
                oProduct = p;
                break;
            }
        }

        if(oProduct != null){
            oCtrl.addPharmacyProduct(columns[0].trim(), oProduct, Integer.parseInt(columns[2].trim()));

            if (oCtrl.registerPharmacyProduct()) {
                LOGGER.log(Level.INFO,"Product added to the Pharmacy with success!");
            }else LOGGER.log(Level.WARNING,"There was a problem adding the Product to the Pharmacy.");
        }else LOGGER.log(Level.WARNING,"There was a problem adding the Product to the Pharmacy.");
    }

    public static void readParkFile(String[] columns) {
        RegisterParkController oCtrl = new RegisterParkController();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("admin@gmail.com", UserSession.Role.ADMIN));
        VehicleType oType = null;
        if (columns[3].equals("Scooter"))
            oType = VehicleType.SCOOTER;
        else if (columns[3].equals("Drone")) oType = VehicleType.DRONE;
        else LOGGER.log(Level.WARNING,"There was a problem with the Vehicle Type!");

        if (oCtrl.addPark(columns[0],Integer.parseInt(columns[1]),Double.parseDouble(columns[2]),oType,
                Integer.parseInt(columns[4]),Integer.parseInt(columns[5]))) {
            LOGGER.log(Level.INFO,"Park added with success!");
        }else LOGGER.log(Level.WARNING,"There was a problem adding the Park.");
        ApplicationPOT.getInstance().clearCurrentSession();
    }

    private static void readOrderFile(String[] columns) {
        MakeAnOrderController oCtrl = new MakeAnOrderController();

        ApplicationPOT.getInstance().setCurrentSession(new UserSession(columns[0], UserSession.Role.CLIENT));

        List<Product> lstProducts = oCtrl.getAvailableProducts();

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

            ApplicationPOT.getInstance().clearCurrentSession();
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

    public static void readDeliveryRunFile(String [] columns) {
        RegisterDeliveryRunController ctrl = new RegisterDeliveryRunController();
        List<Order> lstOrders = ctrl.getOrdersList(columns[0]);
        if(ctrl.registerDeliveryRun(lstOrders,true,false)) {
            LOGGER.log(Level.INFO,"Delivery Run registered with success!");
        }else LOGGER.log(Level.WARNING,"There was a problem registering the delivery run.");
    }
}