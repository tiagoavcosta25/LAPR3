package lapr.project.ui;

import lapr.project.controller.RegisterCourierController;

public class RegisterCourierUI {


    public static void main(String[] args) throws Exception {

        RegisterCourierController m_ctrl = new RegisterCourierController();

        try {
            if (m_ctrl.newCourier("Ernesto", "ernesto@gmail.com", 250161761, "PT98003506514853185258910")) {
                if (m_ctrl.registersCourier()) {
                    System.out.println("Operation was Successfull!");
                }

            } else System.out.println("Operation was NOT Successfull!");
        } catch (Exception e) {
            System.out.println("Format ERROR!");
            System.out.println("Operation NOT successfull!");


            /**Scanner sc = new Scanner(System.in);
             try {
             System.out.println("Please input the following information:");
             System.out.println("Name:");
             String name = sc.nextLine();
             System.out.println("Email:");
             String email = sc.nextLine();
             System.out.println("NIF:");
             Integer nif = Integer.parseInt(sc.nextLine());

             if (m_ctrl.newCourier("Ernesto", "ernesto@gmail.com", 14809313, "PT98003506514853185258910")) {
             if(m_ctrl.registersCourier()){
             System.out.println("Operation was Successfull!");
             }

             } else System.out.println("Operation was NOT Successfull!");
             } catch (Exception e) {
             System.out.println("Format ERROR!");
             System.out.println("Operation NOT successfull!");
             }
             }*/
        }
    }
}

