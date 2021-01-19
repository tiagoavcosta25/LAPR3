package lapr.project.controller;

import lapr.project.model.UserSession;

public class ApplicationPOT {

    private UserSession m_currentSession;

    private static ApplicationPOT singleton = null;

    public ApplicationPOT() {
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
        return m_currentSession;
    }

    /**
     * Modifies the current session.
     * @param session the current session.
     */
    public void setCurrentSession(UserSession session) {
        m_currentSession = session;
    }

    public void clearCurrentSession() { m_currentSession = null; }

}
