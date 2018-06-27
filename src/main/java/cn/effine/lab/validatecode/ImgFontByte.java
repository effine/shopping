package cn.effine.lab.validatecode;

import cn.effine.utils.PropertiesUtils;
import org.apache.log4j.Logger;

import java.awt.*;
import java.io.ByteArrayInputStream;

/**
 * ttf字体文件
 *
 * @author dsna
 */
public class ImgFontByte {

    private Logger logger = Logger.getLogger(ImgFontByte.class);

    /**
     * 获得字体
     *
     * @param fontHeight
     * @return
     */
    public Font getFont(int fontHeight) {
        try {
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, new ByteArrayInputStream(hex2byte(PropertiesUtils.getProp("font"))));
            return baseFont.deriveFont(Font.PLAIN, fontHeight);
        } catch (Exception e) {
            logger.error(e);
            return new Font("Arial", Font.PLAIN, fontHeight);
        }
    }

    /**
     * @param font
     * @return
     */
    private byte[] hex2byte(String font) {
        if (font == null) {
            return new byte[0];
        }
        String str = font.trim();
        int len = str.length();
        int divisor = 2;
        if (len == 0 || len % divisor == 1) {
            return new byte[0];
        }

        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += divisor) {
                b[i / 2] = (byte) Integer
                        .decode("0x" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            logger.error(e);
        }
        return new byte[0];
    }
}