package lapr.project.model;

import javafx.util.Pair;
import lapr.project.data.DeliveryRunDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.VehicleDB;
import lapr.project.graph.map.Edge;
import lapr.project.graph.map.Graph;
import lapr.project.graph.map.GraphAlgorithms;
import lapr.project.utils.Constants;
import lapr.project.utils.EnergyCalculator;
import lapr.project.utils.WriteFile;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorldMap {

    private static final String DELIVERYRUNPATH = "DeliveryRunPath";
    private static final Logger LOGGER = Logger.getLogger(WorldMap.class.getName());
    private Graph<Address, Path> moGraphScooter;
    private Graph<Address, Path> moGraphDrone;
    private DeliveryRunDB moDeliveryRunDB;
    private VehicleDB moVehicleDB;
    private PharmacyDB moPharmacyDB;

    public WorldMap() {
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
        Iterable<Address> iterable = null;
        if(vehicleType.equals(VehicleType.SCOOTER))
            iterable = moGraphScooter.vertices();
        else if(vehicleType.equals(VehicleType.DRONE))
            iterable = moGraphDrone.vertices();
        if(iterable == null) return null;
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


    public Pair<Pair<VehicleModel, Double>, List<Address>> pathsWithPharmacies
    (Graph<Address, Path> g, Address s, Address d, List<VehicleModel> vmList, List<Order> orderList) {

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
            if (localPathList.size() < g.numVertices() * 2) {
                pathsWithPharmaciesCalculator(g, adj, d, localPathList, result, vmList, orderList);
            }
            localPathList.removeLast();
        }
    }

    /**
     * CALCULATE PATH COST
     */

    public Pair<Pair<VehicleModel, Double>, List<Address>> calculateBestVehicleAndBestPath(List<Order> orderList) {
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

    public Pair<Pair<VehicleModel, Double>, List<Address>> calculateBestVehicleForMostEficientPath
            (List<Order> orderList, Pharmacy pharmacy, List<VehicleModel> scooterList, List<VehicleModel> droneList) {

        List<Address> deliveryPoints = new ArrayList<>();
        for(Order o : orderList) {
            deliveryPoints.add(o.getClient().getAddress());
        }

        List<Address> scooterPath = calculateMostEfficientPath(VehicleType.SCOOTER, pharmacy.getAddress(),
                pharmacy.getAddress(), deliveryPoints);
        List<Address> dronePath = calculateMostEfficientPath(VehicleType.DRONE, pharmacy.getAddress(),
                pharmacy.getAddress(), deliveryPoints);

        Pair<VehicleModel, Double> bestScooter = getBestPossibleModel(scooterList, scooterPath, orderList);
        Pair<VehicleModel, Double> bestDrone = getBestPossibleModel(droneList, dronePath, orderList);

        String body;
        if(bestScooter.getValue() != Double.MAX_VALUE && bestDrone.getValue() != Double.MAX_VALUE) {
            body = String.format("\n\n\nBest scooter model: %s\nBest scooter energetic cost: %.2f" +
                    "\nScooter best path: %s\n\n\nBest drone model: %s\nBest drone energetic cost: %.2f" +
                    "\nDrone best path: %s",bestScooter.getKey(),bestScooter.getValue(),scooterPath,bestDrone.getKey(),
                    bestDrone.getValue(),dronePath);
            LOGGER.log(Level.INFO, body);
            WriteFile.write(DELIVERYRUNPATH,body);
            if(bestScooter.getValue() < bestDrone.getValue()) {
                return new Pair<>(bestScooter, scooterPath);
            }
            return new Pair<>(bestDrone, dronePath);
        }

        Pair<Pair<VehicleModel, Double>, List<Address>> resultScooter = null;
        Pair<Pair<VehicleModel, Double>, List<Address>> resultDrone = null;
        if(bestScooter.getValue() == Double.MAX_VALUE) {
            resultScooter = pathsWithPharmacies(this.moGraphScooter,
                    pharmacy.getAddress(), pharmacy.getAddress(), scooterList, orderList);
        }
        if(bestDrone.getValue() == Double.MAX_VALUE) {
            resultDrone = pathsWithPharmacies(this.moGraphDrone,
                    pharmacy.getAddress(), pharmacy.getAddress(), droneList, orderList);
        }
        //IN CASE WITHOUT PHARMACIES IN CONSIDERATION DOESNT WORK, CALCULATE WITH PHARMACIES

        if(resultDrone == null && resultScooter == null && bestScooter.getValue() == Double.MAX_VALUE &&
                bestDrone.getValue() == Double.MAX_VALUE) {
            body = "\n\n\nThere is no scooter model nor drone model that can make this path!";
            LOGGER.log(Level.INFO, body);
            WriteFile.write(DELIVERYRUNPATH,body);
            return null;
        }
        else if(resultDrone == null && resultScooter == null && bestScooter.getValue() == Double.MAX_VALUE) {
            body = String.format("\n\n\nNo scooter model could make this path!\n\n\nBest drone model: %s" +
                    "\nBest drone energetic cost: %.2f\nDrone best path: %s",bestDrone.getKey(),bestDrone.getValue(),
                    dronePath);
            LOGGER.log(Level.INFO, body);
            WriteFile.write(DELIVERYRUNPATH,body);
            return new Pair<>(bestDrone, dronePath);
        }
        else if(resultDrone == null && resultScooter == null && bestDrone.getValue() == Double.MAX_VALUE) {
            body = String.format("\n\n\nNo drone model could make this path!\n\n\nBest scooter model: %s" +
                    "\nBest scooter energetic cost: %.2f\nScooter best path: %s",bestScooter.getKey(),bestScooter.getValue(),
                    scooterPath);
            LOGGER.log(Level.INFO, body);
            WriteFile.write(DELIVERYRUNPATH,body);
            return new Pair<>(bestScooter, scooterPath);
        }
        else if(resultScooter != null && resultDrone != null) {
            body = String.format("\n\n\nBest scooter model: %s\nBest scooter energetic cost: %.2f\n" +
                    "Scooter best path: %s\n\n\nBest drone model: %s\nBest drone energetic cost: %.2f\n" +
                    "Drone best path: %s",resultScooter.getKey().getKey(),resultScooter.getKey().getValue(),
                    resultScooter.getValue(),resultDrone.getKey().getKey(),resultDrone.getKey().getValue(),
                    resultDrone.getValue());
            LOGGER.log(Level.INFO, body);
            WriteFile.write(DELIVERYRUNPATH,body);
            if(resultScooter.getKey().getValue() < resultDrone.getKey().getValue())
                return resultScooter;
            return resultDrone;
        }
        else if(resultScooter == null && resultDrone != null) {
            body = String.format("\n\n\nNo scooter can make this path!\n\n\nBest drone model: %s" +
                    "\nBest drone energetic cost: %.2f\nDrone best path: %s",resultDrone.getKey().getKey(),
                    resultDrone.getKey().getValue(),resultDrone.getValue());
            LOGGER.log(Level.INFO, body);
            WriteFile.write(DELIVERYRUNPATH,body);
            if(bestScooter.getValue() < resultDrone.getKey().getValue())
                return new Pair<>(bestScooter, scooterPath);
            return resultDrone;
        }
        else if(resultScooter != null) {
            body = String.format("\n\n\nNo drone can make this path!\n\n\nBest scooter model: %s" +
                    "\nBest scooter energetic cost: %.2f\nScooter best path: %s",resultScooter.getKey().getKey(),
                    resultScooter.getKey().getValue(),resultScooter.getValue());
            LOGGER.log(Level.INFO, body);
            WriteFile.write(DELIVERYRUNPATH,body);
            if(resultScooter.getKey().getValue() < bestDrone.getValue())
                return resultScooter;
            return new Pair<>(bestDrone, dronePath);
        }
        return null;
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

    public Double calculatePathCost(List<Address> allAddresses, List<Order> orderList,
                                    VehicleModel vModel, Double maxEnergy) {

        double energyRemaining = maxEnergy;
        double energyCost = 0;
        double tempEnergy;
        double totalMass = vModel.getWeight();
        if(vModel.getVehicleType().equals(VehicleType.SCOOTER))
            totalMass += Constants.DEFAULT_COURIER_WEIGHT;
        else if(vModel.getVehicleType().equals(VehicleType.NOTDEFINED))
            return null;
        Map<Address, Double> orderWeightMap = new HashMap<>();
        for(Order order : orderList) {
            orderWeightMap.put(order.getClient().getAddress(), order.getTotalWeight());
            totalMass += order.getTotalWeight();
        }

        for(int i = 0; i < allAddresses.size() - 1; i++) {
            Path path;
            if(vModel.getVehicleType().equals(VehicleType.SCOOTER))
                path = getPathFromAddresses(this.moGraphScooter, allAddresses.get(i), allAddresses.get(i + 1));
            else
                path = getPathFromAddresses(this.moGraphDrone, allAddresses.get(i), allAddresses.get(i + 1));
            double distanceUsingCoordinates = allAddresses.get(i).distanceTo(allAddresses.get(i + 1));
            double localHeightDifference = allAddresses.get(i + 1).getAltitude() - allAddresses.get(i).getAltitude();
            double winDegree = path.getWindAngle();
            double winSpeed = path.getWindSpeed();
            double kineticFrictionCoefficient = path.getKineticFrictionCoefficient();
            if(orderWeightMap.containsKey(allAddresses.get(i))) {
                totalMass -= orderWeightMap.get(allAddresses.get(i));
            }
            if(vModel.getVehicleType().equals(VehicleType.SCOOTER))
                tempEnergy = EnergyCalculator.calculateScooterEnergy(distanceUsingCoordinates, winDegree, winSpeed,
                        localHeightDifference,
                        totalMass, kineticFrictionCoefficient);
            else
                tempEnergy = EnergyCalculator.calculateDroneEnergy(totalMass, winSpeed, winDegree,
                        distanceUsingCoordinates);
            energyCost += tempEnergy;
            energyRemaining -= tempEnergy;
            if(energyRemaining < 0) {
                return Double.MAX_VALUE;
            }
            if(checkIfAddressIsPharmacy(allAddresses.get(i + 1)))
                energyRemaining = maxEnergy;
        }
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
                        e.getElement().getKineticFrictionCoefficient());
            }
            else {
                energia = EnergyCalculator.calculateDroneEnergy(totalMass, e.getElement().getWindSpeed(),
                        e.getElement().getWindAngle(), distanceUsingCoordinates);
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
                if (tempPath.size() == 0)
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
