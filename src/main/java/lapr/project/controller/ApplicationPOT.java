package lapr.project.controller;

import lapr.project.model.Platform;

public class ApplicationPOT {

    private final Platform m_oPlataform;
    private UserSession m_currentSession;

    public ApplicationPOT() {
        m_oPlataform = new Platform();
    }


    public Platform getPlatform()
    {
        return this.m_oPlataform;
    }

    private static ApplicationPOT singleton = null;

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

    public void clearCurrentSession() { m_currentSession = new UserSession(); }

}
