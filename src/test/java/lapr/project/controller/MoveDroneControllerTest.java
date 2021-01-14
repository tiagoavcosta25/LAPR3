package lapr.project.controller;

import javafx.util.Pair;
import lapr.project.model.Address;
import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class MoveDroneControllerTest {

    public MoveDroneController oMoveDroneController;

    public MoveDroneControllerTest(){
        this.oMoveDroneController = new MoveDroneController();
    }
    @Test
    void moveDrone(){
        System.out.println("moveDrone");
        Pair<LinkedList<Address>, Double> pathInfo = new Pair<>(null, 250.0);
        Drone oDrone = new Drone(250, 5, 2.5f, "charged", 50, 20, 3, new Pharmacy());
        boolean result = this.oMoveDroneController.moveDrone(pathInfo,oDrone);
        assertFalse(result);
    }
    @Test
    void moveDrone2(){
        System.out.println("moveDrone2");
        Pair<LinkedList<Address>, Double> pathInfo = new Pair<>(null, 10.0);
        Drone oDrone = new Drone();
        boolean result = this.oMoveDroneController.moveDrone(pathInfo,oDrone);
        assertTrue(result);
    }



}
