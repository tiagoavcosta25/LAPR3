package lapr.project.model;

import java.util.Objects;

public abstract class Vehicle {
    private int m_intId;
    private double m_dblBatteryPerc;
    private VehicleModel m_oModel;
    private Pharmacy m_oPharmacy;

    private static int DEFAULT_ID = -1;
    private static double DEFAULT_BATTERY_PERC = -1;
    private static VehicleModel DEFAULT_VEHICLE_MODEL = new VehicleModel();
    private static Pharmacy DEFAULT_PHARMACY = new Pharmacy();

    private static double STARTING_BATTERY_PERC = 100;

    public Vehicle() {
        this.m_intId = DEFAULT_ID;
        this.m_dblBatteryPerc = DEFAULT_BATTERY_PERC;
        this.m_oModel = DEFAULT_VEHICLE_MODEL;
        this.m_oPharmacy = DEFAULT_PHARMACY;
    }

    public Vehicle(VehicleModel oModel, Pharmacy oPharmacy) {
        this.m_intId = DEFAULT_ID;
        this.m_dblBatteryPerc = STARTING_BATTERY_PERC;
        this.m_oModel = oModel;
        this.m_oPharmacy = oPharmacy;
    }

    public Vehicle(int intId, VehicleModel oModel, Pharmacy oPharmacy) {
        this.m_intId = intId;
        this.m_dblBatteryPerc = STARTING_BATTERY_PERC;
        this.m_oModel = oModel;
        this.m_oPharmacy = oPharmacy;
    }

    public Vehicle(int intId, double dblBatteryPerc, VehicleModel oModel, Pharmacy oPharmacy) {
        this.m_intId = intId;
        this.m_dblBatteryPerc = dblBatteryPerc;
        this.m_oModel = oModel;
        this.m_oPharmacy = oPharmacy;
    }

    public int getId() {
        return m_intId;
    }

    public void setId(int intId) {
        this.m_intId = intId;
    }

    public boolean hasId(Integer intId) {return this.getId() == intId;}

    public Pharmacy getPharmacy() {
        return m_oPharmacy;
    }

    public void setPharmacy(Pharmacy oPharmacy) {
        this.m_oPharmacy = oPharmacy;
    }

    public VehicleModel getModel() {
        return m_oModel;
    }

    public void setModel(VehicleModel oModel) {
        this.m_oModel = oModel;
    }

    public double getBatteryPerc() {
        return m_dblBatteryPerc;
    }

    public void setBatteryPerc(double dblBatteryPerc) {
        this.m_dblBatteryPerc = dblBatteryPerc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return m_intId == vehicle.m_intId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "Id=" + m_intId +
                ", BatteryPerc=" + m_dblBatteryPerc +
                ", Model=" + m_oModel +
                ", Pharmacy=" + m_oPharmacy +
                '}';
    }

}


