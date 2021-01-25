package lapr.project.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;

public class QRCode {

    private QRCode() {
    }

    private static String CHARSET = "UTF-8";
    private static String FORMAT = ".png";
    private static String PATH = "src/main/resources/imgs/";
    private static int HEIGHT = 200;
    private static int WIDTH = 200;

    public static boolean generateQRCode(String data, String fileName) throws WriterException, IOException {

        try{
            Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();

            hashMap.put(EncodeHintType.ERROR_CORRECTION,
                    ErrorCorrectionLevel.L);

            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(data.getBytes(CHARSET), CHARSET),
                    BarcodeFormat.QR_CODE, WIDTH, HEIGHT);

            String path = PATH + fileName + FORMAT;

            MatrixToImageWriter.writeToPath(matrix, path.substring(path.lastIndexOf('.') + 1), Paths.get(path));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static String readQRCode(String fileName) throws IOException, NotFoundException{
        try{
            BinaryBitmap binaryBitmap
                    = new BinaryBitmap(new HybridBinarizer(
                    new BufferedImageLuminanceSource(
                            ImageIO.read(
                                    new FileInputStream(PATH + fileName + FORMAT)))));

            Result result
                    = new MultiFormatReader().decode(binaryBitmap);

            return result.getText();
        } catch (Exception e){
            return null;
        }
    }
}
