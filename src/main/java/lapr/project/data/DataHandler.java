package lapr.project.data;


import lapr.project.model.*;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

/**
 * Exemplo de classe cujas instâncias manipulam dados de BD Oracle.
 */
public class DataHandler {
    /**
     * O URL da BD.
     */
    private String jdbcUrl;

    /**
     * O nome de utilizador da BD.
     */
    private String username;

    /**
     * A password de utilizador da BD.
     */
    private String password;

    /**
     * A ligação à BD.
     */
    private Connection connection;

    /**
     * A invocação de "stored procedures".
     */
    private CallableStatement callStmt;

    /**
     * Conjunto de resultados retornados por "stored procedures".
     */
    private ResultSet rSet;

    /**
     * Additional Number of columns added when executing the addressManager method.
     */
    private static int COLUMNSADDEDADDRESS = 8;

    /**
     * Additional Number of columns added when executing the creditCardManager method.
     */
    private static int COLUMNSADDEDCC = 3;

    /**
     * Additional Number of columns added when executing the pharmacyManager method.
     */
    private static int COLUMNSADDEDPHARMACY = 11;

    /**
     * Additional Number of columns added when executing the clientManager method.
     */
    private static int COLUMNSADDEDCLIENT = 14;

    /**
     * Additional Number of columns added when executing the orderManager method.
     */
    private static int COLUMNSADDEDORDER = 37;

    /**
     * Additional Number of columns added when executing the invoiceManager method.
     */
    private static int COLUMNSADDEDINVOICE = 47;

    /**
     * Additional Number of columns added when executing the productManager method.
     */
    private static int COLUMNSADDEDPRODUCT = 5;

    /**
     * Additional Number of columns added when executing the pharmacyProductManager method.
     */
    private static int COLUMNSADDEDPHARMACYPRODUCT = 6;

    /**
     * Additional Number of columns added when executing the orderProductManager method.
     */
    private static int COLUMNSADDEDORDERPRODUCT = 6;

    /**
     * Additional Number of columns added when executing the batteryManager method.
     */
    private static int COLUMNSADDEDBATTERY = 4;

    /**
     * Additional Number of columns added when executing the vehicleModelManager method.
     */
    private static int COLUMNSADDEDVEHICLEMODEL = 10;

    /**
     * Additional Number of columns added when executing the scooterManager method.
     */
    private static int COLUMNSADDEDSCOOTER = 23;

    /**
     * Additional Number of columns added when executing the droneManager method.
     */
    private static int COLUMNSADDEDDRONE = 23;

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
            e.printStackTrace();
        }

        this.jdbcUrl = System.getProperty("database.url");
        this.username = System.getProperty("database.username");
        this.password = System.getProperty("database.password");
    }

    /**
     * Constrói uma instância de "DataHandler" recebendo, por parâmetro, o URL
     * da BD e as credenciais do utilizador.
     *
     * @param jdbcUrl  o URL da BD.
     * @param username o nome do utilizador.
     * @param password a password do utilizador.
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
     * Allows running entire scripts
     *
     * @param fileName
     * @throws IOException
     * @throws SQLException
     */
    public void scriptRunner(String fileName) throws IOException, SQLException {

        openConnection();

        ScriptRunner runner = new ScriptRunner(getConnection(), false, false);

        runner.runScript(new BufferedReader(new FileReader(fileName)));

        closeAll();

    }

    /**
     * Estabelece a ligação à BD.
     */
    protected void openConnection() {
        try {
            connection = DriverManager.getConnection(
                    jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fecha os objetos "ResultSet", "CallableStatement" e "Connection", e
     * retorna uma mensagem de erro se alguma dessas operações não for bem
     * sucedida. Caso contrário retorna uma "string" vazia.
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


    protected Connection getConnection() {
        if (connection == null)
            openConnection();
        return connection;
    }

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

    protected CreditCard creditCardManager(ResultSet rSet, int firstColumn) throws SQLException { // column number +3
        long dblCreditCardNr = rSet.getLong(firstColumn);
        firstColumn++;
        java.sql.Date sqlStartDate = rSet.getDate(firstColumn);
        java.util.Date utilStartDate = new java.util.Date(sqlStartDate.getTime());
        firstColumn++;
        Integer strCCV = rSet.getInt(firstColumn);
        return new CreditCard(dblCreditCardNr, utilStartDate, strCCV);
    }

    protected Pharmacy pharmacyManager(ResultSet rSet, int firstColumn) throws SQLException, NoSuchAlgorithmException { // column number +11

        Integer pharmacyID = rSet.getInt(firstColumn);
        firstColumn++;
        String pharmacyName = rSet.getString(firstColumn);
        firstColumn++;
        String strEmail = rSet.getString(firstColumn);
        firstColumn++;
        Address oAddress = addressManager(rSet, firstColumn);

        return new Pharmacy(pharmacyID, pharmacyName, strEmail, oAddress);
    }

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

    protected Order orderManager(ResultSet rSet, int firstColumn) throws SQLException, NoSuchAlgorithmException { // column number +37

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
        firstColumn+= COLUMNSADDEDCLIENT;
        Pharmacy oPharmacy = pharmacyManager(rSet, firstColumn);


        return new Order(intId, dblAmount, dblTotalWeight, dblAdditionalFee, dtOrderDate, strDescription,
                strStatus, blIsHomeDelivery, oClient, oPharmacy, new TreeMap<>());
    }

    protected Invoice invoiceManager(ResultSet rSet, int firstColumn) throws SQLException, NoSuchAlgorithmException { // column number +47

        Order oOrder = orderManager(rSet, firstColumn);
        firstColumn+= COLUMNSADDEDORDER;
        int intInvoiceId = rSet.getInt(firstColumn);
        firstColumn++;
        Date dtInvoiceDate = rSet.getDate(firstColumn);
        firstColumn++;
        double dblTotalPrice = rSet.getDouble(firstColumn);


        return new Invoice(intInvoiceId, dtInvoiceDate, dblTotalPrice, oOrder);
    }

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

    protected Pharmacy pharmacyProductManager(ResultSet rSet, int firstColumn, Pharmacy oPharmacy) throws SQLException { // column number +6

        int intStock = rSet.getInt(firstColumn);
        firstColumn++;
        Product oProduct = productManager(rSet, firstColumn);

        oPharmacy.getStock().put(oProduct, intStock);

        return oPharmacy;
    }

    protected Order orderProductManager(ResultSet rSet, int firstColumn, Order oOrder) throws SQLException { // column number +6

        int intQuantity = rSet.getInt(firstColumn);
        firstColumn++;
        Product oProduct = productManager(rSet, firstColumn);

        oOrder.getProducts().put(oProduct, intQuantity);

        return oOrder;
    }

    protected Invoice invoiceProductManager(ResultSet rSet, int firstColumn, Invoice oInvoice) throws SQLException { // column number +6

        double intValue = rSet.getDouble(firstColumn);
        firstColumn++;
        CreditCard oCreditCard = creditCardManager(rSet, firstColumn);
        firstColumn+= COLUMNSADDEDORDER;

        oInvoice.getPayments().put(oCreditCard, intValue);

        return oInvoice;
    }

    protected Scooter scooterManager(ResultSet rSet, int firstColumn) throws SQLException, NoSuchAlgorithmException { // column number +23

        int intId = rSet.getInt(firstColumn);
        firstColumn++;
        double dblBatteryPerc = rSet.getDouble(firstColumn);
        firstColumn++;
        VehicleModel oVehicleModel = vehicleModelManager(rSet,firstColumn);
        firstColumn+= COLUMNSADDEDVEHICLEMODEL;
        Pharmacy oPharmacy = pharmacyManager(rSet,firstColumn);

        return new Scooter(intId, dblBatteryPerc, oVehicleModel, oPharmacy);
    }

    protected Drone droneManager(ResultSet rSet, int firstColumn) throws SQLException, NoSuchAlgorithmException { // column number +23

        int intId = rSet.getInt(firstColumn);
        firstColumn++;
        double dblBatteryPerc = rSet.getDouble(firstColumn);
        firstColumn++;
        VehicleModel oVehicleModel = vehicleModelManager(rSet,firstColumn);
        firstColumn+= COLUMNSADDEDVEHICLEMODEL;
        Pharmacy oPharmacy = pharmacyManager(rSet,firstColumn);

        return new Drone(intId, dblBatteryPerc, oVehicleModel, oPharmacy);
    }

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

        return new VehicleModel(strDesignation, dblPotency, dblWeight, dblMaxPayload, oBattery, oVehicleType);
    }

    protected Battery batteryManager(ResultSet rSet, int firstColumn) throws SQLException { // column number +4

        int intBatteryId = rSet.getInt(firstColumn);
        firstColumn++;
        double dblBatteryPerc = rSet.getDouble(firstColumn);
        firstColumn++;
        int intBatteryCapacity = rSet.getInt(firstColumn);
        firstColumn++;
        double dblBatteryVoltage = rSet.getDouble(firstColumn);

        return new Battery(intBatteryId, intBatteryCapacity, dblBatteryPerc, dblBatteryVoltage);
    }

    protected PharmacyTransfer pharmacyTransferManager(ResultSet rSet, int firstColumn) throws SQLException, NoSuchAlgorithmException { // column number +4

        int intId = rSet.getInt(firstColumn);
        firstColumn++;
        Date dtDate = rSet.getDate(firstColumn);
        firstColumn++;
        int intQuantity = rSet.getInt(firstColumn);
        firstColumn++;
        Product oProduct = productManager(rSet, firstColumn);
        firstColumn += COLUMNSADDEDPRODUCT;
        Pharmacy oNearbyPharmacy = pharmacyManager(rSet, firstColumn);
        firstColumn += COLUMNSADDEDPRODUCT;
        Order oOrder = orderManager(rSet, firstColumn);

        return new PharmacyTransfer(intId, dtDate, oOrder, oProduct, intQuantity, oNearbyPharmacy);
    }

    public void genericRemove(int intId, String strProcedureCall) {
        try {
            openConnection();
            String procedureCall = "{ call " + strProcedureCall + "(?) }";

            CallableStatement callStmt = getConnection().prepareCall(procedureCall);

            callStmt.setInt(1, intId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
