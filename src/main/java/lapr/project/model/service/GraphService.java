package lapr.project.model.service;

import javafx.util.Pair;
import lapr.project.data.DeliveryRunDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.VehicleDB;
import lapr.project.graph.map.Edge;
import lapr.project.graph.map.Graph;
import lapr.project.graph.map.GraphAlgorithms;
import lapr.project.model.*;
import lapr.project.utils.Constants;
import lapr.project.utils.EnergyCalculator;
import lapr.project.utils.WriteFile;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GraphService {

    private static final String DELIVERYRUNPATH = "DeliveryRunPath";
    private static final Logger LOGGER = Logger.getLogger(GraphService.class.getName());
    private Graph<Address, Path> moGraphScooter;
    private Graph<Address, Path> moGraphDrone;
    private DeliveryRunDB moDeliveryRunDB;
    private VehicleDB moVehicleDB;
    private PharmacyDB moPharmacyDB;

    public GraphService() {
        moGraphScooter = new Graph<>(true);
        moGraphDrone = new Graph<>(true);
        moDeliveryRunDB = new DeliveryRunDB();
        moVehicleDB = new VehicleDB();
        moPharmacyDB = new PharmacyDB();
    }

    public void setScooterGraph(Graph<Address, Path> moGraph) {
        this.moGraphScooter = moGraph;
    }

    public Graph<Address, Path> getScooterGraph() {
        return moGraphScooter;
    }

    public void setDroneGraph(Graph<Address, Path> moGraphDrone) {
        this.moGraphDrone = moGraphDrone;
    }

    public Graph<Address, Path> getDroneGraph() {
        return moGraphDrone;
    }

    public void setMoDeliveryRunDB(DeliveryRunDB moDeliveryRunDB) {
        this.moDeliveryRunDB = moDeliveryRunDB;
    }

    public void setMoVehicleDB(VehicleDB moVehicleDB) {
        this.moVehicleDB = moVehicleDB;
    }

    public void setMoPharmacyDB(PharmacyDB moPharmacyDB) {
        this.moPharmacyDB = moPharmacyDB;
    }

    public DeliveryRunDB getMoDeliveryRunDB() {
        return moDeliveryRunDB;
    }

    public VehicleDB getMoVehicleDB() {
        return moVehicleDB;
    }

    public PharmacyDB getMoPharmacyDB() {
        return moPharmacyDB;
    }

    /**
     * CREATE GRAPH
     */
    public void createGraph() {
        createGraph(moDeliveryRunDB.getAllAddresses(), moDeliveryRunDB.getAllPaths());
    }

    private void createGraph(List<Address> addresses, List<Path> paths) {
        double dist;
        Pair<Address, Address> pathAdd;
        for (Address a : addresses) {
            moGraphScooter.insertVertex(a);
            moGraphDrone.insertVertex(a);
        }
        for (Path p : paths) {
            if (p.getVehicleType().equals(VehicleType.SCOOTER)) {
                pathAdd = getAddresses(p.getLatitudeA(), p.getLongitudeA(), p.getLatitudeB(), p.getLongitudeB(), VehicleType.SCOOTER);
                if (pathAdd != null) {
                    dist = pathAdd.getKey().distanceTo(pathAdd.getValue());
                    moGraphScooter.insertEdge(pathAdd.getKey(), pathAdd.getValue(), p, dist);
                }
            }
            else if (p.getVehicleType().equals(VehicleType.DRONE)) {
                pathAdd = getAddresses(p.getLatitudeA(), p.getLongitudeA(), p.getLatitudeB(), p.getLongitudeB(), VehicleType.DRONE);
                if (pathAdd != null) {
                    dist = pathAdd.getKey().distanceTo(pathAdd.getValue());
                    moGraphDrone.insertEdge(pathAdd.getKey(), pathAdd.getValue(), p, dist);
                }
            }
        }
    }

    private Pair<Address, Address> getAddresses(double dblLatitudeA, double dblLongitudeA,
                                                double dblLatitudeB, double dblLongitudeB, VehicleType vehicleType) {
        Address origem = null;
        Address destino = null;
        Iterable<Address> iterable;
        if(vehicleType.equals(VehicleType.SCOOTER))
            iterable = moGraphScooter.vertices();
        else
            iterable = moGraphDrone.vertices();
        for (Address a : iterable) {
            if (a.getLatitude() == dblLatitudeA && a.getLongitude() == dblLongitudeA) {
                origem = a;
            } else if (a.getLatitude() == dblLatitudeB && a.getLongitude() == dblLongitudeB) {
                destino = a;
            }
            if (origem != null && destino != null)
                return new Pair<>(origem, destino);
        }
        return null;
    }

    public List<Path> getListOfPaths(Graph<Address, Path> g) {
        LinkedList<Path> paths = new LinkedList<>();
        for(Edge<Address, Path> edge : g.edges())
            paths.add(edge.getElement());
        return paths;
    }

    public Path getPathFromAddresses(List<Path> paths, Address addA, Address addB) {
        for(Path path : paths) {
            if(path.getLatitudeA() == addA.getLatitude() && path.getLongitudeA() == addA.getLongitude() &&
                    path.getLatitudeB() == addB.getLatitude() && path.getLongitudeB() == addB.getLongitude())
                return path;
        }
        return null;
    }

    public List<Path> getAllPathsFromAddresses(VehicleType vType, List<Address> addresses) {
        List<Path> pathsReturn = new ArrayList<>();
        List<Path> paths;
        if(vType.equals(VehicleType.SCOOTER))
            paths = getListOfPaths(this.moGraphScooter);
        else if(vType.equals(VehicleType.DRONE))
            paths = getListOfPaths(this.moGraphDrone);
        else
            return Collections.emptyList();
        for(int i = 0; i < addresses.size() - 1; i++) {
            pathsReturn.add(getPathFromAddresses(paths, addresses.get(i), addresses.get(i + 1)));
        }
        return pathsReturn;
    }

    public boolean checkIfAddressIsPharmacy(Address add) {
        List<Pharmacy> pharmacyList = moPharmacyDB.getPharmacies();
        for(Pharmacy ph : pharmacyList) {
            if(ph.getAddress().equals(add)) return true;
        }
        return false;
    }


    public boolean checkIfPathGoesByIntermediates(List<Address> path, List<Order> orders) {
        List<Address> intermediates = new LinkedList<>();
        for(Order o : orders) {
            intermediates.add(o.getClient().getAddress());
        }
        return path.containsAll(intermediates);
    }

    public boolean checkIfInListThreeTimes(Address address, List<Address> addresses) {
        int counter = 0;
        for(Address a : addresses) {
            if(address.equals(a))
                counter++;
            if(counter == 3)
                return true;
        }
        return false;
    }


    public Route pathsWithPharmacies(Graph<Address, Path> g, Address s, Address d, List<VehicleModel> vmList,
                                     List<Order> orderList,
             boolean energy) {
        if(g.numVertices() == 0)
            return new Route();
        List<Route> result = new LinkedList<>();
        LinkedList<Address> pathList = new LinkedList<>();
        pathList.add(s);
        pathsWithPharmaciesCalculator(g, s, d, pathList, result, vmList, orderList, energy);
        double minCost = Double.MAX_VALUE;
        Route finalResult = new Route();
        if(result.isEmpty())
            return new Route();
        for(Route route : result) {
            if(energy) {
                if(route.getTotalEnergy() < minCost) {
                    finalResult = route;
                    minCost = route.getTotalEnergy();
                }
            } else {
                if(route.getTotalTime() < minCost) {
                    finalResult = route;
                    minCost = route.getTotalEnergy();
                }
            }
        }
        return finalResult;
    }

    private void pathsWithPharmaciesCalculator
            (Graph<Address, Path> g, Address u, Address d, LinkedList<Address> localPathList,
             List<Route> result, List<VehicleModel> vmList,
             List<Order> orderList, boolean energy) {

        if (localPathList.size() > 1 && u.equals(d)) {
            LinkedList<Address> finalPathList = new LinkedList<>(localPathList);
            if(checkIfPathGoesByIntermediates(finalPathList, orderList)) {
                Route vehicleAndCost = getBestPossibleModel(vmList, localPathList,
                        orderList, energy);
                result.add(vehicleAndCost);
            }
            return;
        }
        for (Address adj : g.adjVertices(u)) {
            localPathList.add(adj);
            if (localPathList.size() < g.numVertices() * 2 && !checkIfInListThreeTimes(adj, localPathList)) {
                pathsWithPharmaciesCalculator(g, adj, d, localPathList, result, vmList, orderList, energy);
            }
            localPathList.removeLast();
        }
    }

    /**
     * PRINT RESULTS
     */
    public void printResults(Route resultScooter,
                             Route resultDrone) {
        String body;
        if(resultScooter.getTotalTime() == Double.MAX_VALUE && resultDrone.getTotalTime() == Double.MAX_VALUE) {
            body = "\n\n\nThere is no scooter model nor drone model that can make this path!";
            LOGGER.log(Level.INFO, body);
            WriteFile.write(DELIVERYRUNPATH,body);
        }
        else if(resultDrone.getTotalTime() == Double.MAX_VALUE) {
            body = String.format("\n\n\nNo drone can make this path!\n\n\nBest scooter route characteristics: %s",
                    resultScooter);

            LOGGER.log(Level.INFO, body);
            WriteFile.write(DELIVERYRUNPATH,body);
        }
        else if(resultScooter.getTotalTime() == Double.MAX_VALUE) {
            body = String.format("\n\n\nNo scooter can make this path!\n\n\nBest drone route characteristics: %s",
                    resultDrone);
            LOGGER.log(Level.INFO, body);
            WriteFile.write(DELIVERYRUNPATH,body);
        }
        else {
            body = String.format("\n\n\nBest scooter route characteristics: %s" +
                            "\n\n\nBest drone route characteristics: %s",
                    resultScooter, resultDrone);
            LOGGER.log(Level.INFO, body);
            WriteFile.write(DELIVERYRUNPATH,body);
        }
    }

    /**
     * CALCULATE PATH COST WITH PHARMACIES
     */
    public List<Route> calculateBestVehicleAndBestPath(List<Order> orderList, boolean energy, boolean lookForPharmacies) {
        double maxWeight = 0;
        for(Order o : orderList) {
            maxWeight += o.getTotalWeight();
        }
        Pharmacy pharmacy = orderList.get(0).getPharmacy();
        List<VehicleModel> scooterList = new ArrayList<>();
        List<VehicleModel> droneList = new ArrayList<>();
        for(VehicleModel vM : moVehicleDB.getPharmacyModel(pharmacy.getEmail())) {
            if(vM.getMaxPayload() >= maxWeight) {
                if (vM.getVehicleType().equals(VehicleType.SCOOTER)) scooterList.add(vM);
                else droneList.add(vM);
            }
        }
        if(scooterList.isEmpty() && droneList.isEmpty()) {
            String body = "\n\n\nThere is no scooter model nor drone model that can make this path!";
            LOGGER.log(Level.INFO, body);
            WriteFile.write(DELIVERYRUNPATH,body);
            return Collections.emptyList();
        }

        //CALCULATE WITHOUT PHARMACIES IN CONSIDERATION
        return calculateBestVehicleForMostEficientPath(orderList, pharmacy, scooterList, droneList,
                energy, lookForPharmacies);
    }


    public List<Route> calculateBestVehicleForMostEficientPath
            (List<Order> orderList, Pharmacy pharmacy, List<VehicleModel> scooterList, List<VehicleModel> droneList,
             boolean energy, boolean lookForPharmacies) {

        List<Route> lstReturn = new ArrayList<>();
        List<Address> deliveryPoints = new ArrayList<>();
        for(Order o : orderList) {
            deliveryPoints.add(o.getClient().getAddress());
        }

        List<Address> scooterPath = calculateMostEfficientPath(VehicleType.SCOOTER, pharmacy.getAddress(),
                pharmacy.getAddress(), deliveryPoints, energy);
        List<Address> dronePath = calculateMostEfficientPath(VehicleType.DRONE, pharmacy.getAddress(),
                pharmacy.getAddress(), deliveryPoints, energy);

        Route routeScooter;
        Route routeDrone;

        routeScooter = getBestPossibleModel(scooterList, scooterPath, orderList, energy);

        routeDrone = getBestPossibleModel(droneList, dronePath, orderList, energy);

        if(routeScooter.getTotalEnergy() != Double.MAX_VALUE && routeDrone.getTotalEnergy() != Double.MAX_VALUE) {
            printResults(routeScooter, routeDrone);
            lstReturn.add(routeScooter);
            lstReturn.add(routeDrone);
            return lstReturn;
        }
        //Retornar os 2 logo

        if(!lookForPharmacies){
            printResults(routeScooter, routeDrone);
            lstReturn.add(routeScooter);
            lstReturn.add(routeDrone);
            return lstReturn;
        }


        if(routeScooter.getTotalEnergy() == Double.MAX_VALUE) {
            routeScooter = pathsWithPharmacies(this.moGraphScooter,
                    pharmacy.getAddress(), pharmacy.getAddress(), scooterList, orderList, energy);
        }
        if(routeDrone.getTotalEnergy() == Double.MAX_VALUE) {
            routeDrone = pathsWithPharmacies(this.moGraphDrone,
                    pharmacy.getAddress(), pharmacy.getAddress(), droneList, orderList, energy);
        }
        lstReturn.add(routeScooter);
        lstReturn.add(routeDrone);
        //Calcular os outros

        printResults(routeScooter, routeDrone);
        return lstReturn;
    }

    public Route getBestPossibleModel(List<VehicleModel> vList, List<Address> path,
                                                           List<Order> orderList, boolean energy) {
        double lowestCost = Double.MAX_VALUE;
        double energiaModelo100;
        double energiaModeloPath;
        Route bestCostRoute = new Route();
        for(VehicleModel vehicle : vList) {
            // value/1000 for kwh
            energiaModelo100 = moVehicleDB.getEnergyByVehicleModel(vehicle.getId()) / 1000;
            Route pair = calculatePathCost(path, orderList, vehicle, energiaModelo100);
            if(energy)
                energiaModeloPath = pair.getTotalEnergy();
            else
                energiaModeloPath = pair.getTotalTime();
            if(energiaModeloPath < lowestCost) {
                bestCostRoute = pair;
                lowestCost = energiaModeloPath;
            }
        }
        return bestCostRoute;
    }

    public Route calculatePathCost(List<Address> allAddresses, List<Order> orderList,
                                   VehicleModel vModel, Double maxEnergy) {
        double energyRemaining = maxEnergy;
        double energyCost = 0;
        double tempEnergy;
        Route route = new Route(vModel.getVehicleType(), allAddresses);
        double totalMass = vModel.getWeight();
        if (vModel.getVehicleType().equals(VehicleType.SCOOTER))
            totalMass += Constants.DEFAULT_COURIER_WEIGHT;
        else if (vModel.getVehicleType().equals(VehicleType.NOTDEFINED))
            return new Route();
        Map<Address, Double> orderWeightMap = new HashMap<>();
        for (Order order : orderList) {
            orderWeightMap.put(order.getClient().getAddress(), order.getTotalWeight());
            totalMass += order.getTotalWeight();
        }
        List<Path> paths;
        for (int i = 0; i < allAddresses.size() - 1; i++) {
            Path path;
            if (vModel.getVehicleType().equals(VehicleType.SCOOTER)) {
                paths = getListOfPaths(this.moGraphScooter);
            }
            else {
                paths = getListOfPaths(this.moGraphDrone);
            }
            path = getPathFromAddresses(paths, allAddresses.get(i), allAddresses.get(i + 1));
            double distanceUsingCoordinates = allAddresses.get(i).distanceTo(allAddresses.get(i + 1));
            double localHeightDifference = allAddresses.get(i + 1).getAltitude() - allAddresses.get(i).getAltitude();
            double winDegree = path.getWindAngle();
            double winSpeed = path.getWindSpeed();
            double kineticFrictionCoefficient = path.getKineticFrictionCoefficient();
            Pair<Double, Double> tempPair;
            if (orderWeightMap.containsKey(allAddresses.get(i))) {
                totalMass -= orderWeightMap.get(allAddresses.get(i));
            }
            if (vModel.getVehicleType().equals(VehicleType.SCOOTER)) {
                tempPair =  EnergyCalculator.calculateScooterEnergy(distanceUsingCoordinates, winDegree, winSpeed,
                        localHeightDifference,
                        totalMass, kineticFrictionCoefficient,vModel.getPotency());
            }
            else {
                tempPair = EnergyCalculator.calculateDroneEnergy(totalMass, winSpeed, winDegree,
                        distanceUsingCoordinates,vModel.getPotency());
            }
            tempEnergy = tempPair.getKey();
            route.getEnergyList().add(tempEnergy);
            route.getTimeList().add(tempPair.getValue() / 60);
            energyCost += tempEnergy;
            energyRemaining -= tempEnergy;
            if (energyRemaining < 0) {
                return new Route();
            }
            if (checkIfAddressIsPharmacy(allAddresses.get(i + 1))) {
                if(i + 1 != allAddresses.size() - 1 &&
                        moDeliveryRunDB.checkValidChargingSlot(allAddresses.get(i + 1))) {
                    energyRemaining = maxEnergy;
                    route.getChargeStops().add(allAddresses.get(i + 1));
                }
            }
        }
        if(energyCost < maxEnergy) {
            route.setChargeStops(new ArrayList<>());
        }

        List<Path> pathsForRoute = getAllPathsFromAddresses(vModel.getVehicleType(), allAddresses);
        route.setPathList(pathsForRoute);
        route.setAddressList(allAddresses);
        route.setVehicleModel(vModel);

        return route;
    }


    public List<Address> calculateMostEfficientPath(VehicleType vType, Address startAddress,
                                                    Address endAddress, List<Address> deliveryPoints, boolean energy) {
        Graph<Address, Path> cloneGraph;

        if(vType.equals(VehicleType.SCOOTER)) cloneGraph = this.moGraphScooter.clone();
        else if(vType.equals(VehicleType.DRONE)) cloneGraph = this.moGraphDrone.clone();
        else return Collections.emptyList();

        prepareGraph(cloneGraph, vType, energy);

        List<Address> usablePoints = new LinkedList<>(deliveryPoints);
        List<LinkedList<Address>> permutations = calculatePermutations(usablePoints);
        List<Pair<LinkedList<Address>, Double>> lst = calculatePermutationPaths(cloneGraph, startAddress,
                endAddress, permutations);

        Double minimumWeight = Double.MAX_VALUE;
        LinkedList<Address> result = new LinkedList<>();

        for (Pair<LinkedList<Address>, Double> pair : lst) {
            if (pair.getValue() < minimumWeight) {
                minimumWeight = pair.getValue();
                result = pair.getKey();
            }
        }
        return result;
    }

    public void prepareGraph(Graph<Address, Path> g, VehicleType vType, boolean energy) {
        for(Edge<Address, Path> e : g.edges()) {
            double distanceUsingCoordinates = e.getVOrig().distanceTo(e.getVDest());
            double localHeightDifference = e.getVOrig().getAltitude() - e.getVDest().getAltitude();
            double totalMass = 1;
            double totalPower=10000;
            double weight;
            if(vType.equals(VehicleType.SCOOTER)) {
                if(energy)
                    weight = EnergyCalculator.calculateScooterEnergy(distanceUsingCoordinates,
                            e.getElement().getWindAngle(), e.getElement().getWindSpeed(), localHeightDifference,
                            totalMass, e.getElement().getKineticFrictionCoefficient(),totalPower).getKey();
                else
                    weight = EnergyCalculator.calculateScooterEnergy(distanceUsingCoordinates,
                            e.getElement().getWindAngle(), e.getElement().getWindSpeed(), localHeightDifference,
                            totalMass, e.getElement().getKineticFrictionCoefficient(),totalPower).getValue();
            }
            else {
                if(energy)
                    weight = EnergyCalculator.calculateDroneEnergy(totalMass, e.getElement().getWindSpeed(),
                            e.getElement().getWindAngle(), distanceUsingCoordinates,totalPower).getKey();
                else
                    weight = EnergyCalculator.calculateDroneEnergy(totalMass, e.getElement().getWindSpeed(),
                            e.getElement().getWindAngle(), distanceUsingCoordinates,totalPower).getValue();
            }
            e.setWeight(weight);
        }
    }

    public List<Pair<LinkedList<Address>, Double>> calculatePermutationPaths
            (Graph<Address, Path> g, Address a1, Address a2, List<LinkedList<Address>> permutations) {
        int[][] path;
        double[][] matriz = prepareAdjacencyMatrix(g);
        path = GraphAlgorithms.transitiveClosureFloyd(matriz, g.numVertices());


        List<Pair<LinkedList<Address>, Double>> listOfPaths = new LinkedList<>();
        for (LinkedList<Address> list : permutations) {
            list.addFirst(a1);
            list.addLast(a2);
            LinkedList<Address> permutationPath = new LinkedList<>();
            double totalCost = 0.0;
            for (int i = 0; i < list.size() - 1; i++) {
                LinkedList<Address> tempPath = new LinkedList<>();
                //CALCULATE ENERGY
                totalCost += GraphAlgorithms.getShortestPathFloyd(g, path, matriz, list.get(i), list.get(i + 1), tempPath);   //O(V^2)
                if (tempPath.isEmpty())
                    return Collections.emptyList();
                tempPath.removeLast();
                permutationPath.addAll(tempPath);
            }
            permutationPath.add(a2);
            listOfPaths.add(new Pair<>(permutationPath, totalCost));
        }
        return listOfPaths;
    }

    public double[][] prepareAdjacencyMatrix(Graph<Address, Path> g) {
        double[][] matriz = new double[g.numVertices()][g.numVertices()];
        Object[] teste1 = g.allkeyVerts();
        Address[] teste = new Address[g.numVertices()];

        int i = 0;
        for(Object o : teste1 ) {
            teste[i++] = (Address)o;
        }

        for(i = 0; i < teste.length; i++) {
            for(int j = 0; j < teste.length; j++) {
                if(g.getEdge(teste[i], teste[j]) != null && !teste[i].equals(teste[j])) {
                    matriz[i][j] = g.getEdge(teste[i], teste[j]).getWeight();
                } else
                    matriz[i][j] = Double.MAX_VALUE;
            }
        }
        return matriz;
    }

    public List<LinkedList<Address>> calculatePermutations(List<Address> original) {
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

}
