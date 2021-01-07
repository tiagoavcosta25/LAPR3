package lapr.project.model;


public class Courier extends User {

    private int m_id;
    private String m_iban;

    public Courier() {
        super();
        this.setM_id(-1);
        this.setM_iban("No Iban");
    }

    public Courier(int intId, String strName, String strEmail, String strPassword,Integer strNif,String strIban) {
        super(strEmail,strPassword,strNif,strName);
        this.setM_id(intId);
        this.setM_name(strName);
        this.setM_nif(strNif);
        this.setM_iban(strIban);
    }

    public Courier(String strName, String strEmail, String strPassword,Integer strNif,String strIban) {
        super(strEmail,strPassword,strNif,strName);
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

    public String getM_iban() {
        return m_iban;
    }

    public void setM_iban(String m_iban) {
        this.m_iban = m_iban;
    }
}

