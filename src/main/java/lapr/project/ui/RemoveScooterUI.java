package lapr.project.ui;

import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.RegisterScooterController;
import lapr.project.controller.RemoveScooterController;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.registration.PharmacyRegistration;

import java.util.List;
import java.util.Scanner;

public class RemoveScooterUI {
    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            RegisterScooterController oRegisterCtrl = new RegisterScooterController();
            RemoveScooterController oRemoveCtrl = new RemoveScooterController();

            PharmacyRegistration m_oPharmacyRegistration = null;
            Pharmacy m_oPharmacy = m_oPharmacyRegistration.getPharmacyByManagerEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());


            List<Scooter> lstScooters = oRegisterCtrl.getScooters(m_oPharmacy.getId());

            for(Scooter s : lstScooters){
                System.out.println(s.toString());
            }

            System.out.print("\nChoose the Scooter's Id to Remove: ");
            Integer intScooterId = Integer.parseInt(sc.nextLine());
            System.out.println();

            Scooter oScooter = new Scooter();
            for(Scooter s : lstScooters){
                if (s.hasId(intScooterId)){
                    oScooter = s;
                    break;
                }
            }

            if (oRemoveCtrl.removeScooter(intScooterId)) {
                System.out.println("Operation was Successfull!");

            } else System.out.println("Operation was NOT Successfull!");
        } catch (Exception e) {
            System.out.println("Format ERROR!");
            System.out.println("Operation NOT successfull!");
        }
    }
}
