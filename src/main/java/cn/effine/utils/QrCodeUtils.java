/**
 * @author effine
 * @Date 2015年8月13日  下午2:20:52
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.utils;


import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 * 二维码操作类
 */
public class QrCodeUtils {

    private static Logger logger = Logger.getLogger(QrCodeUtils.class);

    private QrCodeUtils() {
    }

    /**
     * 生成二维码图片
     *
     * @param width    宽度
     * @param height   高度
     * @param content  二维码内容
     * @param filePath 二维码生成路径
     * @return 二维码图片URL
     */
    public static String generateQRCode(int width, int height, String content, String filePath) {
        if (width <= 0 || height <= 0) {
            return "二维码图片尺寸错误";
        }
        if (StringUtils.isBlank(content)) {
            return "二维码内容为空";
        }

        // 图片类型
        String format = "png";
        // 文件名
        String fileName = System.currentTimeMillis() + "." + format;

        Map<EncodeHintType, String> hints = new HashMap<>(1);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        Path fullName = FileSystems.getDefault().getPath(filePath, fileName);
        try {
            // 生成矩阵二维码
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            // 输出图像
            MatrixToImageWriter.writeToPath(bitMatrix, format, fullName);
        } catch (IOException | WriterException e) {
            logger.error(e);
        }
        return fileName;
    }

    /**
     * 解析二维码图片
     *
     * @param fullName 图片网站文件名
     * @return 二维码图片内容
     */
    public static String parseQRCode(String fullName) {
        if (StringUtils.isBlank(fullName)) {
            return "二维码图片地址为空";
        }

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(fullName));
        } catch (IOException e1) {
            logger.error(e1);
        }
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
        Map<DecodeHintType, String> hints = new HashMap<>(1);
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        Result result;
        try {
            // 对图像进行解码
            result = new MultiFormatReader().decode(binaryBitmap, hints);
            return result.getText();
        } catch (NotFoundException e) {
            logger.error(e);
        }
        return null;
    }
}