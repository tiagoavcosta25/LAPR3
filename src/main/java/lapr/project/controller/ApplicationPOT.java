package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.service.GraphService;

/**
 * ApplicationPOT.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */

public class ApplicationPOT {

    /**
     * User Session Instance.
     */
    private UserSession moCurrentSession;

    /**
     * Graph Management class.
     */
    private GraphService moGraphService;

    /**
     * Singleton.
     */
    private static ApplicationPOT singleton = null;

    /**
     * An empty constructor of ApplicationPOT.
     */
    public ApplicationPOT() {
        moGraphService = new GraphService();
    }

    /**
     * Returns the current Instance.
     * @return the current Instance.
     */
    public static ApplicationPOT getInstance()
    {
        if(singleton == null)
        {
            synchronized(ApplicationPOT.class)
            {
                singleton = new ApplicationPOT();
            }
        }
        return singleton;
    }

    /**
     * Returns the current session.
     * @return the current session.
     */
    public UserSession getCurrentSession() {
        return moCurrentSession;
    }

    /**
     * Modifies the current session.
     * @param session the current session.
     */
    public void setCurrentSession(UserSession session) {
        moCurrentSession = session;
    }

    /**
     * The methods clears the current session.
     */
    public void clearCurrentSession() { moCurrentSession = null; }

    /**
     * Returns the World Map Intance.
     * @return the World Map Intance.
     */
    public GraphService getWorldMap() {
        return moGraphService;
    }

}
