package lapr.project.ui;

import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.RegisterScooterController;
import lapr.project.controller.UpdateScooterController;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.data.registration.PharmacyRegistration;

import java.util.List;
import java.util.Scanner;

public class UpdateScooterUI {
    public static void main(String[] args) {
        {

            try {
                Scanner sc = new Scanner(System.in);
                RegisterScooterController oRegisterCtrl = new RegisterScooterController();
                UpdateScooterController oUpdateCtrl = new UpdateScooterController();

                PharmacyRegistration m_oPharmacyRegistration = null;
                Pharmacy m_oPharmacy = m_oPharmacyRegistration.getPharmacyByManagerEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());


                List<Scooter> lstScooters = oRegisterCtrl.getScooters(m_oPharmacy.getId());

                for(Scooter s : lstScooters){
                    System.out.println(s.toString());
                }

                System.out.print("\nChoose the Scooter's Id to Update: ");
                Integer intScooterId = Integer.parseInt(sc.nextLine());
                System.out.println();

                Scooter oScooter = new Scooter();
                for(Scooter s : lstScooters){
                    if (s.hasId(intScooterId)){
                        oScooter = s;
                        break;
                    }
                }

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


                if (oUpdateCtrl.updateScooter(intScooterId, m_fltBatteryPerc, m_strChargingStatus, m_fltPotency,
                        m_fltWeight, m_intBatteryCapacity, m_fltMaxPayload)) {
                    System.out.println("Operation was Successfull!");

                } else System.out.println("Operation was NOT Successfull!");
            } catch (Exception e) {
                System.out.println("Format ERROR!");
                System.out.println("Operation NOT successfull!");
            }
        }
    }
}
