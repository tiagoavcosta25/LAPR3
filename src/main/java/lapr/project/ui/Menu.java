package lapr.project.ui;

public class Menu {

    private Menu() {
    }

    public static void displayHomeScreen() {
        System.out.println("\n\n       WELCOME TO OUR APPLICATION\n\n&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n" +
                "&&&&&&&&&&&&&&&&&&&&&&&&    &&&&&&&&&&&&\n" +
                "&&&&&&&&&&&&&&&&&&&&&&      &&&&&&&&&&&&\n" +
                "&&&&&&&&&&&&&&&&&&&&&     &&&&&&&&&&&&&&\n" +
                "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n" +
                "&&&&&&&&                          &&&&&&\n" +
                "&&&&&&                            &&&&&&\n" +
                "&&&&&                           &&&&&&&&\n" +
                "&&&&&                          &&&&&&&&&\n" +
                "&&&&&             TEAM         &&&&&&&&&\n" +
                "&&&&&             LISA          &&&&&&&&\n" +
                "&&&&&,            G-21            &&&&&&\n" +
                "&&&&&&                              &&&&\n" +
                "&&&&&&&&                           &&&&&\n" +
                "&&&&&&&&&                        &&&&&&&\n" +
                "&&&&&&&&&&&#         &&        &&&&&&&&&\n" +
                "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n");
        System.out.println("António Barbosa <1190404@isep.ipp.pt>\n" +
                "Ernesto Rodrigues <1190560@isep.ipp.pt>\n" +
                "Jessica Alves <1190682@isep.ipp.pt>\n" +
                "Pedro Santos <1190967@isep.ipp.pt>\n" +
                "Rodrigo Costa <1191014@isep.ipp.pt>\n" +
                "Tiago Costa <1191460@isep.ipp.pt>\n");
        System.out.println("**************************************");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Do Nothing
        }
        Menu.clear();
    }

    public static void displayMenu(String strTitle, String strContent) {
        System.out.println("****************" + strTitle + "*****************");
        System.out.println(strContent);
        System.out.println("**************************************");
        System.out.print("Choose An Option: ");
    }

    public static void clear() {
        for (int i = 0; i < 100; i++){
            System.out.println();
        }
    }

    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Do Nothing
        }
    }

    public static void runUI(UI oUI){
        oUI.run();
    }
}
