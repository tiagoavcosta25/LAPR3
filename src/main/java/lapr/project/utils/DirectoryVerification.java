package lapr.project.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Directory Verification.
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

public class DirectoryVerification {

    /**
     * Logger which is used to generate warnings or information, with
     * a custom message.
     */
    private static final Logger LOGGER = Logger.getLogger(DirectoryVerification.class.getName());

    /**
     * An empty constructor of Directory Verification.
     */
    private DirectoryVerification() {
    }

    /**
     * verify File Creation.
     * @param path File Path.
     * @param filter Filter.
     * @param secTimeToTest Time To Test.
     * @return String.
     */
    public static String verifyFileCreation(String path, String filter, int secTimeToTest) {
        int slept = 0;
        int timeToTest = secTimeToTest * 1000;
        while(slept <= timeToTest - Constants.SLEEP_TIME) {
            FilenameFilter fileFilter = (dir, name) -> name.endsWith(filter);
            File dir = new File(path);
            try {
                if (!dir.isDirectory()) {
                    LOGGER.log(Level.WARNING, "Path doesn't exist!");
                    return "";
                }
                String[] list = dir.list(fileFilter);
                if (list.length == 0)
                    slept += Constants.SLEEP_TIME;
                else
                    return list[0].substring(0, list[0].length() - filter.length());
                Thread.sleep(Constants.SLEEP_TIME);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.log(Level.WARNING, "Something went wrong!");
            }
        }
        return "";
    }

}
