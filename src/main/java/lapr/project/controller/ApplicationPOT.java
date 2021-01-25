package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.service.GraphServices;

public class ApplicationPOT {

    private UserSession moCurrentSession;
    private GraphServices moGraphServices;

    private static ApplicationPOT singleton = null;

    public ApplicationPOT() {
        moGraphServices = new GraphServices();
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

    public GraphServices getWorldMap() {
        return moGraphServices;
    }

}
