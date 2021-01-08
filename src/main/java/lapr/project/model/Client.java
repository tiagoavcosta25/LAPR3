package lapr.project.model;

import java.util.Date;

public class Client extends User {

    private Integer m_credits;
    private Address m_address;
    private CreditCard m_creditCard;

    public Client() {
        super();
        this.m_credits = 0;
        this.m_address = new Address();
        this.m_creditCard = new CreditCard();
    }

    public Client(String name, Integer nif, String email, String password, Double latitude, Double longitude, String streetName,
                  String doorNumber, String postalCode, String locality, String country, long creditCardNr,
                  Date validityDate, Integer CCV) {
        super(email, password,nif,name);
        this.m_credits = 0;
        this.m_address = new Address(latitude, longitude, streetName, doorNumber, postalCode, locality, country);
        this.m_creditCard = new CreditCard(creditCardNr,validityDate,CCV);
    }

    public Client(Integer id, String name, Integer nif, String email, String password, Integer credits, Double latitude, Double longitude, String streetName,
                  String doorNumber, String postalCode, String locality, String country, long creditCardNr,
                  Date validityDate, Integer CCV) {
        super(id, email, password,nif,name);
        this.m_credits = credits;
        this.m_address = new Address(latitude, longitude, streetName, doorNumber, postalCode, locality, country);
        this.m_creditCard = new CreditCard(creditCardNr,validityDate,CCV);
    }


    public Address getM_address() {
        return m_address;
    }

    public Integer getM_credits() {
        return m_credits;
    }

    public CreditCard getM_creditCard() {
        return m_creditCard;
    }

    @Override
    public String toString() {
        return "Client{" +
                "m_credits=" + m_credits +
                ", m_address=" + m_address +
                ", m_creditCard=" + m_creditCard +
                '}';
    }
}
