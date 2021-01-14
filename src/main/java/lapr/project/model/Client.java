package lapr.project.model;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Client extends User {

    private Integer m_intCredits;
    private Address m_oAddress;
    private CreditCard m_oCreditCard;

    public Client() {
        super();
        this.m_intCredits = 0;
        this.m_oAddress = new Address();
        this.m_oCreditCard = new CreditCard();
    }

    public Client(String name, Integer nif, String email, String password, Double latitude, Double longitude, String streetName,
                  String doorNumber, String postalCode, String locality, String country, long creditCardNr,
                  Date validityDate, Integer CCV) throws NoSuchAlgorithmException {
        super(email, password, nif, name);
        this.m_intCredits = 0;
        this.m_oAddress = new Address(latitude, longitude, streetName, doorNumber, postalCode, locality, country);
        this.m_oCreditCard = new CreditCard(creditCardNr, validityDate, CCV);
    }

    public Client(Integer id, String name, Integer nif, String email, String password, Integer credits, Double latitude, Double longitude, String streetName,
                  String doorNumber, String postalCode, String locality, String country, long creditCardNr,
                  Date validityDate, Integer CCV) {
        super(id, email, password, nif, name);
        this.m_intCredits = credits;
        this.m_oAddress = new Address(latitude, longitude, streetName, doorNumber, postalCode, locality, country);
        this.m_oCreditCard = new CreditCard(creditCardNr, validityDate, CCV);
    }

    public Client(Integer id, String name, Integer nif, String email, String password, Integer credits, Address address, CreditCard creditCard) {
        super(id, email, password, nif, name);
        this.m_intCredits = credits;
        this.m_oAddress = address;
        this.m_oCreditCard = creditCard;
    }


    public Address getAddress() {
        return m_oAddress;
    }

    public Integer getCredits() {
        return m_intCredits;
    }

    public CreditCard getCreditCard() {
        return m_oCreditCard;
    }

    public void setCredits(Integer oCredits) {
        this.m_intCredits = oCredits;
    }

    public void setAddress(Address oAddress) {
        this.m_oAddress = oAddress;
    }

    public void setCreditCard(CreditCard oCreditCard) {
        this.m_oCreditCard = oCreditCard;
    }

    public void addCredits(Integer oAdditionalCredits) {
        this.m_intCredits += oAdditionalCredits;
    }

    @Override
    public String toString() {

        return  super.toString() + "Client{" +
                "m_credits=" + m_intCredits +
                ", m_address=" + m_oAddress +
                ", m_creditCard=" + m_oCreditCard +
                '}';
    }
}
