package lapr.project.model;

import lapr.project.utils.EncryptPassword;

import java.security.NoSuchAlgorithmException;

/**
 * The class that represents a User, which has it's own email, password and role
 * (Administrative, Manager of Organization or Collaborator of Organization.
 * <p>
 * Group: Team Lisa [G-037]
 * ______________________________________________________
 *
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 */
public abstract class User {

    /**
     * The User's unique id
     */
    private Integer m_intId;
    /**
     * The User's email
     */
    private String m_strEmail;
    /**
     * The User's password
     */
    private String m_strPassword;

    /**
     * The User's NIF
     */
    private Integer m_intNif;

    /**
     * The User's name
     */
    private String m_strName;

    /**
     * An empty constructor of User that initiates all the variables by omission
     */
    public User() {
        this.m_strEmail = "No Email Registered";
        this.m_strPassword = "No Password Registered";
        this.m_intNif = 0;
        this.m_strName = "No Name";
    }

    /**
     * A constructor of User that initiates the email and password as the ones
     * given by parameter
     *
     * @param strEmail    the User's email
     * @param strPassword the User's password
     */
    public User(String strEmail, String strPassword, Integer intNif, String strName) throws NoSuchAlgorithmException {
        this.m_strEmail = strEmail;
        this.m_strPassword = EncryptPassword.encryptPasswordMD5(strPassword);
        this.m_intNif = intNif;
        this.m_strName = strName;
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
        this.m_intId = id;
        this.m_strEmail = strEmail;
        this.m_strPassword = strPassword;
        this.m_intNif = intNif;
        this.m_strName = strName;
    }

    /**
     * Returns the User's email
     *
     * @return User's email
     */
    public String getEmail() {
        return this.m_strEmail;
    }

    /**
     * Modifies the User's email value
     *
     * @param strEmail the User's email
     */
    public void setEmail(String strEmail) {
        this.m_strEmail = strEmail;
    }

    /**
     * Returns the User's password
     *
     * @return User's password
     */
    public String getPw() {
        return this.m_strPassword;
    }

    /**
     * Modifies the User's password
     *
     * @param strPw the User's password
     */
    public void setPw(String strPw) {
        this.m_strPassword = strPw;
    }

    public Integer getId() {
        return m_intId;
    }

    public void setId(Integer m_id) {
        this.m_intId = m_id;
    }

    public Integer getNif() {
        return m_intNif;
    }

    public void setNif(Integer m_nif) {
        this.m_intNif = m_nif;
    }

    public String getName() {
        return m_strName;
    }

    public void setName(String m_name) {
        this.m_strName = m_name;
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
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;
        return this.m_strEmail.equals(user.m_strEmail);
    }
}
