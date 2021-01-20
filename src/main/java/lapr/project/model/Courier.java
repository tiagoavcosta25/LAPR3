package lapr.project.model;


import java.security.NoSuchAlgorithmException;

public class Courier extends User {

    private String mIban;
    private Pharmacy mPharmacy;

    public Courier() {
        super();
        this.setM_iban("No Iban");
        this.setM_Pharmacy(new Pharmacy());
    }

    public Courier(int intId, String strName, String strEmail, String strPassword,Integer strNif,String strIban, Pharmacy oPharmacy) throws NoSuchAlgorithmException {
        super(intId, strEmail,strPassword,strNif,strName);
        this.setM_iban(strIban);
        this.setM_Pharmacy(oPharmacy);
    }

    public Courier(String strName, String strEmail, String strPassword,Integer strNif,String strIban, Pharmacy oPharmacy) throws NoSuchAlgorithmException {
        super(strEmail,strPassword,strNif,strName);
        this.setM_Pharmacy(oPharmacy);
        this.setM_iban(strIban);
    }

    public String getM_iban() {
        return mIban;
    }

    public void setM_iban(String m_iban) {
        this.mIban = m_iban;
    }

    public Pharmacy getM_Pharmacy() {
        return mPharmacy;
    }

    public void setM_Pharmacy(Pharmacy m_Pharmacy) {
        this.mPharmacy = m_Pharmacy;
    }
}

