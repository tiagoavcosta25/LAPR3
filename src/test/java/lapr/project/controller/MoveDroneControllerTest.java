package lapr.project.controller;

import javafx.util.Pair;
import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class MoveDroneControllerTest {

    public MoveDroneController oMoveDroneController;

    public MoveDroneControllerTest() {
        this.oMoveDroneController = new MoveDroneController();
    }

    @Test
    void moveDrone() {
        System.out.println("moveDrone");
        Pair<LinkedList<Address>, Double> pathInfo = new Pair<>(null, 250.0);
        List<Order> list = new ArrayList<>();
        list.add(new Order(1f, 0.25f, 0, new Date(System.currentTimeMillis()), null, null, false, new Client(), new Pharmacy(), new TreeMap<>()));
        list.add(new Order(1f, 1f, 0, new Date(System.currentTimeMillis()), null, null, false, new Client(), new Pharmacy(), new TreeMap<>()));
        DeliveryRun dr = new DeliveryRun(new Courier(), list);
        dr.setVehicle(new Drone(new VehicleModel("Modelo1", 250,10, 10, new Battery(5,4,80), VehicleType.DRONE), new Pharmacy()));
        dr.setStatus(DeliveryStatus.IDLE);
        boolean result = this.oMoveDroneController.moveDrone(pathInfo, dr);
        assertTrue(result);
    }

    @Test
    void moveDrone2() {
        System.out.println("moveDrone2");
        Pair<LinkedList<Address>, Double> pathInfo = new Pair<>(null, 10.0);
        DeliveryRun dr = new DeliveryRun(new Courier(), new ArrayList<>());
        dr.setVehicle(new Drone());
        boolean result = this.oMoveDroneController.moveDrone(pathInfo, dr);
        assertFalse(result);
    }


}
