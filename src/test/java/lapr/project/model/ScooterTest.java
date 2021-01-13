package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class ScooterTest {

    private Scooter scooterTest1;
    private Scooter scooterTest2;
    private Scooter scooterTest3;

    public ScooterTest(){
        scooterTest1 = new Scooter();
        scooterTest2 = new Scooter(2.0f, 2.0f, 20f,
                "Not Charging", 20f, 100, 20f, new Pharmacy());
        scooterTest3 = new Scooter(3.0f, 3.0f, 3.0f,
                "Not Charging", 30f, 100, 30f, new Pharmacy());
    }

    @Test
    void testToString() {
        String expected = "Vehicle{Id=-1, PharmacyId=-1, Potency=-1.0, Weight=-1.0, MaxPayload=-1.0, " +
                "CharginStatus='Not Charging', Battery=Battery{Id=0, BatteryPerc=-1.0, BatteryCapacity=-1, " +
                "BatteryVoltage=-1.0}, Pharmacy=Pharmacy{m_intId=-1, m_strName='No name.', " +
                "m_oAddress=Address{m_id=-1, m_latitude=-22.0, m_longitude=-22.0, m_streetName='No Street " +
                "Name', m_doorNumber='No Door Number', m_postalCode='No Postal Code', m_locality='No " +
                "Locality', m_country='No Country'}, m_mapStock={}}}";
        String real = scooterTest1.toString();
        assertEquals(expected, real);
    }

}
