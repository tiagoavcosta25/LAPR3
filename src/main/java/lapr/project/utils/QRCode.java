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

public class QRCode {

    private QRCode() {
    }

    private static String mCHARSET = "UTF-8";
    private static String mFORMAT = ".png";
    private static String mPATH = "src/main/resources/imgs/";
    private static int mHEIGHT = 200;
    private static int mWIDTH = 200;

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
