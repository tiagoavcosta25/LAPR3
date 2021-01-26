package lapr.project.ui;

import lapr.project.ui.console.LoginUI;
import lapr.project.ui.console.RegisterClientUI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);
            LOGGER.log(Level.INFO, "Importing Data to the Database...");
            FileReader.readFiles();
            LOGGER.log(Level.INFO, "Data Imported.");

            int intOp;

            do{
                Menu.clear();
                Menu.displayMenu("MENU", "[1] Login\n[2] Register Client Account\n\n[0] Exit The Program");
                intOp = sc.nextInt();
                Menu.clear();

                switch(intOp){
                    case 1: {
                        Menu.runUI(new LoginUI());
                        break;
                    }
                    case 2: {
                        Menu.runUI(new RegisterClientUI());
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