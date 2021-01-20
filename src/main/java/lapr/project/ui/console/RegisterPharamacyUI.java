package lapr.project.ui.console;

import lapr.project.controller.RegisterPharmacyController;

public class RegisterPharamacyUI {
    public void run(){
        try{
            RegisterPharmacyController oCtrl = new RegisterPharmacyController();

            oCtrl.newPharmacy("Pharmacy #1", "info@pharmacy1.com", 19d, -19d, 10d,
                    "Rua da Farmácia", "n 119", "4400-123", "Vila Nova de Gaia", "Portugal");

            oCtrl.registerPharmacy();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
