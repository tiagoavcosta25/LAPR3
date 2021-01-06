package lapr.project.controller;

import lapr.project.model.Platform;

public class ApplicationPOT {

    private final Platform m_oPlataform;

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
}
