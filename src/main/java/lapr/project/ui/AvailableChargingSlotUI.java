package lapr.project.ui;

import lapr.project.controller.AvailableChargingSlotController;
import lapr.project.controller.NotifyAndRemoveController;
import lapr.project.model.ChargingSlot;

public class AvailableChargingSlotUI {


    public static void main(String[] args) throws Exception {

        AvailableChargingSlotController m_ctrl = new AvailableChargingSlotController();

        ChargingSlot chargingSlot = m_ctrl.getAvailableChargingSlot();
        if (chargingSlot != null) {
            System.out.println("-------Charging Slot Available Information-------");
            System.out.println("ID: " + chargingSlot.getM_intId());
            System.out.println("Output Power: " + chargingSlot.getM_outputPower());
            System.out.println("-------Park Information-------");
            System.out.println("ID: " + chargingSlot.getPark().getM_intId());
            System.out.println("Max Slots: " + chargingSlot.getPark().getM_intMaxSlotsNumber());
            System.out.println("-------Pharmacy Information-------");
            System.out.println("ID: " + chargingSlot.getPark().getM_oPharmacy().getId());
            System.out.println("Name : " + chargingSlot.getPark().getM_oPharmacy().getName());

        } else System.out.println("Operation was NOT Successfull!");

    }
}

