package lapr.project.ui;

import lapr.project.controller.AvailableChargingSlotController;
import lapr.project.controller.NotifyAndRemoveController;
import lapr.project.model.ChargingSlot;

public class AvailableChargingSlotUI {


    public static void main(String[] args) throws Exception {

        AvailableChargingSlotController m_ctrl = new AvailableChargingSlotController();

        ChargingSlot chargingSlot = m_ctrl.getAvailableChargingSlot();
        if (chargingSlot != null) {
            System.out.println("Charging Slot Available Information:");
            System.out.println(chargingSlot.toString());
        } else System.out.println("Operation was NOT Successfull!");

    }
}

