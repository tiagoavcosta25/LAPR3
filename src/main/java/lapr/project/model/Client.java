package lapr.project.model;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(moAddress, client.moAddress);
    }

    public void setLstCreditCard(List<CreditCard> oCreditCard) {
        this.mLstCreditCard = oCreditCard;
    }

    public void addCredits(Integer oAdditionalCredits) {
        this.mintCredits += oAdditionalCredits;
    }

    @Override
    public String toString() {

        return super.toString() + "Client{" +
                "m_credits=" + mintCredits +
                ", m_address=" + moAddress +
                ", m_creditCard=" + mLstCreditCard +
                '}';
    }
}
