package lapr.project.model;

public class Client extends User {

    private String name;
    private String address;
    private String gpsLocation;
    private Integer creditCardNr;
    private Integer validityDate;
    private Integer CCV;

    public Client() {
        super();
        this.name = "No name";
        this.address = "No Address";
        this.gpsLocation = "No GPS Location";
        this.creditCardNr = 0;
        this.validityDate = 0;
        this.CCV = 0;
    }

    public Client(String name, String email, String password,String address, String gpsLocation,
                  Integer creditCardNr, Integer validityDate, Integer CCV) {
        super(email,password);
        this.name = name;
        this.address = address;
        this.gpsLocation = gpsLocation;
        this.creditCardNr = creditCardNr;
        this.validityDate = validityDate;
        this.CCV = CCV;
    }

}
