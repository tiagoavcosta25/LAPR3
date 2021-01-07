package lapr.project.model;

import java.util.Date;

public class CreditCard {

    private Double m_creditCardNr;
    private Date m_validityDate;
    private Integer m_CCV;

    public CreditCard() {
        this.m_creditCardNr = 0.0;
        this.m_validityDate = new Date();
        this.m_CCV = 0;
    }

    public CreditCard(Double creditCardNr, Date validityDate, Integer CCV) {
        this.m_creditCardNr = creditCardNr;
        this.m_validityDate = validityDate;
        this.m_CCV = CCV;
    }

    public Double getM_creditCardNr() {
        return m_creditCardNr;
    }

    public void setM_creditCardNr(Double m_creditCardNr) {
        this.m_creditCardNr = m_creditCardNr;
    }

    public Date getM_validityDate() {
        return m_validityDate;
    }

    public void setM_validityDate(Date m_validityDate) {
        this.m_validityDate = m_validityDate;
    }

    public Integer getM_CCV() {
        return m_CCV;
    }

    public void setM_CCV(Integer m_CCV) {
        this.m_CCV = m_CCV;
    }
}
