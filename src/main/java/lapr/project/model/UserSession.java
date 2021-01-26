package lapr.project.model;

import lapr.project.controller.ApplicationPOT;

public class UserSession {

    /**
     * The Current User's email
     */
    private String mstrCurrentUserEmail;

    /**
     * The Current User's role
     */
    private Role moRole;


    public enum Role {
        ADMIN("Administrator"),
        COURIER("Courier"),
        CLIENT("Client");

        private final String mstrLabel;

        private Role(String label) {
            this.mstrLabel = label;
        }

        public String getRole() {
            return this.mstrLabel;
        }

    }

    /**
     * Empty constructor of UserSession
     * which initializes a null session
     */
    public UserSession() {
        this.mstrCurrentUserEmail = null;
        this.moRole = null;
        ApplicationPOT.getInstance().clearCurrentSession();
    }

    /**
     * A constructor of UserSession that receives an Email as parameter
     * and initializes the email variable
     *
     * @param email the User's email that is currently logged in
     */
    public UserSession(String email) {
        this.mstrCurrentUserEmail = email;
        this.moRole = null;
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
        this.mstrCurrentUserEmail = email;
        switch (role) {
            case 1:
                this.moRole = Role.CLIENT;
                break;
            case 2:
                this.moRole = Role.ADMIN;
                break;
            case 3:
                this.moRole = Role.COURIER;
                break;
            default:
                this.moRole = null;
        }
        ApplicationPOT.getInstance().setCurrentSession(this);
    }

    public UserSession(String email, Role role) {
        this.mstrCurrentUserEmail = email;
        this.moRole = role;
    }

    /**
     * Returns the current logged User's email.
     *
     * @return User's email.
     */
    public String getCurrentUserEmail() {
        return mstrCurrentUserEmail;
    }

    public Role getRole() {
        return moRole;
    }

    public void setRole(Role oRole) {
        this.moRole = oRole;
    }

}
