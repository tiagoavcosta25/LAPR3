package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.model.User;

public class UserSession {

    /**
     * The Current User's email
     */
    private String m_currentUserEmail;

    /**
     * The Current User's role
     */
    private Role m_role;


    public enum Role {
        ADMIN("Administrator"),
        COURIER("Courier"),
        CLIENT("Client");

        private final String label;

        private Role(String label) {
            this.label = label;
        }

        public Role getRole() {
            return this;
        }

    }

    /**
     * Empty constructor of UserSession
     * which initializes a null session
     */
    public UserSession() {
        this.m_currentUserEmail = null;
        this.m_role = null;
        ApplicationPOT.getInstance().clearCurrentSession();
    }

    /**
     * A constructor of UserSession that receives an Email as parameter
     * and initializes the email variable
     *
     * @param email the User's email that is currently logged in
     */
    public UserSession(String email) {
        this.m_currentUserEmail = email;
        this.m_role = null;
        ApplicationPOT.getInstance().setCurrentSession(this);
    }

    /**
     * A constructor of UserSession that receives a User as parameter
     * and initializes the email and role variable
     *
     * @param email the User's email that is currently logged in
     * @param role  the User's role that is currently logged in
     */
    public UserSession(String email, Integer role) {
        this.m_currentUserEmail = email;
        switch (role) {
            case 1:
                this.m_role = Role.CLIENT;
                break;
            case 2:
                this.m_role = Role.ADMIN;
                break;
            case 3:
                this.m_role = Role.COURIER;
                break;
            default:
                this.m_role = null;
        }
        ApplicationPOT.getInstance().setCurrentSession(this);
    }

    /**
     * Returns the current logged User's email.
     *
     * @return User's email.
     */
    public String getCurrentUserEmail() {
        return m_currentUserEmail;
    }

    public Role getRole() {
        return m_role;
    }

    public void setRole(Role m_role) {
        this.m_role = m_role;
    }

}
