package lapr.project.model.service;

import lapr.project.controller.RegisterScooterController;
import lapr.project.data.ScooterDB;
import lapr.project.model.Client;
import lapr.project.model.Order;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ScooterServiceTest {

    @InjectMocks
    private ScooterService scooterService;

    @Mock
    private ScooterDB mockScooterDB;

    private Scooter expectedScooter;

    @BeforeEach
    void setUp() {
        this.expectedScooter = new Scooter(35.5f, "Charging Test", 250f, 30f,
                100, 20f, new Pharmacy());
        this.scooterService = new ScooterService();
        this.mockScooterDB = Mockito.mock(ScooterDB.class);
        initMocks(this);
    }

    @Test
    void getScooter() {
        System.out.println("getScooter");
        Scooter expected = new Scooter();
        when(mockScooterDB.getScooter(1)).thenReturn(new Scooter());
        Scooter result = scooterService.getScooter(1);
        assertEquals(expected, result);
    }

    @Test
    void updateScooterFromDB() {
        System.out.println("updateScooterFromDB");
        when(mockScooterDB.updateScooterFromDB(1, 100, "Charging", 2.0f, 2.0f, 30, 200f)).thenReturn(true);

        boolean result = scooterService.updateScooterFromDB(1, 100, "Charging", 2.0f, 2.0f, 30, 200f);
        assertTrue(result);

        result = scooterService.updateScooterFromDB(-1, 100, "Charging", 2.0f, 2.0f, 30, 200f);
        assertFalse(result);

        when(mockScooterDB.updateScooterFromDB(1, 100, "Charging", 2.0f, 2.0f, 30, 200f)).thenReturn(false);
        result = scooterService.updateScooterFromDB(1, 100, "Charging", 2.0f, 2.0f, 30, 200f);
        assertFalse(result);
    }

    @Test
    void getScootersList() {
        System.out.println("getScootersList");
        List<Scooter> expectedListScooters = new ArrayList<>(Arrays.asList(new Scooter()));
        when(scooterService.getScootersList(-1)).thenReturn(expectedListScooters);
        List<Scooter>  result = scooterService.getScootersList(-1);
        assertEquals(expectedListScooters, result);
    }

    @Test
    void removeScooterFromDB() {
        System.out.println("removeScooterFromDB");
        when(mockScooterDB.removeScooterFromDB(1)).thenReturn(true);
        boolean result = scooterService.removeScooterFromDB(1);
        assertTrue(result);

        when(mockScooterDB.removeScooterFromDB(-1)).thenReturn(false);
        result = scooterService.removeScooterFromDB(2);
        assertFalse(result);
    }

    @Test
    void newScooter() {
        System.out.println("newScooter");
        Scooter result = scooterService.newScooter(35.5f, "Charging Test", 250f, 30f,
                100, 20f, new Pharmacy());
        assertEquals(expectedScooter, result);
    }

    @Test
    void registerScooter() {
        System.out.println("registerScooter");
        when(mockScooterDB.registerScooter(expectedScooter)).thenReturn(true);
        boolean result = scooterService.registerScooter(expectedScooter);
        assertTrue(result);

        when(mockScooterDB.registerScooter(null)).thenReturn(false);
        result = scooterService.registerScooter(null);
        assertFalse(result);
    }
}