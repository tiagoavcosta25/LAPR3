package lapr.project.model.service;

import lapr.project.data.ScooterDB;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.VehicleModel;
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
import static org.mockito.ArgumentMatchers.booleanThat;
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
        this.expectedScooter = new Scooter(new VehicleModel(), new Pharmacy());
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
        when(mockScooterDB.updateScooterFromDB(1, 100, "Charging",
                2.0f, 2.0f, 30, 200f, 20f, 1)).thenReturn(true);

        boolean result = scooterService.updateScooterFromDB(1, 100,
                "Charging", 2.0f, 2.0f, 30,
                200f, 20f, 1);
        assertTrue(result);

        result = scooterService.updateScooterFromDB(-1, 100, "Charging",
                2.0f, 2.0f, 30, 200f, 20f, 1);
        assertFalse(result);

        when(mockScooterDB.updateScooterFromDB(1, 100, "Charging",
                2.0f, 2.0f, 30, 200f, 20f, 1)).thenReturn(false);
        result = scooterService.updateScooterFromDB(1, 100, "Charging",
                2.0f, 2.0f, 30, 200f, 20f, 1);
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
        Scooter result = scooterService.newScooter(new VehicleModel(), new Pharmacy());
        assertEquals(expectedScooter, result);
    }

    @Test
    void registerScooter() {
        System.out.println("registerScooter");
        when(mockScooterDB.registerScooter(expectedScooter)).thenReturn(1);
        int result = scooterService.registerScooter(expectedScooter);
        assertEquals(1, result);

        when(mockScooterDB.registerScooter(null)).thenReturn(-1);
        result = scooterService.registerScooter(null);
        assertEquals(-1, result);
    }

    @Test
    void parkScooter() {
        boolean real = scooterService.parkScooter(12, 12);
        assertFalse(real);
    }
}