package lapr.project.ui;

import java.util.Scanner;

public class Menu implements Runnable {

    static Scanner sc = new Scanner(System.in);

    public void run() {
        menu();
    }

    public static void menu() {
        System.out.println("Welcome to our SARS-COVID Vaccine Distribution App!");
        System.out.println("1- Register Client");
        System.out.println("2- Login");
        RegisterClientUI registerClientUI = new RegisterClientUI();
        LoginUI loginUI = new LoginUI();

        try {
            int choice = Integer.parseInt(sc.nextLine());
            while (choice != 1 && choice != 2) {
                choice = Integer.parseInt(sc.nextLine());
            }
            switch (choice) {
                case 1:
                    registerClientUI.run();
                    break;
                case 2:
                    loginUI.run();
                    break;
            }
        } catch (Exception e) {
            System.out.println("There has been an error with your request!");
        }
    }
}
