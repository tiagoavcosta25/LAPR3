package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.service.GraphService;

public class ApplicationPOT {

    private UserSession moCurrentSession;
    private GraphService moGraphService;

    private static ApplicationPOT singleton = null;

    public ApplicationPOT() {
        moGraphService = new GraphService();
    }

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

    public void clearCurrentSession() { moCurrentSession = null; }

    public GraphService getWorldMap() {
        return moGraphService;
    }

}
