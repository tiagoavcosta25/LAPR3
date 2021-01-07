package lapr.project.model;

import java.util.Date;

public class Client extends User {

    private String m_name;
    private Integer m_credits;
    private Address m_address;
    private CreditCard m_creditCard;

    public Client() {
        super();
        this.m_name = "No name";
        this.m_credits = 0;
        this.m_address = new Address();
        this.m_creditCard = new CreditCard();
    }

    public Client(String name, Integer nif, String email, String password, float latitude, float longitude, String streetName,
                  String doorNumber, String postalCode, String locality, String country, Integer creditCardNr,
                  Date validityDate, Integer CCV) {
        super(email, password,nif);
        this.m_name = name;
        this.m_credits = 0;
        this.m_address = new Address(latitude, longitude, streetName, doorNumber, postalCode, locality, country);
        this.m_creditCard = new CreditCard(creditCardNr,validityDate,CCV);
    }

    public Client(Integer id, String name, Integer nif, String email, String password, Integer credits, float latitude, float longitude, String streetName,
                  String doorNumber, String postalCode, String locality, String country, Integer creditCardNr,
                  Date validityDate, Integer CCV) {
        super(id, email, password,nif);
        this.m_name = name;
        this.m_credits = credits;
        this.m_address = new Address(latitude, longitude, streetName, doorNumber, postalCode, locality, country);
        this.m_creditCard = new CreditCard(creditCardNr,validityDate,CCV);
    }

    public String getM_name() {
        return m_name;
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
}
