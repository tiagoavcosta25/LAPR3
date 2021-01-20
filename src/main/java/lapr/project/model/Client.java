package lapr.project.model;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Client extends User {

    private Integer m_intCredits;
    private Address m_oAddress;
    private List<CreditCard> m_lstCreditCard;

    public Client() {
        super();
        this.m_intCredits = 0;
        this.m_oAddress = new Address();
        this.m_lstCreditCard = new ArrayList<>();
    }

    public Client(String name, Integer nif, String email, String password, Double latitude, Double longitude, Double altitude, String streetName,
                  String doorNumber, String postalCode, String locality, String country, List<CreditCard> lstCreditCardNr) throws NoSuchAlgorithmException {
        super(email, password, nif, name);
        this.m_intCredits = 0;
        this.m_oAddress = new Address(latitude, longitude, altitude, streetName, doorNumber, postalCode, locality, country);
        this.m_lstCreditCard = lstCreditCardNr;
    }

    public Client(Integer id, String name, Integer nif, String email, String password, Integer credits, Double latitude, Double longitude,
                  Double altitude, String streetName, String doorNumber, String postalCode, String locality, String country,
                  List<CreditCard> lstCreditCardNr) {
        super(id, email, password, nif, name);
        this.m_intCredits = credits;
        this.m_oAddress = new Address(latitude, longitude, altitude, streetName, doorNumber, postalCode, locality, country);
        this.m_lstCreditCard = lstCreditCardNr;
    }


    public Client(Integer id, String name, Integer nif, String email, String password, Integer credits, Address address, List<CreditCard> lstCreditCardNr) {
        super(id, email, password, nif, name);
        this.m_intCredits = credits;
        this.m_oAddress = address;
        this.m_lstCreditCard = lstCreditCardNr;
    }


    public Address getAddress() {
        return m_oAddress;
    }

    public Integer getCredits() {
        return m_intCredits;
    }

    public List<CreditCard> getLstCreditCard() {
        return m_lstCreditCard;
    }

    public void setCredits(Integer oCredits) {
        this.m_intCredits = oCredits;
    }

    public void setAddress(Address oAddress) {
        this.m_oAddress = oAddress;
    }

    public void setLstCreditCard(List<CreditCard> oCreditCard) {
        this.m_lstCreditCard = oCreditCard;
    }

    public void addCredits(Integer oAdditionalCredits) {
        this.m_intCredits += oAdditionalCredits;
    }

    @Override
    public String toString() {

        return  super.toString() + "Client{" +
                "m_credits=" + m_intCredits +
                ", m_address=" + m_oAddress +
                ", m_creditCard=" + m_lstCreditCard +
                '}';
    }
}
