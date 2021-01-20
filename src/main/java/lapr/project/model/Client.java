package lapr.project.model;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Client extends User {

    private Integer mintCredits;
    private Address moAddress;
    private List<CreditCard> mLstCreditCard;

    public Client() {
        super();
        this.mintCredits = 0;
        this.moAddress = new Address();
        this.mLstCreditCard = new ArrayList<>();
    }

    public Client(String name, Integer nif, String email, String password, Double latitude, Double longitude, Double altitude, String streetName,
                  String doorNumber, String postalCode, String locality, String country, List<CreditCard> lstCreditCardNr) throws NoSuchAlgorithmException {
        super(email, password, nif, name);
        this.mintCredits = 0;
        this.moAddress = new Address(latitude, longitude, altitude, streetName, doorNumber, postalCode, locality, country);
        this.mLstCreditCard = lstCreditCardNr;
    }

    public Client(Integer id, String name, Integer nif, String email, String password, Integer credits, Double latitude, Double longitude,
                  Double altitude, String streetName, String doorNumber, String postalCode, String locality, String country,
                  List<CreditCard> lstCreditCardNr) {
        super(id, email, password, nif, name);
        this.mintCredits = credits;
        this.moAddress = new Address(latitude, longitude, altitude, streetName, doorNumber, postalCode, locality, country);
        this.mLstCreditCard = lstCreditCardNr;
    }


    public Client(Integer id, String name, Integer nif, String email, String password, Integer credits, Address address, List<CreditCard> lstCreditCardNr) {
        super(id, email, password, nif, name);
        this.mintCredits = credits;
        this.moAddress = address;
        this.mLstCreditCard = lstCreditCardNr;
    }


    public Address getAddress() {
        return moAddress;
    }

    public Integer getCredits() {
        return mintCredits;
    }

    public List<CreditCard> getLstCreditCard() {
        return mLstCreditCard;
    }

    public void setCredits(Integer oCredits) {
        this.mintCredits = oCredits;
    }

    public void setAddress(Address oAddress) {
        this.moAddress = oAddress;
    }

    public void setLstCreditCard(List<CreditCard> oCreditCard) {
        this.mLstCreditCard = oCreditCard;
    }

    public void addCredits(Integer oAdditionalCredits) {
        this.mintCredits += oAdditionalCredits;
    }

    @Override
    public String toString() {

        return  super.toString() + "Client{" +
                "m_credits=" + mintCredits +
                ", m_address=" + moAddress +
                ", m_creditCard=" + mLstCreditCard +
                '}';
    }
}
