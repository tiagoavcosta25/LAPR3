package lapr.project.data;

import javafx.util.Pair;
import lapr.project.data.DataHandler;
import lapr.project.graph.map.Graph;
import lapr.project.graph.map.GraphAlgorithms;
import lapr.project.model.Address;
import lapr.project.model.Path;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DeliveryDB extends DataHandler {

    private Graph<Address, String> m_graph;

    public DeliveryDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
        m_graph = new Graph<>(true);
    }



    public Graph<Address, String> getM_graph() {
        return m_graph;
    }


    public boolean addPathToDB(Path p) {
        return addPathToDB(p.getIdAddressA(), p.getIdAddressB(), p.getName());
    }

    private boolean addPathToDB(int intIdAddressA, int intIdAddressB, String strName) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addPath(?,?) }");

            callStmt.setInt(1, intIdAddressA);
            callStmt.setInt(2, intIdAddressB);
            callStmt.setString(3, strName);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    public List<Path> getAllPaths() {
        CallableStatement callStmt = null;
        List<Path> lstPaths = new ArrayList<>();
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPaths() }");

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                int intIdAddress1 = rSet.getInt(1);
                int intIdAddress2 = rSet.getInt(2);
                String strName = rSet.getString(3);
                lstPaths.add(new Path(intIdAddress1, intIdAddress2, strName));
            }
            return lstPaths;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Paths Avaliable.");
    }


    public List<Address> getAllAddresses() {
        CallableStatement callStmt = null;
        List<Address> lstAddress = new ArrayList<>();
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllAddress() }");

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                lstAddress.add(addressManager(rSet, 1));
            }
            return lstAddress;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Addresses Avaliable.");
    }

    public List<Address> getAddressesByDeliveryRunId(String email) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getDeliveryRunIdByCourierEmail(?) }");

            callStmt.registerOutParameter(1, OracleTypes.NUMBER);
            callStmt.setString(2, email);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            int delRunId = 0;
            if (rSet.next()) {
                delRunId = rSet.getInt(1);
            }
            callStmt = getConnection().prepareCall("{ ? = call getAddressesByDeliveryRunId(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, delRunId);

            callStmt.execute();

            rSet = (ResultSet) callStmt.getObject(1);
            List<Address> list = new ArrayList<>();
            while (rSet.next()) {
                Address a = addressManager(rSet, 1);
                list.add(a);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Addresses in the delivery run associated with the courier with the following email:" + email);

    }


    /**
     * CREATE GRAPH
     */
    public void createGraph() {
        createGraph(getAllAddresses(), getAllPaths());
    }

    private void createGraph(List<Address> addresses, List<Path> paths) {
        double dist = 0;
        Pair<Address, Address> pathAdd;
        for (Address a : addresses) {
            m_graph.insertVertex(a);
        }
        for (Path p : paths) {

            pathAdd = getAddressesById(p.getIdAddressA(), p.getIdAddressB());

            if (pathAdd != null) {
                dist = pathAdd.getKey().distanceTo(pathAdd.getValue());
                m_graph.insertEdge(pathAdd.getKey(), pathAdd.getValue(), "Distance", dist);
            }
        }

    }

    private Pair<Address, Address> getAddressesById(int intAddressAId, int intAddressBId) {
        Address origem = null;
        Address destino = null;
        for (Address a : m_graph.vertices()) {
            if (a.getId() == intAddressAId) {
                origem = a;
            } else if (a.getId() == intAddressBId) {
                destino = a;
            }
            if (origem != null && destino != null)
                return new Pair<>(origem, destino);
        }
        return null;
    }

    /**
     * CALCULATE MOST EFFICIENT PATH/SHORTEST PATH
     */
    public Pair<LinkedList<Address>, Double> calculateMostEfficientPath(Address startAddress, Address endAddress, List<Address> deliveryPoints) {
        List<LinkedList<Address>> permutations = calculatePermutations(deliveryPoints);
        List<Pair<LinkedList<Address>, Double>> lst = calculatePermutationPaths(startAddress, endAddress, permutations);

        Double minimumWeight = Double.MAX_VALUE;
        Pair<LinkedList<Address>, Double> result = new Pair<LinkedList<Address>, Double>(null, 0d);

        for (Pair<LinkedList<Address>, Double> pair : lst) {
            if (pair.getValue() < minimumWeight) {
                minimumWeight = pair.getValue();
                result = pair;
            }
        }
        return result;
    }

    public List<Pair<LinkedList<Address>, Double>> calculatePermutationPaths(Address a1, Address a2,
                                                                             List<LinkedList<Address>> permutations) {    //O(k^2*V^2)
        List<Pair<LinkedList<Address>, Double>> listOfPaths = new LinkedList<>();
        Address start = a1, finish = a2;
        for (LinkedList<Address> list : permutations) {                                                                   //O(k^2*V^2)
            list.addFirst(start);
            list.addLast(finish);
            LinkedList<Address> permutationPath = new LinkedList<>();
            Double totalCost = 0.0;
            for (int i = 0; i < list.size() - 1; i++) {                                                                  //O(k*V^2)
                LinkedList<Address> tempPath = new LinkedList<>();
                totalCost += GraphAlgorithms.shortestPath(m_graph, list.get(i), list.get(i + 1), tempPath);   //O(V^2)
                if (tempPath.size() == 0)
                    return new LinkedList<>();
                tempPath.removeLast();
                permutationPath.addAll(tempPath);
            }
            permutationPath.add(finish);
            listOfPaths.add(new Pair<>(permutationPath, totalCost));
        }
        return listOfPaths;
    }

    public List<LinkedList<Address>> calculatePermutations(List<Address> original) {                                               //O(n!)
        if (original.isEmpty()) {
            List<LinkedList<Address>> result = new ArrayList<>();
            result.add(new LinkedList<>());
            return result;
        }
        Address firstElement = original.remove(0);
        List<LinkedList<Address>> returnValue = new ArrayList<>();
        List<LinkedList<Address>> permutations = calculatePermutations(original);
        for (List<Address> smallerPermutated : permutations) {
            for (int index = 0; index <= smallerPermutated.size(); index++) {
                LinkedList<Address> temp = new LinkedList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }

    //TO IMPLEMENT
    public float getMaxPayload(String email) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getMaxPayload(?) }");

            callStmt.registerOutParameter(1, OracleTypes.FLOAT);
            callStmt.setString(2, email);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            float maxPayload = 0;
            if (rSet.next()) {
                maxPayload= rSet.getFloat(1);
            }

            return maxPayload;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No payload found for the DR with the courier with the following email:" + email);

    }
}
