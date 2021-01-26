package lapr.project.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteFile {

    private WriteFile() {
    }

    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    public static boolean write(String strFileName, String strContent) {

            String strFilePath = "src/main/resources/files/output/" + strFileName + ".txt";
            String body = String.format("______________________________________________________________________________________\n" +
                            "%s\n\n______________________________________________________________________________________\n\n" +
                            "Thank you for choosing us.\nKing regards,\nPharmacy Service G21.",strContent);

        try(FileWriter fw = new FileWriter(strFilePath);) {
            fw.write(body);
            fw.close();

            return true;

        } catch (Exception e){
            LOGGER.log(Level.WARNING, "There was a problem creating the file!");
            return false;
        }
    }
}
