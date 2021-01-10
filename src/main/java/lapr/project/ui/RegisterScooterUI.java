package lapr.project.ui;

import lapr.project.controller.RegisterScooterController;
import lapr.project.model.Pharmacy;
import java.util.Scanner;

public class RegisterScooterUI {

    public static void main(String[] args) throws Exception {
        try {
            Scanner sc = new Scanner(System.in);
            RegisterScooterController oCtrl = new RegisterScooterController();

            System.out.println("Please input the following information:");
            System.out.println("Battery Percentage:");
            float m_fltBatteryPerc = Float.parseFloat(sc.nextLine());
            System.out.println("Charging Status (Charging/Not Charging):");
            String m_strChargingStatus = sc.nextLine();
            System.out.println("Potency:");
            float m_fltPotency = Float.parseFloat(sc.nextLine());
            System.out.println("Weight:");
            float m_fltWeight = Float.parseFloat(sc.nextLine());
            System.out.println("Battery Capacity:");
            int m_intBatteryCapacity = Integer.parseInt(sc.nextLine());
            System.out.println("Maximum Payload:");
            float m_fltMaxPayload = Float.parseFloat(sc.nextLine());

            if (oCtrl.newScooter(m_fltBatteryPerc, m_strChargingStatus, m_fltPotency, m_fltWeight,
                    m_intBatteryCapacity, m_fltMaxPayload, new Pharmacy())) {
                if (oCtrl.registersScooter()) {
                    System.out.println("Operation was Successfull!");
                }

            } else System.out.println("Operation was NOT Successfull!");
        } catch (Exception e) {
            System.out.println("Format ERROR!");
            System.out.println("Operation NOT successfull!");
        }
    }
}
