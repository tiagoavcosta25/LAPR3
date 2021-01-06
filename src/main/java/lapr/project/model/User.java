package lapr.project.model;

/**
 * The class that represents a User, which has it's own email, password and role
 * (Administrative, Manager of Organization or Collaborator of Organization.
 *
 * Group: Team Lisa [G-037]
 * ______________________________________________________
 *
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 *
 */
public abstract class User {

    /**
     * The User's email
     */
    private String m_strEmail;
    /**
     * The User's password
     */
    private String m_strPassword;


    /**
     * An empty constructor of User that initiates all the variables by omission
     *
     */
    public User() {
        this.m_strEmail = "No Email Registered";
        this.m_strPassword = "No Password Registered";
    }

    /**
     * A constructor of User that initiates the email and password as the ones
     * given by parameter
     *
     * @param strEmail      the User's email
     * @param strPassword   the User's password
     */
    public User(String strEmail, String strPassword) {
        this.m_strEmail = strEmail;
        this.m_strPassword = strPassword;
    }


    /**
     * Returns the User's email
     *
     * @return User's email
     */
    public String getStrEmail() {
        return this.m_strEmail;
    }

    /**
     * Modifies the User's email value
     *
     * @param strEmail the User's email
     */
    public void setStrEmail(String strEmail) {
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
