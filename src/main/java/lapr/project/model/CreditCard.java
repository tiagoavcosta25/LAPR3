package lapr.project.model;

import java.util.Date;
import java.util.Objects;

public class CreditCard {

    private Long m_creditCardNr;
    private Date m_validityDate;
    private Integer m_CCV;

    public CreditCard() {
        this.m_creditCardNr = 0L;
        this.m_validityDate = new Date();
        this.m_CCV = 0;
    }

    public CreditCard(long creditCardNr, Date validityDate, Integer CCV) {
        this.m_creditCardNr = creditCardNr;
        this.m_validityDate = validityDate;
        this.m_CCV = CCV;
    }

    public Long getM_creditCardNr() {
        return m_creditCardNr;
    }

    public void setM_creditCardNr(Long m_creditCardNr) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(m_creditCardNr, that.m_creditCardNr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_creditCardNr);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "m_creditCardNr=" + m_creditCardNr +
                ", m_validityDate=" + m_validityDate +
                ", m_CCV=" + m_CCV +
                '}';
    }
}
