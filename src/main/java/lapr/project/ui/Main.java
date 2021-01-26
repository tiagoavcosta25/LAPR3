package lapr.project.ui;

import lapr.project.ui.console.LoginUI;
import lapr.project.ui.console.RegisterClientUI;
import lapr.project.ui.console.RegisterCourierUI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);
            LOGGER.log(Level.INFO, "Importing Data to the Database...");
            //FileReader.readFiles();
            LOGGER.log(Level.INFO, "Data Imported.");

            int intOp;

            do{
                Menu.clear();
                Menu.displayMenu("MENU", "[1] Login\n[2] Create A Client\n[3] Create A Courier\n\n[0] Exit The Program");
                intOp = sc.nextInt();

                switch(intOp){
                    case 1: {
                        Menu.clear();
                        LoginUI UI = new LoginUI();
                        UI.run();
                        break;
                    }
                    case 2: {
                        Menu.clear();
                        RegisterClientUI UI = new RegisterClientUI();
                        UI.run();
                        break;
                    }
                    case 3: {
                        Menu.clear();
                        RegisterCourierUI UI = new RegisterCourierUI();
                        UI.run();
                        break;
                    }
                    case 0: {
                        LOGGER.log(Level.INFO, "Thank You For Using Our Application.");
                        break;
                    }
                    default:
                        LOGGER.log(Level.WARNING, "Choose a valid option.");
                        break;
                }
            } while (intOp != 0);
        } catch (Exception e){
            LOGGER.log(Level.WARNING, "There was an error.");
        }
    }
}