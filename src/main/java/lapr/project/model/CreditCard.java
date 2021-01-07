package lapr.project.model;

public class CreditCard {

    private Integer m_creditCardNr;
    private String m_validityDate;
    private Integer m_CCV;

    public CreditCard() {
        this.m_creditCardNr = 0;
        this.m_validityDate = "No Validity Date";
        this.m_CCV = 0;
    }

    public CreditCard(Integer creditCardNr, String validityDate, Integer CCV) {
        this.m_creditCardNr = creditCardNr;
        this.m_validityDate = validityDate;
        this.m_CCV = CCV;
    }

    public Integer getM_creditCardNr() {
        return m_creditCardNr;
    }

    public void setM_creditCardNr(Integer m_creditCardNr) {
        this.m_creditCardNr = m_creditCardNr;
    }

    public String getM_validityDate() {
        return m_validityDate;
    }

    public void setM_validityDate(String m_validityDate) {
        this.m_validityDate = m_validityDate;
    }

    public Integer getM_CCV() {
        return m_CCV;
    }

    public void setM_CCV(Integer m_CCV) {
        this.m_CCV = m_CCV;
    }
}
