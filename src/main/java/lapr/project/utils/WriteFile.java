package lapr.project.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteFile {

    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    public static boolean write(String strFileName, String strContent) {
        try {
            String strFilePath = "src/main/resources/files/output/" + strFileName + ".txt";
            File file = new File(strFilePath);

            FileWriter fw = new FileWriter(strFilePath);
            fw.write(strContent);
            fw.close();

            return true;

        } catch (Exception e){
            LOGGER.log(Level.WARNING, "There was a problem creating the file!");
            return false;
        }
    }
}
