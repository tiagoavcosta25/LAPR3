package lapr.project.model;

import java.util.Date;
import java.util.Objects;

public class CreditCard implements Comparable{

    private Long m_lCreditCardNr;
    private Date m_dtValidityDate;
    private Integer m_intCCV;

    public CreditCard() {
        this.m_lCreditCardNr = -1L;
        this.m_dtValidityDate = new Date();
        this.m_intCCV = -1;
    }

    public CreditCard(long creditCardNr, Date validityDate, Integer CCV) {
        this.m_lCreditCardNr = creditCardNr;
        this.m_dtValidityDate = (Date) validityDate.clone();
        this.m_intCCV = CCV;
    }

    public Long getCreditCardNr() {
        return m_lCreditCardNr;
    }

    public void setCreditCardNr(Long m_creditCardNr) {
        this.m_lCreditCardNr = m_creditCardNr;
    }

    public boolean hasNumber(Long intNum) {return this.m_lCreditCardNr == intNum;}

    public Date getValidityDate() {
        return (Date) m_dtValidityDate.clone();
    }

    public void setValidityDate(Date m_validityDate) {
        this.m_dtValidityDate = (Date) m_validityDate.clone();
    }

    public Integer getCCV() {
        return m_intCCV;
    }

    public void setCCV(Integer m_CCV) {
        this.m_intCCV = m_CCV;
    }

    @Override
    public int compareTo(Object o) {
        CreditCard c = (CreditCard) o;
        if(this.m_lCreditCardNr < c.getCreditCardNr()){
            return -1;
        } else if(this.m_lCreditCardNr == c.getCreditCardNr()){
            return 0;
        }
        return 1;
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
