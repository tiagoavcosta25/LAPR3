package lapr.project.utils;

import java.io.File;
import java.io.FilenameFilter;

public class DirectoryVerification {

    private static final int SLEEP_TIME = 5000;

    public static String verifyFileCreation(String path, String filter, int secTimeToTest) {
        int slept = 0;
        int timeToTest = secTimeToTest * 1000;
        while(slept <= timeToTest - SLEEP_TIME) {
            FilenameFilter fileFilter = (dir, name) -> name.endsWith(filter);
            File dir = new File(path);
            try {
                if (!dir.isDirectory()) {
                    System.out.println("Path does not exist!");
                    throw new InterruptedException();
                }
                String[] list = dir.list(fileFilter);
                if (list.length == 0)
                    slept += SLEEP_TIME;
                else
                    return list[0].substring(0, list[0].length() - filter.length());
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                System.out.println("Path does not exist!");
                return "";
            }
        }
        return "";
    }
}
