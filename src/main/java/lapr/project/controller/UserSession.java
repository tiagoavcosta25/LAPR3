package lapr.project.controller;

import lapr.project.model.User;

/**
 * The Class that represents the Current Session that is logged on the app
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
public class UserSession {

    /**
     * The Current User's email
     */
    private String m_currentUserEmail;

    /**
     * Empty constructor of CurrentSession
     * which initializes a null session
     *
     */
    public UserSession() {
        this.m_currentUserEmail = null;
        ApplicationPOT.getInstance().setCurrentSession(this);
    }
    /**
     * A constructor of CurrentSession that receives a User as parameter
     * and initializes the variables to the ones referring to the User
     *
     * @param user the User that is currently logged in
     */
    public UserSession(User user){
        m_currentUserEmail = user.getStrEmail();
        ApplicationPOT.getInstance().setCurrentSession(this);
    }

    /**
     * Returns the current logged User's email.
     *
     * @return User's email.
     */
    public String getM_currentUserEmail(){
        return m_currentUserEmail;
    }


}
