package lapr.project.model;


import java.security.NoSuchAlgorithmException;

public class Courier extends User {

    private int m_id;
    private String m_iban;
    private Pharmacy m_Pharmacy;

    public Courier() {
        super();
        this.setM_id(-1);
        this.setM_iban("No Iban");
        this.setM_Pharmacy(new Pharmacy());
    }

    public Courier(int intId, String strName, String strEmail, String strPassword,Integer strNif,String strIban, Pharmacy oPharmacy) throws NoSuchAlgorithmException {
        super(strEmail,strPassword,strNif,strName);
        this.setM_id(intId);
        this.setName(strName);
        this.setNif(strNif);
        this.setM_iban(strIban);
        this.setM_Pharmacy(oPharmacy);
    }

    public Courier(String strName, String strEmail, String strPassword,Integer strNif,String strIban, Pharmacy oPharmacy) throws NoSuchAlgorithmException {
        super(strEmail,strPassword,strNif,strName);
        this.setM_Pharmacy(oPharmacy);
        this.setName(strName);
        this.setNif(strNif);
        this.setM_iban(strIban);
    }


    public Integer getId() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getM_iban() {
        return m_iban;
    }

    public void setM_iban(String m_iban) {
        this.m_iban = m_iban;
    }

    public Pharmacy getM_Pharmacy() {
        return m_Pharmacy;
    }

    public void setM_Pharmacy(Pharmacy m_Pharmacy) {
        this.m_Pharmacy = m_Pharmacy;
    }
}

