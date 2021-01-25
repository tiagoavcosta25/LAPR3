package lapr.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @Test
    void displayMenu() {
        Menu.displayMenu("Teste","Teste");
    }

    @Test
    void clear() {
        Menu.clear();
    }
}