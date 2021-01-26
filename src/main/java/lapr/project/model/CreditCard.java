package lapr.project.model;

import java.util.Date;
import java.util.Objects;

/**
 * CreditCard.
 * <p>
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 *
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class CreditCard implements Comparable{

    /**
     * Credit Card Number
     */
    private Long mlCreditCardNr;

    /**
     * Credit Card Validity Date
     */
    private Date mdtValidityDate;

    /**
     * Credit Card CCV
     */
    private Integer mintCCV;

    /**
     * Empty constructor of CreditCard, which sets all the atributes
     * to default values
     */
    public CreditCard() {
        this.mlCreditCardNr = -1L;
        this.mdtValidityDate = new Date();
        this.mintCCV = -1;
    }

    /**
     * Constructor of CreditCard, which sets all the atributes to the ones
     * given by parameter
     *
     * @param creditCardNr  Credit Card Number
     * @param validityDate  Credit Card Validity Date
     * @param ccv           Credit Card CCV
     */
    public CreditCard(long creditCardNr, Date validityDate, Integer ccv) {
        this.mlCreditCardNr = creditCardNr;
        this.mdtValidityDate = (Date) validityDate.clone();
        this.mintCCV = ccv;
    }

    /**
     * Returns the Credit Card Number
     *
     * @return  Credit Card Number
     */
    public Long getCreditCardNr() {
        return mlCreditCardNr;
    }

    /**
     * Sets the Credit Card Number to the one given by parameter
     *
     * @param lCreditCardNr new Credit Card Number
     */
    public void setCreditCardNr(Long lCreditCardNr) {
        this.mlCreditCardNr = lCreditCardNr;
    }

    /**
     * Checks if a Credit Card has a certain number, given by parameter
     *
     * @param intNum    Credit Card Number
     * @return  True if the Credit Card has the Number given by parameter, false if otherwise
     */
    public boolean hasNumber(Long intNum) {return this.mlCreditCardNr.equals(intNum);}

    /**
     * Returns the Credit Card Validity Date
     *
     * @return  Credit Card Validity Date
     */
    public Date getValidityDate() {
        return (Date) mdtValidityDate.clone();
    }

    /**
     * Sets the Validity Date to the one given by parameter
     *
     * @param oValidityDate new Validity Date
     */
    public void setValidityDate(Date oValidityDate) {
        this.mdtValidityDate = (Date) oValidityDate.clone();
    }

    /**
     * Returns the Credit Card CCV
     *
     * @return  Credit Card CCV
     */
    public Integer getCCV() {
        return mintCCV;
    }

    /**
     * Sets the CCV to the one ggiven by parameter
     *
     * @param intCCV   new Credit Card CCV
     */
    public void setCCV(Integer intCCV) {
        this.mintCCV = intCCV;
    }

    /**
     * Compares two Credit Card objects
     *
     * @param o Other Object
     * @return  0 if the objects are the same, 1 if the first is greater
     *          than the second or -1 if otherwise
     */
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

    /**
     * Equals method, which compares two Credit Cards
     *
     * @param o Other Object
     * @return  True if both Credit Cards are equal, false if otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(mlCreditCardNr, that.mlCreditCardNr);
    }

    /**
     * HashCode
     *
     * @return  Integer hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(mlCreditCardNr);
    }

    /**
     * ToString method, which formats a string to
     * print a formatted message
     *
     * @return formatted String
     */
    @Override
    public String toString() {
        return "CreditCard{" +
                "m_creditCardNr=" + mlCreditCardNr +
                ", m_validityDate=" + mdtValidityDate +
                ", m_CCV=" + mintCCV +
                '}';
    }
}
