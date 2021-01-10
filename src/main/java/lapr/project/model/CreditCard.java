package lapr.project.model;

import java.util.Date;
import java.util.Objects;

public class CreditCard {

    private Long m_lCreditCardNr;
    private Date m_dtValidityDate;
    private Integer m_intCCV;

    public CreditCard() {
        this.m_lCreditCardNr = 0L;
        this.m_dtValidityDate = new Date();
        this.m_intCCV = 0;
    }

    public CreditCard(long creditCardNr, Date validityDate, Integer CCV) {
        this.m_lCreditCardNr = creditCardNr;
        this.m_dtValidityDate = validityDate;
        this.m_intCCV = CCV;
    }

    public Long getCreditCardNr() {
        return m_lCreditCardNr;
    }

    public void setCreditCardNr(Long m_creditCardNr) {
        this.m_lCreditCardNr = m_creditCardNr;
    }

    public Date getValidityDate() {
        return m_dtValidityDate;
    }

    public void setValidityDate(Date m_validityDate) {
        this.m_dtValidityDate = m_validityDate;
    }

    public Integer getCCV() {
        return m_intCCV;
    }

    public void setCCV(Integer m_CCV) {
        this.m_intCCV = m_CCV;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(m_lCreditCardNr, that.m_lCreditCardNr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_lCreditCardNr);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "m_creditCardNr=" + m_lCreditCardNr +
                ", m_validityDate=" + m_dtValidityDate +
                ", m_CCV=" + m_intCCV +
                '}';
    }
}
