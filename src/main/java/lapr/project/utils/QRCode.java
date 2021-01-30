package lapr.project.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * QRCode.
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
public class QRCode {

    /**
     * Private Construtor.
     */
    private QRCode() {
    }

    /**
     * Charset.
     */
    private static String mCHARSET = "UTF-8";

    /**
     * Format.
     */
    private static String mFORMAT = ".png";

    /**
     * Path.
     */
    private static String mPATH = "src/main/resources/imgs/";

    /**
     * Height of the Qr Code.
     */
    private static int mHEIGHT = 200;

    /**
     * Width of the Qr Code.
     */
    private static int mWIDTH = 200;

    /**
     * Generates a QR Code based on a string with a fileName.png
     * @param data string to generate the Qr Code.
     * @param fileName file name.
     * @return true if everything works out, false if it doesn't.
     */
    public static boolean generateQRCode(String data, String fileName) {

        try{
            Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();

            hashMap.put(EncodeHintType.ERROR_CORRECTION,
                    ErrorCorrectionLevel.L);

            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(data.getBytes(mCHARSET), mCHARSET),
                    BarcodeFormat.QR_CODE, mHEIGHT, mWIDTH);

            String path = mPATH + fileName + mFORMAT;

            MatrixToImageWriter.writeToPath(matrix, path.substring(path.lastIndexOf('.') + 1), Paths.get(path));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Reads a certain Qr Code.
     * @param fileName file name.
     * @return the information of the Qr Code.
     */
    public static String readQRCode(String fileName){
        try{
            BinaryBitmap binaryBitmap
                    = new BinaryBitmap(new HybridBinarizer(
                    new BufferedImageLuminanceSource(
                            ImageIO.read(
                                    new FileInputStream(mPATH + fileName + mFORMAT)))));

            Result result
                    = new MultiFormatReader().decode(binaryBitmap);

            return result.getText();
        } catch (Exception e){
            return null;
        }
    }
}
