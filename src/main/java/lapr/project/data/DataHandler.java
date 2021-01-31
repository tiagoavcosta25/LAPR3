package lapr.project.data;


import lapr.project.model.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

/**
 * Data Handler.
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

public class DataHandler {

    /**
     * Database URL.
     */
    private String jdbcUrl;

    /**
     * Database User.
     */
    private String username;

    /**
     * Database Password.
     */
    private String password;

    /**
     * Database Connection.
     */
    private Connection connection;

    /**
     * "Stored Procedures" Invocation.
     */
    private CallableStatement callStmt;

    /**
     * Result Set of "Stored Procedures".
     */
    private ResultSet rSet;

    /**
     * Additional Number of columns added when executing the pharmacyManager method.
     */
    private static int mCOLUMNSADDEDPHARMACY = 11;

    /**
     * Additional Number of columns added when executing the clientManager method.
     */
    private static int mCOLUMNSADDEDCLIENT = 14;

    /**
     * Additional Number of columns added when executing the orderManager method.
     */
    private static int mCOLUMNSADDEDORDER = 37;

    /**
     * Additional Number of columns added when executing the productManager method.
     */
    private static int mCOLUMNSADDEDPRODUCT = 5;

    /**
     * Additional Number of columns added when executing the vehicleModelManager method.
     */
    private static int mCOLUMNSADDEDVEHICLEMODEL = 10;

    /**
     * Use connection properties set on file application.properties
     */
    public DataHandler() {
        try {
            Properties properties =
                    new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
        }

        this.jdbcUrl = System.getProperty("database.url");
        this.username = System.getProperty("database.username");
        this.password = System.getProperty("database.password");
    }

    /**
     * Creates a DataHandler instance reciving Database URL, User Name and Password.
     *
     * @param jdbcUrl  Database URL.
     * @param username User Name.
     * @param password User Password.
     */
    public DataHandler(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        connection = null;
        callStmt = null;
        rSet = null;
    }

    /**
     * Open a Connection the the DataBase.
     */
    protected void openConnection() {
        try {
            connection = DriverManager.getConnection(
                    jdbcUrl, username, password);
        } catch (SQLException e) {
            // Do Nothing
        }
    }

    /**
     * Close the ResultSet, CallableStatement e Connection, and returns an error message if anything goes wrong.
     * Otherwise returns an empty String.
     */
    protected String closeAll() {

        StringBuilder message = new StringBuilder();

        if (rSet != null) {
            try {
                rSet.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            rSet = null;
        }

        if (callStmt != null) {
            try {
                callStmt.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            callStmt = null;
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            connection = null;
        }
        return message.toString();
    }

    /**
     * Returns the Database Connection.
     * @return the Database Connection.
     */
    protected Connection getConnection() {
        if (connection == null)
            openConnection();
        return connection;
    }

    /**
     * Generic method that converts Data in a ResultSet from the Database to a Java Object related to an Address.
     * @param rSet ResultSet.
     * @param firstColumn Column that the Data starts.
     * @return Address.
     * @throws SQLException SQLException.
     */
    protected Address addressManager(ResultSet rSet, int firstColumn) throws SQLException { // column number +8
        Double latitude = rSet.getDouble(firstColumn);
        firstColumn++;
        Double longitude = rSet.getDouble(firstColumn);
        firstColumn++;
        Double altitude = rSet.getDouble(firstColumn);
        firstColumn++;
        String doorNumber = rSet.getString(firstColumn);
        firstColumn++;
        String streetName = rSet.getString(firstColumn);
        firstColumn++;
        String postalCode = rSet.getString(firstColumn);
        firstColumn++;
        String locality = rSet.getString(firstColumn);
        firstColumn++;
        String country = rSet.getString(firstColumn);
        return new Address(latitude, longitude, altitude, streetName, doorNumber, postalCode, locality, country);
    }

    /**
     * Generic method that converts Data in a ResultSet from the Database to a Java Object related to a Credit Card.
     * @param rSet ResultSet.
     * @param firstColumn Column that the Data starts.
     * @return Credit Card.
     * @throws SQLException SQLException.
     */
    protected CreditCard creditCardManager(ResultSet rSet, int firstColumn) throws SQLException { // column number +3
        long dblCreditCardNr = rSet.getLong(firstColumn);
        firstColumn++;
        java.sql.Date sqlStartDate = rSet.getDate(firstColumn);
        java.util.Date utilStartDate = new java.util.Date(sqlStartDate.getTime());
        firstColumn++;
        Integer strCCV = rSet.getInt(firstColumn);
        return new CreditCard(dblCreditCardNr, utilStartDate, strCCV);
    }

    /**
     * Generic method that converts Data in a ResultSet from the Database to a Java Object related to a Pharmacy.
     * @param rSet ResultSet.
     * @param firstColumn Column that the Data starts.
     * @return Pharmacy.
     * @throws SQLException SQLException.
     */
    protected Pharmacy pharmacyManager(ResultSet rSet, int firstColumn) throws SQLException { // column number +11

        Integer pharmacyID = rSet.getInt(firstColumn);
        firstColumn++;
        String pharmacyName = rSet.getString(firstColumn);
        firstColumn++;
        String strEmail = rSet.getString(firstColumn);
        firstColumn++;
        Address oAddress = addressManager(rSet, firstColumn);

        return new Pharmacy(pharmacyID, pharmacyName, strEmail, oAddress);
    }

    /**
     * Generic method that converts Data in a ResultSet from the Database to a Java Object related to a Client.
     * @param rSet ResultSet.
     * @param firstColumn Column that the Data starts.
     * @return Client.
     * @throws SQLException SQLException.
     */
    protected Client clientManager(ResultSet rSet, int firstColumn) throws SQLException { // column number +14

        int intId = rSet.getInt(firstColumn);
        firstColumn++;
        String strEmail = rSet.getString(firstColumn);
        firstColumn++;
        String strPassword = rSet.getString(firstColumn);
        firstColumn++;
        Integer strNif = rSet.getInt(firstColumn);
        firstColumn++;
        String strName = rSet.getString(firstColumn);
        firstColumn++;
        Integer intCredits = rSet.getInt(firstColumn);
        firstColumn++;
        Address oClientAddress = addressManager(rSet, firstColumn);

        return new Client(intId, strName, strNif, strEmail, strPassword, intCredits, oClientAddress, new ArrayList<>());
    }

    /**
     * Generic method that converts Data in a ResultSet from the Database to a Java Object related to an Order.
     * @param rSet ResultSet.
     * @param firstColumn Column that the Data starts.
     * @return Order.
     * @throws SQLException SQLException.
     */
    protected Order orderManager(ResultSet rSet, int firstColumn) throws SQLException { // column number +37

        int intId = rSet.getInt(firstColumn);
        firstColumn++;
        String strDescription = rSet.getString(firstColumn);
        firstColumn++;
        String strStatus = rSet.getString(firstColumn);
        firstColumn++;
        Date dtOrderDate = rSet.getDate(firstColumn);
        firstColumn++;
        double dblTotalWeight = rSet.getDouble(firstColumn);
        firstColumn++;
        double dblAmount = rSet.getDouble(firstColumn);
        firstColumn++;
        double dblAdditionalFee = rSet.getDouble(firstColumn);
        firstColumn++;
        boolean blIsHomeDelivery = rSet.getBoolean(firstColumn);
        firstColumn++;
        Client oClient = clientManager(rSet, firstColumn);
        firstColumn+= mCOLUMNSADDEDCLIENT;
        Pharmacy oPharmacy = pharmacyManager(rSet, firstColumn);


        return new Order(intId, dblAmount, dblTotalWeight, dblAdditionalFee, dtOrderDate, strDescription,
                strStatus, blIsHomeDelivery, oClient, oPharmacy, new TreeMap<>());
    }


    /**
     * Generic method that converts Data in a ResultSet from the Database to a Java Object related to an Invoice.
     * @param rSet ResultSet.
     * @param firstColumn Column that the Data starts.
     * @return Invoice.
     * @throws SQLException SQLException.
     */
    protected Invoice invoiceManager(ResultSet rSet, int firstColumn) throws SQLException { // column number +47

        Order oOrder = orderManager(rSet, firstColumn);
        firstColumn+= mCOLUMNSADDEDORDER;
        int intInvoiceId = rSet.getInt(firstColumn);
        firstColumn++;
        Date dtInvoiceDate = rSet.getDate(firstColumn);
        firstColumn++;
        double dblTotalPrice = rSet.getDouble(firstColumn);


        return new Invoice(intInvoiceId, dtInvoiceDate, dblTotalPrice, oOrder);
    }


    /**
     * Generic method that converts Data in a ResultSet from the Database to a Java Object related to a Product.
     * @param rSet ResultSet.
     * @param firstColumn Column that the Data starts.
     * @return Product.
     * @throws SQLException SQLException.
     */
    protected Product productManager(ResultSet rSet, int firstColumn) throws SQLException { // column number +5

        int intID = rSet.getInt(firstColumn);
        firstColumn++;
        String strName = rSet.getString(firstColumn);
        firstColumn++;
        String strDescription = rSet.getString(firstColumn);
        firstColumn++;
        Double dblUnitaryPrice = rSet.getDouble(firstColumn);
        firstColumn++;
        Double dblUnitaryWeight = rSet.getDouble(firstColumn);


        return new Product(intID, strName, strDescription, dblUnitaryPrice, dblUnitaryWeight);
    }

    /**
     * Generic method that adds Stock from Data in a ResultSet from the Database to a Java Object
     * related to a Pharmacy.
     * @param rSet ResultSet.
     * @param firstColumn Column that the Data starts.
     * @param oPharmacy Pharmacy.
     * @return Pharmacy.
     * @throws SQLException SQLException.
     */
    protected Pharmacy pharmacyProductManager(ResultSet rSet, int firstColumn, Pharmacy oPharmacy) throws SQLException { // column number +6

        int intStock = rSet.getInt(firstColumn);
        firstColumn++;
        Product oProduct = productManager(rSet, firstColumn);

        oPharmacy.getStock().put(oProduct, intStock);

        return oPharmacy;
    }

    /**
     * Generic method that adds a Produtc from Data in a ResultSet from the Database to a Java Object
     * related to an Order.
     * @param rSet ResultSet.
     * @param firstColumn Column that the Data starts.
     * @param oOrder Order.
     * @return Order.
     * @throws SQLException SQLException.
     */
    protected Order orderProductManager(ResultSet rSet, int firstColumn, Order oOrder) throws SQLException { // column number +6

        int intQuantity = rSet.getInt(firstColumn);
        firstColumn++;
        Product oProduct = productManager(rSet, firstColumn);

        oOrder.getProducts().put(oProduct, intQuantity);

        return oOrder;
    }

    /**
     * Generic method that adds Product fromm Data in a ResultSet from the Database to a Java Object
     * related to an Invoice.
     * @param rSet ResultSet.
     * @param firstColumn Column that the Data starts.
     * @param oInvoice Invoice.
     * @return Invoice.
     * @throws SQLException SQLException.
     */
    protected Invoice invoiceProductManager(ResultSet rSet, int firstColumn, Invoice oInvoice) throws SQLException { // column number +6

        double intValue = rSet.getDouble(firstColumn);
        firstColumn++;
        CreditCard oCreditCard = creditCardManager(rSet, firstColumn);

        oInvoice.getPayments().put(oCreditCard, intValue);

        return oInvoice;
    }


    /**
     * Generic method that converts Data in a ResultSet from the Database to a Java Object related to a Scooter.
     * @param rSet ResultSet.
     * @param firstColumn Column that the Data starts.
     * @return Scooter.
     * @throws SQLException SQLException.
     */
    protected Scooter scooterManager(ResultSet rSet, int firstColumn) throws SQLException { // column number +23

        int intId = rSet.getInt(firstColumn);
        firstColumn++;
        double dblBatteryPerc = rSet.getDouble(firstColumn);
        firstColumn++;
        VehicleModel oVehicleModel = vehicleModelManager(rSet,firstColumn);
        firstColumn+= mCOLUMNSADDEDVEHICLEMODEL;
        Pharmacy oPharmacy = pharmacyManager(rSet,firstColumn);

        return new Scooter(intId, dblBatteryPerc, oVehicleModel, oPharmacy);
    }


    /**
     * Generic method that converts Data in a ResultSet from the Database to a Java Object related to a Drone.
     * @param rSet ResultSet.
     * @param firstColumn Column that the Data starts.
     * @return Drone.
     * @throws SQLException SQLException.
     */
    protected Drone droneManager(ResultSet rSet, int firstColumn) throws SQLException { // column number +23

        int intId = rSet.getInt(firstColumn);
        firstColumn++;
        double dblBatteryPerc = rSet.getDouble(firstColumn);
        firstColumn++;
        VehicleModel oVehicleModel = vehicleModelManager(rSet,firstColumn);
        firstColumn+= mCOLUMNSADDEDVEHICLEMODEL;
        Pharmacy oPharmacy = pharmacyManager(rSet,firstColumn);

        return new Drone(intId, dblBatteryPerc, oVehicleModel, oPharmacy);
    }


    /**
     * Generic method that converts Data in a ResultSet from the Database to a Java Object related to a Vehicle Model.
     * @param rSet ResultSet.
     * @param firstColumn Column that the Data starts.
     * @return Vehicle Model.
     * @throws SQLException SQLException.
     */
    protected VehicleModel vehicleModelManager(ResultSet rSet, int firstColumn) throws SQLException { // column number +10

        int intId = rSet.getInt(firstColumn);
        firstColumn++;
        String strDesignation = rSet.getString(firstColumn);
        firstColumn++;
        double dblPotency = rSet.getDouble(firstColumn);
        firstColumn++;
        double dblWeight = rSet.getDouble(firstColumn);
        firstColumn++;
        double dblMaxPayload = rSet.getDouble(firstColumn);
        firstColumn++;
        VehicleType oVehicleType = VehicleType.getTypeByDesignation(rSet.getString(firstColumn));
        firstColumn++;
        Battery oBattery = batteryManager(rSet,firstColumn);

        return new VehicleModel(intId, strDesignation, dblPotency, dblWeight, dblMaxPayload, oBattery, oVehicleType);
    }


    /**
     * Generic method that converts Data in a ResultSet from the Database to a Java Object related to a Battery.
     * @param rSet ResultSet.
     * @param firstColumn Column that the Data starts.
     * @return Battery.
     * @throws SQLException SQLException.
     */
    protected Battery batteryManager(ResultSet rSet, int firstColumn) throws SQLException { // column number +4

        int intBatteryId = rSet.getInt(firstColumn);
        firstColumn++;
        double dblEfficiency = rSet.getDouble(firstColumn);
        firstColumn++;
        int intBatteryCapacity = rSet.getInt(firstColumn);
        firstColumn++;
        double dblBatteryVoltage = rSet.getDouble(firstColumn);

        return new Battery(intBatteryId, intBatteryCapacity, dblBatteryVoltage, dblEfficiency);
    }

    /**
     * Generic method that converts Data in a ResultSet from the Database to a Java Object related to a Pharmacy Transfer.
     * @param rSet ResultSet.
     * @param firstColumn Column that the Data starts.
     * @return Pharmacy Transfer.
     * @throws SQLException SQLException.
     */
    protected PharmacyTransfer pharmacyTransferManager(ResultSet rSet, int firstColumn) throws SQLException { // column number +4

        int intId = rSet.getInt(firstColumn);
        firstColumn++;
        Date dtDate = rSet.getDate(firstColumn);
        firstColumn++;
        int intQuantity = rSet.getInt(firstColumn);
        firstColumn++;
        Product oProduct = productManager(rSet, firstColumn);
        firstColumn += mCOLUMNSADDEDPRODUCT;
        Pharmacy oNearbyPharmacy = pharmacyManager(rSet, firstColumn);
        firstColumn += mCOLUMNSADDEDPHARMACY;
        Order oOrder = orderManager(rSet, firstColumn);

        return new PharmacyTransfer(intId, dtDate, oOrder, oProduct, intQuantity, oNearbyPharmacy);
    }

    /**
     * Returns the Database URL.
     * @return the Database URL.
     */
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    /**
     * Modifies the Database URL.
     * @param jdbcUrl the Database URL.
     */
    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    /**
     * Returns the Database Username.
     * @return the Database Username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Modifies the Database Username.
     * @param username the Database Username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the Database Password.
     * @return the Database Password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Modifies the Database Password.
     * @param password the Database Password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
