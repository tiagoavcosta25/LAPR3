package lapr.project.model;

import lapr.project.utils.EncryptPassword;

import java.security.NoSuchAlgorithmException;

/**
 * User.
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
public abstract class User {

    /**
     * The User's unique id
     */
    private Integer mintId;
    /**
     * The User's email
     */
    private String mstrEmail;
    /**
     * The User's password
     */
    private String mstrPassword;

    /**
     * The User's NIF
     */
    private Integer mintNif;

    /**
     * The User's name
     */
    private String mstrName;

    /**
     * An empty constructor of User that initiates all the variables by omission
     */
    public User() {
        this.mstrEmail = "No Email Registered";
        PassGenerator oPG = new PassGenerator();
        this.mstrPassword = oPG.generatePassword();
        this.mintNif = 0;
        this.mstrName = "No Name";
    }

    /**
     * A constructor of User that initiates the email and password as the ones
     * given by parameter
     *
     * @param strEmail    the User's email
     * @param strPassword the User's password
     */
    public User(String strEmail, String strPassword, Integer intNif, String strName) throws NoSuchAlgorithmException {
        this.mstrEmail = strEmail;
        this.mstrPassword = EncryptPassword.encryptPasswordMD5(strPassword);
        this.mintNif = intNif;
        this.mstrName = strName;
    }

    /**
     * A constructor of User that initiates the id, email and password as the ones
     * given by parameter
     *
     * @param id          the User's id
     * @param strEmail    the User's email
     * @param strPassword the User's password
     */
    public User(Integer id, String strEmail, String strPassword, Integer intNif, String strName) {
        this.mintId = id;
        this.mstrEmail = strEmail;
        this.mstrPassword = strPassword;
        this.mintNif = intNif;
        this.mstrName = strName;
    }

    /**
     * Returns the User's email
     *
     * @return User's email
     */
    public String getEmail() {
        return this.mstrEmail;
    }

    /**
     * Modifies the User's email value
     *
     * @param strEmail the User's email
     */
    public void setEmail(String strEmail) {
        this.mstrEmail = strEmail;
    }

    /**
     * Returns the User's password
     *
     * @return User's password
     */
    public String getPw() {
        return this.mstrPassword;
    }

    /**
     * Modifies the User's password
     *
     * @param strPw the User's password
     */
    public void setPw(String strPw) {
        this.mstrPassword = strPw;
    }

    /**
     * Returns the User ID
     *
     * @return User ID
     */
    public Integer getId() {
        return mintId;
    }

    /**
     * Sets the User ID to the one given by parameter
     *
     * @param intId new User ID
     */
    public void setId(Integer intId) {
        this.mintId = intId;
    }

    /**
     * Returns the User NIF
     *
     * @return User NIF
     */
    public Integer getNif() {
        return mintNif;
    }

    /**
     * Sets the User NIF to the one given by parameter
     *
     * @param intNif new User NIF
     */
    public void setNif(Integer intNif) {
        this.mintNif = intNif;
    }

    /**
     * Returns the User name
     *
     * @return User name
     */
    public String getName() {
        return mstrName;
    }

    /**
     * Sets the User name to the one given by parameter
     *
     * @param strName new User name
     */
    public void setName(String strName) {
        this.mstrName = strName;
    }

    /**
     * This method is responsible for comparing two Users, basing on it's email and password.
     * If both User's email and password are the same, they are the same Users and it will return
     * true
     *
     * @param o the other Object which we want to compare to
     * @return true if both objects are the same User, false if otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;
        return this.mstrEmail.equals(user.mstrEmail);
    }

    /**
     * ToString method, which formats a string to
     * print a formatted message
     *
     * @return formatted String
     */
    @Override
    public String toString() {
        return "User{" +
                "m_intId=" + mintId +
                ", m_strEmail='" + mstrEmail + '\'' +
                ", m_strPassword='" + mstrPassword + '\'' +
                ", m_intNif=" + mintNif +
                ", m_strName='" + mstrName + '\'' +
                '}';
    }
}
