package lapr.project.model;

import lapr.project.data.ClientDB;

public class Client extends User {

    private String m_name;
    private Address m_address;
    private Integer m_creditCardNr;
    private String m_validityDate;
    private Integer m_CCV;

    public Client() {
        super();
        this.m_name = "No name";
        this.m_address = new Address();
        this.m_creditCardNr = 0;
        this.m_validityDate = "No Validity Date";
        this.m_CCV = 0;
    }

    public Client(String name, String email, String password, float latitude, float longitude,String streetName,
                  Integer doorNumber, String postalCode, String locality, String country, Integer creditCardNr,
                  String validityDate, Integer CCV) {
        super(email,password);
        this.m_name = name;
        this.m_address = new Address(latitude,longitude,streetName,doorNumber,postalCode,locality,country);
        this.m_creditCardNr = creditCardNr;
        this.m_validityDate = validityDate;
        this.m_CCV = CCV;
    }

    public String getM_name() {
        return m_name;
    }

    public Address getM_address() {
        return m_address;
    }

    public Integer getM_creditCardNr() {
        return m_creditCardNr;
    }

    public String getM_validityDate() {
        return m_validityDate;
    }

    public Integer getM_CCV() {
        return m_CCV;
    }

    public static Client getClient(String id) {
        return new ClientDB().getClient(id);
    }

    public String getId() {
        return this.getStrEmail();
    }

    public void save() {

        try {
            getClient(this.getId());
        } catch (IllegalArgumentException ex) {
            //Of the record does not exist, save it
            new ClientDB().addClientToDB(this);
        }

        //TODO: implement the update method
    }
}
