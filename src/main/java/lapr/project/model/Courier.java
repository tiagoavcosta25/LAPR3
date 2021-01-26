package lapr.project.model;


import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Courier extends User {

    private String mIban;
    private Pharmacy mPharmacy;

    public Courier() {
        super();
        this.setIban("No Iban");
        this.setPharmacy(new Pharmacy());
    }

    public Courier(int intId, String strName, String strEmail, String strPassword,Integer strNif,String strIban, Pharmacy oPharmacy) throws NoSuchAlgorithmException {
        super(intId, strEmail,strPassword,strNif,strName);
        this.setIban(strIban);
        this.setPharmacy(oPharmacy);
    }

    public Courier(String strName, String strEmail, String strPassword,Integer strNif,String strIban, Pharmacy oPharmacy) throws NoSuchAlgorithmException {
        super(strEmail,strPassword,strNif,strName);
        this.setPharmacy(oPharmacy);
        this.setIban(strIban);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Courier courier = (Courier) o;
        return Objects.equals(mIban, courier.mIban);
    }

    public String getIban() {
        return mIban;
    }

    public void setIban(String strIban) {
        this.mIban = strIban;
    }

    public Pharmacy getPharmacy() {
        return mPharmacy;
    }

    public void setPharmacy(Pharmacy oPharmacy) {
        this.mPharmacy = oPharmacy;
    }
}

