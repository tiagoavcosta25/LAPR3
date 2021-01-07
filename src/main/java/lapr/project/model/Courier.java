package lapr.project.model;


public class Courier extends User {

    private int m_id;
    private String m_name;
    private String m_nif;
    private String m_iban;

    public Courier() {
        super();
        this.setM_id(-1);
        this.setM_name("No name");
        this.setM_nif("No nif");
        this.setM_iban("No Iban");
    }

    public Courier(int intId, String strName, String strNif, String strIban) {
        this.setM_id(intId);
        this.setM_name(strName);
        this.setM_nif(strNif);
        this.setM_iban(strIban);
    }

    public Courier(String strName, String strEmail, String strPassword,String strNif,String strIban) {
        super(strEmail,strPassword);
        this.setM_name(strName);
        this.setM_nif(strNif);
        this.setM_iban(strIban);
    }


    public Integer getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_nif() {
        return m_nif;
    }

    public void setM_nif(String m_nif) {
        this.m_nif = m_nif;
    }

    public String getM_iban() {
        return m_iban;
    }

    public void setM_iban(String m_iban) {
        this.m_iban = m_iban;
    }
}

