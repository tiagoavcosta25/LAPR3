package lapr.project.model;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Client.
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
public class Client extends User {

    /**
     * Client Credits
     */
    private Integer mintCredits;

    /**
     * Client Address
     */
    private Address moAddress;

    /**
     * Client's List of Credit Card
     */
    private List<CreditCard> mLstCreditCard;

    /**
     * Empty constructor of Client, which sets all the atributes
     * to default values
     */
    public Client() {
        super();
        this.mintCredits = 0;
        this.moAddress = new Address();
        this.mLstCreditCard = new ArrayList<>();
    }

    /**
     * Client constructor, which sets all the atributes to the ones
     * given by parameter
     *
     * @param name          Client name
     * @param nif           Client NIF
     * @param email         Client email
     * @param password      Client password
     * @param latitude      Client's Address latitude
     * @param longitude     Client's Address longitude
     * @param altitude      Client's Address altitude
     * @param streetName    Client's Address street mame
     * @param doorNumber    Client's Address door number
     * @param postalCode    Client's Address postal code
     * @param locality      Client's Address locality
     * @param country       Client's Address country
     * @param lstCreditCard Client's List of Credit Cards
     * @throws NoSuchAlgorithmException Exception raised when the encryptation of the password
     *                                  doesn't find an algorithm
     */
    public Client(String name, Integer nif, String email, String password, Double latitude, Double longitude, Double altitude, String streetName,
                  String doorNumber, String postalCode, String locality, String country, List<CreditCard> lstCreditCard) throws NoSuchAlgorithmException {
        super(email, password, nif, name);
        this.mintCredits = 0;
        this.moAddress = new Address(latitude, longitude, altitude, streetName, doorNumber, postalCode, locality, country);
        this.mLstCreditCard = new ArrayList<>(lstCreditCard);
    }

    /**
     * Client constructor, which sets all the atributes to the ones
     * given by parameter
     *
     * @param id            Client ID
     * @param name          Client name
     * @param nif           Client NIF
     * @param email         Client email
     * @param password      Client password
     * @param latitude      Client's Address latitude
     * @param longitude     Client's Address longitude
     * @param altitude      Client's Address altitude
     * @param streetName    Client's Address street mame
     * @param doorNumber    Client's Address door number
     * @param postalCode    Client's Address postal code
     * @param locality      Client's Address locality
     * @param country       Client's Address country
     * @param lstCreditCard Client's List of Credit Cards
     */
    public Client(Integer id, String name, Integer nif, String email, String password, Integer credits, Double latitude, Double longitude,
                  Double altitude, String streetName, String doorNumber, String postalCode, String locality, String country,
                  List<CreditCard> lstCreditCard) {
        super(id, email, password, nif, name);
        this.mintCredits = credits;
        this.moAddress = new Address(latitude, longitude, altitude, streetName, doorNumber, postalCode, locality, country);
        this.mLstCreditCard = new ArrayList<>(lstCreditCard);
    }

    /**
     * Client constructor, which sets all the atributes to the ones
     * given by parameter
     *
     * @param id            Client ID
     * @param name          Client name
     * @param nif           Client NIF
     * @param email         Client email
     * @param password      Client password
     * @param credits       Client credits
     * @param address       Client Address
     * @param lstCreditCard Client's List of Credit Cards
     */
    public Client(Integer id, String name, Integer nif, String email, String password, Integer credits, Address address, List<CreditCard> lstCreditCard) {
        super(id, email, password, nif, name);
        this.mintCredits = credits;
        this.moAddress = address;
        this.mLstCreditCard = new ArrayList<>(lstCreditCard);
    }

    /**
     * Returns the Client Address
     *
     * @return Client Address
     */
    public Address getAddress() {
        return moAddress;
    }

    /**
     * Returns the Client credits
     *
     * @return Client credits
     */
    public Integer getCredits() {
        return mintCredits;
    }

    /**
     * Returns the Client's List of Credit Cards
     *
     * @return Client's List of Credit Cards
     */
    public List<CreditCard> getLstCreditCard() {
        return new ArrayList<>(mLstCreditCard);
    }

    /**
     * Sets the Client credits to the ones given by parameter
     *
     * @param oCredits new Client credits
     */
    public void setCredits(Integer oCredits) {
        this.mintCredits = oCredits;
    }

    /**
     * Sets the Client Address to the one given by parameter
     *
     * @param oAddress new Client Address
     */
    public void setAddress(Address oAddress) {
        this.moAddress = oAddress;
    }

    /**
     * Equals method, which compares two Clients
     *
     * @param o the other Object which we want to compare to
     * @return True if both Clients are equal, false if otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(moAddress, client.moAddress);
    }

    /**
     * Sets the List of Credit Cards to the one given by parameter
     *
     * @param oCreditCard Client's List of Credit Cards
     */
    public void setLstCreditCard(List<CreditCard> oCreditCard) {
        this.mLstCreditCard = new ArrayList<>(oCreditCard);
    }

    /**
     * Adds additional credits to the Client credits
     *
     * @param oAdditionalCredits Client additional credits
     */
    public void addCredits(Integer oAdditionalCredits) {
        this.mintCredits += oAdditionalCredits;
    }

    /**
     * ToString method, which formats a string to
     * print a formatted message
     *
     * @return formatted String
     */
    @Override
    public String toString() {

        return super.toString() + "Client{" +
                "m_credits=" + mintCredits +
                ", m_address=" + moAddress +
                ", m_creditCard=" + mLstCreditCard +
                '}';
    }
}
