package lapr.project.model;

public class Client extends User {

    private String m_name;
    private Integer nif;
    private Address m_address;
    private Integer m_creditCardNr;
    private String m_validityDate;
    private Integer m_CCV;

    public Client() {
        super();
        this.m_name = "No name";
        this.nif = 0;
        this.m_address = new Address();
        this.m_creditCardNr = 0;
        this.m_validityDate = "No Validity Date";
        this.m_CCV = 0;
    }

    public Client(String name, Integer nif, String email, String password, float latitude, float longitude,String streetName,
                  String doorNumber, String postalCode, String locality, String country, Integer creditCardNr,
                  String validityDate, Integer CCV) {
        super(email,password);
        this.m_name = name;
        this.nif = nif;
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

}
