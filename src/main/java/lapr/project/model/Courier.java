package lapr.project.model;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;
/**
 * Courier class.
 * <p>
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 *
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class Courier extends User {

    /**
     * Courier's Iban
     */
    private String mIban;
    /**
     * Courier's pharmacy
     */
    private Pharmacy mPharmacy;

    /**
     * Courier constructor initilized with all default attributes.
     */
    public Courier() {
        super();
        this.setIban("No Iban");
        this.setPharmacy(new Pharmacy());
    }

    /**
     * Courier constructor recieves the Courier's id, name, email password, nif, iban and Pharmacy's instance
     *
     * @param intId       Courier's Id
     * @param strName     Courier's name
     * @param strEmail    Courier's email
     * @param strPassword Courier's password
     * @param strNif      Courier's nif
     * @param strIban     Courier's Iban
     * @param oPharmacy   Courier's pharmacy
     */
    public Courier(int intId, String strName, String strEmail, String strPassword, Integer strNif, String strIban, Pharmacy oPharmacy) {
        super(intId, strEmail, strPassword, strNif, strName);
        this.setIban(strIban);
        this.setPharmacy(oPharmacy);
    }

    /**
     * Courier constructor recieves the Courier's , name, email password, nif, iban and Pharmacy's instance
     *
     * @param strName     Courier's name
     * @param strEmail    Courier's email
     * @param strPassword Courier's password
     * @param strNif      Courier's nif
     * @param strIban     Courier's Iban
     * @param oPharmacy   Courier's pharmacy
     * @throws NoSuchAlgorithmException
     */
    public Courier(String strName, String strEmail, String strPassword, Integer strNif, String strIban, Pharmacy oPharmacy) throws NoSuchAlgorithmException {
        super(strEmail, strPassword, strNif, strName);
        this.setPharmacy(oPharmacy);
        this.setIban(strIban);
    }

    /**
     * The method compares two instances of Courier by their Iban's.
     *
     * @param o the other Object which we want to compare to
     * @return true if the Iban is the same, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Courier courier = (Courier) o;
        return Objects.equals(mIban, courier.mIban);
    }

    /**
     * Returns the Courier's Iban
     *
     * @return Courier's Iban
     */
    public String getIban() {
        return mIban;
    }

    /**
     * Sets the Courier's Iban
     *
     * @param strIban Courier's Iban
     */
    public void setIban(String strIban) {
        this.mIban = strIban;
    }

    /**
     * Returns the Courier's pharmacy
     *
     * @return Courier's pharmacy
     */
    public Pharmacy getPharmacy() {
        return mPharmacy;
    }

    /**
     * Sets the Courier's pharmacy
     *
     * @param oPharmacy Courier's pharmacy
     */
    public void setPharmacy(Pharmacy oPharmacy) {
        this.mPharmacy = oPharmacy;
    }
}

