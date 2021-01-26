package lapr.project.utils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DirectoryVerification {

    private static final Logger LOGGER = Logger.getLogger(DirectoryVerification.class.getName());

    private DirectoryVerification() {
    }

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
