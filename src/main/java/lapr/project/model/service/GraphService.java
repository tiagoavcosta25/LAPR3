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
    private List<Address> chargingSpots;
    private boolean pathRecharge;
    private double pathTime;

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

    public Path getPathFromAddresses(Graph<Address, Path> g, Address addA, Address addB) {
        List<Path> paths = getListOfPaths(g);
        for(Path path : paths) {
            if(path.getLatitudeA() == addA.getLatitude() && path.getLongitudeA() == addA.getLongitude() &&
                    path.getLatitudeB() == addB.getLatitude() && path.getLongitudeB() == addB.getLongitude())
                return path;
        }
        return null;
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


    /**
     * CALCULATE PATH COST WITH PHARMACIES
     */

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

/*
    public Pair<Pair<VehicleModel, Double>, List<Address>> pathsWithPharmacies
    (Graph<Address, Path> g, Address s, Address d, List<VehicleModel> vmList, List<Order> orderList) {
        if(g.numVertices() == 0)
            return null;
        List<Pair<Pair<VehicleModel, Double>, List<Address>>> result = new LinkedList<>();
        LinkedList<Address> pathList = new LinkedList<>();
        pathList.add(s);
        pathsWithPharmaciesCalculator(g, s, d, pathList, result, vmList, orderList);
        Double minCost = Double.MAX_VALUE;
        Pair<Pair<VehicleModel, Double>, List<Address>> finalResult = null;
        for(Pair<Pair<VehicleModel, Double>, List<Address>> doublePair : result) {
            if(doublePair.getKey().getValue() < minCost) {
                finalResult = doublePair;
                minCost = doublePair.getKey().getValue();
            }
        }
        return finalResult;
    }
    */

/*
    private void pathsWithPharmaciesCalculator
            (Graph<Address, Path> g, Address u, Address d, LinkedList<Address> localPathList,
             List<Pair<Pair<VehicleModel, Double>, List<Address>>> result, List<VehicleModel> vmList,
             List<Order> orderList) {

        if (localPathList.size() > 1 && u.equals(d)) {
            LinkedList<Address> finalPathList = new LinkedList<>(localPathList);
            if(checkIfPathGoesByIntermediates(finalPathList, orderList)) {
                Pair<VehicleModel, Double> vehicleAndCost = getBestPossibleModel(vmList, localPathList, orderList);
                Pair<Pair<VehicleModel, Double>, List<Address>> vcPath = new Pair<>(vehicleAndCost, finalPathList);
                result.add(vcPath);
            }
            return;
        }
        for (Address adj : g.adjVertices(u)) {
            localPathList.add(adj);
            if (localPathList.size() < g.numVertices() * 2 && !checkIfInListThreeTimes(adj, localPathList)) {
                pathsWithPharmaciesCalculator(g, adj, d, localPathList, result, vmList, orderList);
            }
            localPathList.removeLast();
        }
    }
*/
    /**
     * PRINT PATH
     */
/*
    public void printPhysics(Graph<Address, Path> g, List<Address> paths) {
        StringBuilder result = new StringBuilder();
        result.append("\n\n");
        if(g.equals(this.getScooterGraph())) {
            result.append("Scooter paths:\n");
        } else
            result.append("Drone paths:\n");
        for(int i = 0; i < paths.size() - 1; i++) {
            result.append("\t").append(getPathFromAddresses(g, paths.get(i), paths.get(i + 1))).append("\n");
        }
        result.append("\n\n");
        LOGGER.log(Level.INFO, result.toString());
    }
*/
    /**
     * CALCULATE PATH COST
     */
/*
    public List<Pair<Pair<VehicleModel, Double>, List<Address>>> calculateBestVehicleAndBestPath(List<Order> orderList) {
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
        if(scooterList.isEmpty() && droneList.isEmpty()) return null;

        //CALCULATE WITHOUT PHARMACIES IN CONSIDERATION
        return calculateBestVehicleForMostEficientPath(orderList, pharmacy, scooterList, droneList);
    }

    public List<Pair<Pair<VehicleModel, Double>, List<Address>>> calculateBestVehicleForMostEficientPath
            (List<Order> orderList, Pharmacy pharmacy, List<VehicleModel> scooterList, List<VehicleModel> droneList) {

        List<Pair<Pair<VehicleModel, Double>, List<Address>>> lstReturn = new ArrayList<>();
        List<Address> deliveryPoints = new ArrayList<>();
        for(Order o : orderList) {
            deliveryPoints.add(o.getClient().getAddress());
        }

        List<Address> scooterPath = calculateMostEfficientPath(VehicleType.SCOOTER, pharmacy.getAddress(),
                pharmacy.getAddress(), deliveryPoints);
        List<Address> dronePath = calculateMostEfficientPath(VehicleType.DRONE, pharmacy.getAddress(),
                pharmacy.getAddress(), deliveryPoints);

        double pathTimeScooter;
        double pathTimeDrone;
        boolean scooterRecharged;
        boolean droneRecharged;
        List<Address> chargingSpotScooter;
        List<Address> chargingSpotDrone;

        Pair<VehicleModel, Double> bestScooter = getBestPossibleModel(scooterList, scooterPath, orderList);
        chargingSpotScooter = this.chargingSpots;
        scooterRecharged = this.pathRecharge;
        pathTimeScooter = this.pathTime;
        Pair<VehicleModel, Double> bestDrone = getBestPossibleModel(droneList, dronePath, orderList);
        chargingSpotDrone = this.chargingSpots;
        droneRecharged = this.pathRecharge;
        pathTimeDrone = this.pathTime;

        String body;
        if(bestScooter.getValue() == 0)
            bestScooter = new Pair<>(bestScooter.getKey(), Double.MAX_VALUE);
        if(bestDrone.getValue() == 0)
            bestDrone = new Pair<>(bestDrone.getKey(), Double.MAX_VALUE);
        if(bestScooter.getValue() != Double.MAX_VALUE && bestDrone.getValue() != Double.MAX_VALUE) {
            body = String.format("\n\n\nBest scooter model: %s\nBest scooter energetic cost: %.2f kWh" +
                    "\nScooter best path: %s\nScooter path time: %.2f minutes\nScooter needed to charge: %s\nScooter charged on: %s\n\n\nBest drone model: %s\nBest drone energetic cost: %.2f kWh" +
                    "\nDrone best path: %s\nDrone path time: %.2f minutes\nDrone needed to charge: %s\nDrone charged on: %s",bestScooter.getKey(),
                    bestScooter.getValue(),scooterPath,pathTimeScooter, scooterRecharged, chargingSpotScooter, bestDrone.getKey(),
                    bestDrone.getValue(),dronePath, pathTimeDrone, droneRecharged, chargingSpotDrone);
            printPhysics(moGraphScooter, scooterPath);
            printPhysics(moGraphDrone, dronePath);
            LOGGER.log(Level.INFO, body);
            WriteFile.write(DELIVERYRUNPATH,body);
            lstReturn.add(new Pair<>(bestScooter, scooterPath));
            lstReturn.add(new Pair<>(bestDrone, dronePath));
            return lstReturn;
        }
        //Retornar os 2 logo


        Pair<Pair<VehicleModel, Double>, List<Address>> resultScooter;
        Pair<Pair<VehicleModel, Double>, List<Address>> resultDrone;
        if(bestScooter.getValue() == Double.MAX_VALUE) {
            resultScooter = pathsWithPharmacies(this.moGraphScooter,
                    pharmacy.getAddress(), pharmacy.getAddress(), scooterList, orderList);
            chargingSpotScooter = this.chargingSpots;
            scooterRecharged = this.pathRecharge;
        } else {
            resultScooter = new Pair<>(bestScooter, scooterPath);
        }
        if(bestDrone.getValue() == Double.MAX_VALUE) {
            resultDrone = pathsWithPharmacies(this.moGraphDrone,
                    pharmacy.getAddress(), pharmacy.getAddress(), droneList, orderList);
            chargingSpotDrone = this.chargingSpots;
            droneRecharged = this.pathRecharge;
        } else
            resultDrone = new Pair<>(bestDrone, dronePath);
        lstReturn.add(resultScooter);
        lstReturn.add(resultDrone);
        //Calcular os outros

        if(resultDrone == null && resultScooter == null) {
            body = "\n\n\nThere is no scooter model nor drone model that can make this path!";
            LOGGER.log(Level.INFO, body);
            WriteFile.write(DELIVERYRUNPATH,body);
        }
        else if(resultScooter != null && resultDrone != null) {
            body = String.format("\n\n\nBest scooter model: %s\nBest scooter energetic cost: %.2f kWh\n" +
                    "Scooter best path: %s\nScooter path time: %.2f minutes\nScooter needed to recharge: %s\nScooter needed to recharge on: %s\n\n\n" +
                            "Best drone model: %s\nBest drone energetic cost: %.2f kWh\n" +
                    "Drone best path: %s\nDrone path time: %.2f minutes\nDrone needed to recharge: %s\nDrone needed to recharge on: %s",
                    resultScooter.getKey().getKey(),resultScooter.getKey().getValue(),
                    resultScooter.getValue(),pathTimeScooter, scooterRecharged, chargingSpotScooter,resultDrone.getKey().getKey(),
                    resultDrone.getKey().getValue(),
                    resultDrone.getValue(), pathTimeDrone, droneRecharged, chargingSpotDrone);
            printPhysics(moGraphScooter, resultScooter.getValue());
            printPhysics(moGraphDrone, resultDrone.getValue());
            LOGGER.log(Level.INFO, body);
            WriteFile.write(DELIVERYRUNPATH,body);
        }
        else if(resultScooter == null) {
            body = String.format("\n\n\nNo scooter can make this path!\n\n\nBest drone model: %s" +
                    "\nBest drone energetic cost: %.2f kWh\nDrone best path: %s\nDrone path time: %.2f minutes\nDrone needed to charge: %s\n" +
                            "Drone needed to charge on: %s",resultDrone.getKey().getKey(),
                    resultDrone.getKey().getValue(),resultDrone.getValue(),pathTimeDrone, droneRecharged, chargingSpotDrone);
            printPhysics(moGraphDrone, resultDrone.getValue());
            LOGGER.log(Level.INFO, body);
            WriteFile.write(DELIVERYRUNPATH,body);
        }
        else {
            body = String.format("\n\n\nNo drone can make this path!\n\n\nBest scooter model: %s" +
                    "\nBest scooter energetic cost: %.2f kWh\nScooter best path: %s\nScooter path time: %.2f minutes\nScooter needed to recharge: %s\n" +
                            "Scooter needed to recharge on: %s",resultScooter.getKey().getKey(),
                    resultScooter.getKey().getValue(),resultScooter.getValue(),pathTimeScooter, scooterRecharged, chargingSpotScooter);
            printPhysics(moGraphScooter, resultScooter.getValue());
            LOGGER.log(Level.INFO, body);
            WriteFile.write(DELIVERYRUNPATH,body);
        }
        return lstReturn;
    }


    public Pair<VehicleModel, Double> getBestPossibleModel(List<VehicleModel> vList, List<Address> path,
                                                           List<Order> orderList) {
        VehicleModel mostCapableVModel = null;
        double lowestCost = Double.MAX_VALUE;
        double energiaModelo100;
        double energiaModeloPath;
        for(VehicleModel vehicle : vList) {
            // value/1000 for kwh
            energiaModelo100 = moVehicleDB.getEnergyByVehicleModel(vehicle.getId()) / 1000;
            energiaModeloPath = calculatePathCost(path, orderList, vehicle, energiaModelo100);
            if(energiaModeloPath < lowestCost) {
                mostCapableVModel = vehicle;
                lowestCost = energiaModeloPath;
            }
        }
        if(lowestCost == Double.MAX_VALUE) return new Pair<>(null, Double.MAX_VALUE);
        return new Pair<>(mostCapableVModel, lowestCost);
    }
*/
    public Double calculatePathCost(List<Address> allAddresses, List<Order> orderList,
                                    VehicleModel vModel, Double maxEnergy) {
        this.chargingSpots = new ArrayList<>();
        this.pathRecharge = false;
        this.pathTime = 0;
        double energyRemaining = maxEnergy;
        double energyCost = 0;
        double tempEnergy;
        double tempTime = 0;
        double totalMass = vModel.getWeight();
        if (vModel.getVehicleType().equals(VehicleType.SCOOTER))
            totalMass += Constants.DEFAULT_COURIER_WEIGHT;
        else if (vModel.getVehicleType().equals(VehicleType.NOTDEFINED))
            return null;
        Map<Address, Double> orderWeightMap = new HashMap<>();
        for (Order order : orderList) {
            orderWeightMap.put(order.getClient().getAddress(), order.getTotalWeight());
            totalMass += order.getTotalWeight();
        }

        for (int i = 0; i < allAddresses.size() - 1; i++) {
            Path path;
            if (vModel.getVehicleType().equals(VehicleType.SCOOTER))
                path = getPathFromAddresses(this.moGraphScooter, allAddresses.get(i), allAddresses.get(i + 1));
            else
                path = getPathFromAddresses(this.moGraphDrone, allAddresses.get(i), allAddresses.get(i + 1));
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
                        totalMass, kineticFrictionCoefficient);
            }
            else {
                tempPair = EnergyCalculator.calculateDroneEnergy(totalMass, winSpeed, winDegree,
                        distanceUsingCoordinates);
            }
            tempEnergy = tempPair.getKey();
            tempTime += tempPair.getValue();
            energyCost += tempEnergy;
            energyRemaining -= tempEnergy;
            if (energyRemaining < 0) {
                chargingSpots = new ArrayList<>();
                pathRecharge = false;
                return Double.MAX_VALUE;
            }
            if (checkIfAddressIsPharmacy(allAddresses.get(i + 1))) {
                if(i + 1 != allAddresses.size() - 1 &&
                        moDeliveryRunDB.checkValidChargingSlot(allAddresses.get(i + 1))) {
                    energyRemaining = maxEnergy;
                    chargingSpots.add(allAddresses.get(i + 1));
                }
            }
        }
        if(energyCost > maxEnergy) {
            pathRecharge = true;
        } else {
            pathRecharge = false;
            chargingSpots = new ArrayList<>();
        }

        pathTime = tempTime / 60;
        return energyCost;
    }


    public List<Address> calculateMostEfficientPath(VehicleType vType, Address startAddress,
                                                          Address endAddress, List<Address> deliveryPoints) {
        //Pass the weight of each edge of the graph to the enery cost
        Graph<Address, Path> cloneGraph;

        if(vType.equals(VehicleType.SCOOTER)) cloneGraph = this.moGraphScooter.clone();
        else if(vType.equals(VehicleType.DRONE)) cloneGraph = this.moGraphDrone.clone();
        else return null;

        for(Edge<Address, Path> e : cloneGraph.edges()) {
            double distanceUsingCoordinates = e.getVOrig().distanceTo(e.getVDest());
            double localHeightDifference = e.getVOrig().getAltitude() - e.getVDest().getAltitude();
            double totalMass = 1;


            double energia;
            if(vType.equals(VehicleType.SCOOTER)) {
                energia = EnergyCalculator.calculateScooterEnergy(distanceUsingCoordinates, e.getElement().getWindAngle(),
                        e.getElement().getWindSpeed(), localHeightDifference, totalMass,
                        e.getElement().getKineticFrictionCoefficient()).getKey();
            }
            else {
                energia = EnergyCalculator.calculateDroneEnergy(totalMass, e.getElement().getWindSpeed(),
                        e.getElement().getWindAngle(), distanceUsingCoordinates).getKey();
            }
            e.setWeight(energia);
        }

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

    public List<Pair<LinkedList<Address>, Double>> calculatePermutationPaths
            (Graph<Address, Path> g, Address a1, Address a2, List<LinkedList<Address>> permutations) {    //O(k^2*V^2)

        List<Pair<LinkedList<Address>, Double>> listOfPaths = new LinkedList<>();
        for (LinkedList<Address> list : permutations) {                                                                   //O(k^2*V^2)
            list.addFirst(a1);
            list.addLast(a2);
            LinkedList<Address> permutationPath = new LinkedList<>();
            double totalCost = 0.0;
            for (int i = 0; i < list.size() - 1; i++) {                                                                  //O(k*V^2)
                LinkedList<Address> tempPath = new LinkedList<>();
                //CALCULATE ENERGY
                totalCost += GraphAlgorithms.shortestPath(g, list.get(i), list.get(i + 1), tempPath);   //O(V^2)
                if (tempPath.isEmpty())
                    return new LinkedList<>();
                tempPath.removeLast();
                permutationPath.addAll(tempPath);
            }
            permutationPath.add(a2);
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

}
