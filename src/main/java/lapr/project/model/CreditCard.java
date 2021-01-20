package lapr.project.model;

import java.util.Date;
import java.util.Objects;

public class CreditCard implements Comparable{

    private Long mlCreditCardNr;
    private Date mdtValidityDate;
    private Integer mintCCV;

    public CreditCard() {
        this.mlCreditCardNr = -1L;
        this.mdtValidityDate = new Date();
        this.mintCCV = -1;
    }

    public CreditCard(long creditCardNr, Date validityDate, Integer CCV) {
        this.mlCreditCardNr = creditCardNr;
        this.mdtValidityDate = (Date) validityDate.clone();
        this.mintCCV = CCV;
    }

    public Long getCreditCardNr() {
        return mlCreditCardNr;
    }

    public void setCreditCardNr(Long m_creditCardNr) {
        this.mlCreditCardNr = m_creditCardNr;
    }

    public boolean hasNumber(Long intNum) {return this.mlCreditCardNr.equals(intNum);}

    public Date getValidityDate() {
        return (Date) mdtValidityDate.clone();
    }

    public void setValidityDate(Date m_validityDate) {
        this.mdtValidityDate = (Date) m_validityDate.clone();
    }

    public Integer getCCV() {
        return mintCCV;
    }

    public void setCCV(Integer m_CCV) {
        this.mintCCV = m_CCV;
    }

    @Override
    public int compareTo(Object o) {
        CreditCard c = (CreditCard) o;
        if(this.mlCreditCardNr < c.getCreditCardNr()){
            return -1;
        } else if(this.mlCreditCardNr == c.getCreditCardNr()){
            return 0;
        }
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(mlCreditCardNr, that.mlCreditCardNr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mlCreditCardNr);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "m_creditCardNr=" + mlCreditCardNr +
                ", m_validityDate=" + mdtValidityDate +
                ", m_CCV=" + mintCCV +
                '}';
    }
}
