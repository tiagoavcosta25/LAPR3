package lapr.project.model;

import lapr.project.controller.ApplicationPOT;

/**
 * UserSession.
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
public class UserSession {

    /**
     * The Current User's email
     */
    private String mstrCurrentUserEmail;

    /**
     * The Current User's role
     */
    private Role moRole;

    /**
     * Role Enum
     */
    public enum Role {
        /**
         * Possible Roles
         */
        ADMIN("Administrator"),
        COURIER("Courier"),
        CLIENT("Client");

        /**
         * Role designation
         */
        private final String mstrLabel;

        /**
         * Constructor of Role Enum, which sets the label to the one
         * given by parameter
         *
         * @param label Role label
         */
        private Role(String label) {
            this.mstrLabel = label;
        }

        /**
         * Returns the Role
         *
         * @return Role
         */
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

    /**
     * UserSession constructor, which sets all the atributes to the ones given by parameter
     *
     * @param email Current User Email
     * @param role  Current User Role
     */
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
