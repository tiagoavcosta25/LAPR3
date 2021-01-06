package lapr.project.model;

public class Address {
    private String address;
    private String gpsLocation;

    public Address() {
        this.address = "No Address";
        this.gpsLocation = "No GPS Location";
    }

    public Address(String address, String gpsLocation) {
        this.address = address;
        this.gpsLocation = gpsLocation;
    }


}
