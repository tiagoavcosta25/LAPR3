package lapr.project.data;


import lapr.project.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
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
    private static int COLUMNS_ADDED_ADDRESS = 8;

    /**
     * Additional Number of columns added when executing the creditCardManager method.
     */
    private static int COLUMNS_ADDED_CC = 3;

    /**
     * Additional Number of columns added when executing the pharmacyManager method.
     */
    private static int COLUMNS_ADDED_PHARMACY = 15;

    /**
     * Additional Number of columns added when executing the pharmacyManagerManager method.
     */
    private static int COLUMNS_ADDED_PHARMACY_MANAGER = 5;

    /**
     * Additional Number of columns added when executing the clientManager method.
     */
    private static int COLUMNS_ADDED_CLIENT = 17;

    /**
     * Additional Number of columns added when executing the orderManager method.
     */
    private static int COLUMNS_ADDED_ORDER = 44;

    /**
     * Additional Number of columns added when executing the invoiceManager method.
     */
    private static int COLUMNS_ADDED_INVOICE = 47;

    /**
     * Additional Number of columns added when executing the productManager method.
     */
    private static int COLUMNS_ADDED_PRODUCT = 5;

    /**
     * Additional Number of columns added when executing the pharmacyProductManager method.
     */
    private static int COLUMNS_ADDED_PHARMACY_PRODUCT = 6;

    /**
     * Additional Number of columns added when executing the orderProductManager method.
     */
    private static int COLUMNS_ADDED_ORDER_PRODUCT = 6;

    /**
     * Use connection properties set on file application.properties
     */
    public DataHandler() {
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
        Integer id = rSet.getInt(firstColumn);
        firstColumn++;
        Double latitude = rSet.getDouble(firstColumn);
        firstColumn++;
        Double longitude = rSet.getDouble(firstColumn);
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
        return new Address(id, latitude, longitude, streetName, doorNumber, postalCode, locality, country);
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

    protected Pharmacy pharmacyManager(ResultSet rSet, int firstColumn) throws SQLException, NoSuchAlgorithmException { // column number +15

        Integer pharmacyID = rSet.getInt(firstColumn);
        firstColumn++;
        String pharmacyName = rSet.getString(firstColumn);
        firstColumn++;
        PharmacyManager oPharmacyManager = pharmacyManagerManager(rSet, firstColumn);
        firstColumn+= COLUMNS_ADDED_PHARMACY_MANAGER;
        Address oAddress = addressManager(rSet, firstColumn);

        return new Pharmacy(pharmacyID, pharmacyName, oPharmacyManager, oAddress);
    }

    protected PharmacyManager pharmacyManagerManager(ResultSet rSet, int firstColumn) throws SQLException, NoSuchAlgorithmException { // column number +5

        Integer id = rSet.getInt(firstColumn);
        firstColumn++;
        String emailManager = rSet.getString(firstColumn);
        firstColumn++;
        String password = rSet.getString(firstColumn);
        firstColumn++;
        Integer nif = rSet.getInt(firstColumn);
        firstColumn++;
        String name = rSet.getString(firstColumn);
        firstColumn++;
        return new PharmacyManager(id, emailManager, password, nif, name);
    }

    protected Client clientManager(ResultSet rSet, int firstColumn) throws SQLException { // column number +17

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
        firstColumn+= COLUMNS_ADDED_ADDRESS;
        CreditCard oCreditCard = creditCardManager(rSet, firstColumn);

        return new Client(intId, strName, strNif, strEmail, strPassword, intCredits, oClientAddress, oCreditCard);
    }

    protected Order orderManager(ResultSet rSet, int firstColumn) throws SQLException, NoSuchAlgorithmException { // column number +44

        int intId = rSet.getInt(firstColumn);
        firstColumn++;
        String strDescription = rSet.getString(firstColumn);
        firstColumn++;
        String strStatus = rSet.getString(firstColumn);
        firstColumn++;
        Date dtOrderDate = rSet.getDate(firstColumn);
        firstColumn++;
        float fltTotalWeight = rSet.getFloat(firstColumn);
        firstColumn++;
        float fltAmount = rSet.getFloat(firstColumn);
        firstColumn++;
        float fltAdditionalFee = rSet.getFloat(firstColumn);
        firstColumn++;
        Address oAddress = addressManager(rSet, firstColumn);
        firstColumn+= COLUMNS_ADDED_ADDRESS;
        Client oClient = clientManager(rSet, firstColumn);
        firstColumn+= COLUMNS_ADDED_CLIENT;
        Pharmacy oPharmacy = pharmacyManager(rSet, firstColumn);


        return new Order(intId, fltAmount, fltTotalWeight, fltAdditionalFee, dtOrderDate, strDescription,
                strStatus, oClient, oAddress, oPharmacy, new TreeMap<>());
    }

    protected Invoice invoiceManager(ResultSet rSet, int firstColumn) throws SQLException, NoSuchAlgorithmException { // column number +47

        Order oOrder = orderManager(rSet, firstColumn);
        firstColumn+= COLUMNS_ADDED_ORDER;
        int intInvoiceId = rSet.getInt(firstColumn);
        firstColumn++;
        Date dtInvoiceDate = rSet.getDate(firstColumn);
        firstColumn++;
        Float fltTotalPrice = rSet.getFloat(firstColumn);


        return new Invoice(intInvoiceId, dtInvoiceDate, fltTotalPrice, oOrder);
    }

    protected Product productManager(ResultSet rSet, int firstColumn) throws SQLException { // column number +5

        int intID = rSet.getInt(firstColumn);
        firstColumn++;
        String strName = rSet.getString(firstColumn);
        firstColumn++;
        String strDescription = rSet.getString(firstColumn);
        firstColumn++;
        float fltUnitaryPrice = rSet.getFloat(firstColumn);
        firstColumn++;
        float fltUnitaryWeight = rSet.getFloat(firstColumn);


        return new Product(intID, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
    }

    protected Pharmacy pharmacyProductManager(ResultSet rSet, int firstColumn, Pharmacy oPharmacy) throws SQLException { // column number +6

        int intStock = rSet.getInt(firstColumn);
        firstColumn++;
        Product oProduct = productManager(rSet, firstColumn);
        firstColumn+= COLUMNS_ADDED_ORDER;

        oPharmacy.getStock().put(oProduct, intStock);

        return oPharmacy;
    }

    protected Order orderProductManager(ResultSet rSet, int firstColumn, Order oOrder) throws SQLException { // column number +6

        int intQuantity = rSet.getInt(firstColumn);
        firstColumn++;
        Product oProduct = productManager(rSet, firstColumn);
        firstColumn+= COLUMNS_ADDED_ORDER;

        oOrder.getProducts().put(oProduct, intQuantity);

        return oOrder;
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
