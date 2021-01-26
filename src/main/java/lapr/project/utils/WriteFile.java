package lapr.project.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * WriteFile.
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
public class WriteFile {

    /**
     * Private Constructor.
     */
    private WriteFile() {
    }

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    /**
     * Writes a file with a certain fileName.txt and certain content.
     * @param strFileName file name.
     * @param strContent content of the file.
     * @return true if everything works out, false if it doesn't.
     */
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
