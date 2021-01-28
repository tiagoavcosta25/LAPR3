package lapr.project.ui;

public class Menu {

    private Menu() {
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
            e.printStackTrace();
        }
    }

    public static void runUI(UI oUI){
        oUI.run();
    }
}
