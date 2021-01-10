package lapr.project.ui;

import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.RegisterScooterController;
import lapr.project.controller.ScooterInformationController;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.data.registration.PharmacyRegistration;

import java.util.List;
import java.util.Scanner;

public class ScooterInformationUI {
    public static void main(String[] args) {
        {

            try {
                Scanner sc = new Scanner(System.in);
                RegisterScooterController oRegisterCtrl = new RegisterScooterController();
                ScooterInformationController oInfoCtrl = new ScooterInformationController();

                PharmacyRegistration m_oPharmacyRegistration = null;
                Pharmacy m_oPharmacy = m_oPharmacyRegistration.getPharmacyByManagerEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());


                List<Scooter> lstScooters = oRegisterCtrl.getScooters(m_oPharmacy.getId());

                for(Scooter s : lstScooters){
                    System.out.println(s.toString());
                }

                System.out.print("\nChoose the Scooter's Id to See All Information: ");
                Integer intScooterId = Integer.parseInt(sc.nextLine());
                System.out.println();

                Scooter oScooter = new Scooter();
                for(Scooter s : lstScooters){
                    if (s.hasId(intScooterId)){
                        oScooter = s;
                        break;
                    }
                }

                Scooter m_oScooter = oInfoCtrl.getScooterInformation(intScooterId);
                System.out.println(m_oScooter.toString());

                System.out.println("-1 - to stop the scooter information view");
                Integer intStop = Integer.parseInt(sc.nextLine());
                System.out.println("Operation successfull!");

            } catch (Exception e) {
                System.out.println("Format ERROR!");
                System.out.println("Operation NOT successfull!");
            }
        }
    }
}
