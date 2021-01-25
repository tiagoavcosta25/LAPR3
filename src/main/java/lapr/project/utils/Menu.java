package lapr.project.utils;

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
}
