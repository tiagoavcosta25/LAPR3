package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.WorldMap;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationPOT {

    private UserSession m_currentSession;
    private WorldMap m_worldMap;

    private static ApplicationPOT singleton = null;

    public ApplicationPOT() {
        m_worldMap = new WorldMap();
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

    public WorldMap getWorldMap() {
        return m_worldMap;
    }

}
