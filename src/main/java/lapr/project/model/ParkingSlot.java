package lapr.project.model;

import java.sql.Date;
import java.util.Objects;

public class ParkingSlot {

    private int m_intId;
    private Pharmacy m_oPhamarcy;
    private Scooter m_oScooter;

    private static int DEFAULT_ID = -1;
    private static Pharmacy DEFAULT_PHARMACY = new Pharmacy();
    private static Scooter DEFAULT_SCOOTER = new Scooter();
    private static float DEFAULT_OUTPUT_POWER = -1;

    public ParkingSlot(int intId, Pharmacy oPhamarcy, Scooter oScooter) {
        this.m_intId = intId;
        this.m_oPhamarcy = oPhamarcy;
        this.m_oScooter = oScooter;
    }

    public ParkingSlot(Pharmacy oPhamarcy, Scooter oScooter, float oOutputPower) {
        this.m_intId = DEFAULT_ID;
        this.m_oPhamarcy = oPhamarcy;
        this.m_oScooter = null;
        this.m_outputPower = oOutputPower;
    }

    public ParkingSlot() {
        this.m_intId = DEFAULT_ID;
        this.m_oPhamarcy = DEFAULT_PHARMACY;
        this.m_oScooter = DEFAULT_SCOOTER;
        this.m_outputPower = DEFAULT_OUTPUT_POWER;
    }
}
