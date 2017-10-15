/**
 * @author effine
 * @Date 2015年12月21日  下午11:43:57
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.utils;

import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.SimpleImageException;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleRender;
import com.alibaba.simpleimage.render.WriteRender;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.imageio.*;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;
import java.util.Iterator;

/**
 * 图片工具类
 */
public class ImagesUtils {

    private static Logger logger = Logger.getLogger(ImagesUtils.class);

    private ImagesUtils() {
    }

    /**
     * 图片裁剪(目前支持jpg\jpeg\gif\png格式)
     *
     * @param srcname  源文件名
     * @param distname 目标文件名
     * @param x        X坐标（矩形左上方）
     * @param y        Y坐标（矩形左上方）
     * @param width    剪切的宽度
     * @param height   剪切的长度
     * @return Boolean
     */
    public static boolean cut(String srcname, String distname, int x, int y, int width, int height) {
        try {
            FileInputStream is = new FileInputStream(srcname);
            String suffix = srcname.substring(srcname.lastIndexOf(".") + 1);
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(suffix);
            ImageReader reader = it.next();
            ImageInputStream iis = ImageIO.createImageInputStream(is);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            return ImageIO.write(bi, suffix, new File(distname));
        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
        return false;
    }

    // TODO 阿里simpleimage无法使用
    public static void aliCompressImg() {
        //原图片
        File in = new File("d:/test/mac.jpg");
        //目的图片
        File out = new File("d:/test/mac1.jpg");
        //将图像缩略到1024x1024以内，不足1024x1024则不做任何处理
        ScaleParameter scaleParam = new ScaleParameter(211, 400);

        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        WriteRender wr = null;
        try {
            inStream = new FileInputStream(in);
            outStream = new FileOutputStream(out);
            ImageRender rr = new ReadRender(inStream);
            ImageRender sr = new ScaleRender(rr, scaleParam);
            wr = new WriteRender(sr, outStream);

            //触发图像处理
            wr.render();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            IOUtils.closeQuietly(inStream);
            IOUtils.closeQuietly(outStream);
            if (wr != null) {
                try {
                    wr.dispose();                   //释放simpleImage的内部资源
                } catch (SimpleImageException e) {
                    logger.error(e);
                }
            }
        }
    }

    /**
     * 压缩图片文件
     *
     * @param srcname  源图片
     * @param distname 目标图片
     * @param quality  压缩程度(0~1)
     * @return boolean
     */
    @SuppressWarnings("static-access")
    public static final boolean compress(String srcname, String distname, double quality) {
        if (StringUtils.isNotBlank(srcname) && StringUtils.isNotBlank(distname)) {
            String suffix = srcname.substring(srcname.lastIndexOf(".") + 1);
            // 指定写图片的方式为 jpg
            ImageWriter imgWrier = ImageIO.getImageWritersByFormatName(suffix).next();
            ImageWriteParam imgWriteParams = new JPEGImageWriteParam(null);
            // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
            imgWriteParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            imgWriteParams.setCompressionQuality((float) quality);
            imgWriteParams.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
            ColorModel colorModel = ColorModel.getRGBdefault();
            // 指定压缩时使用的色彩模式
            imgWriteParams.setDestinationType(new ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));
            File file = new File(srcname);
            imgWrier.reset();
            FileOutputStream out = null;
            try {
                BufferedImage src = ImageIO.read(file);
                out = new FileOutputStream(distname);
                // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造
                imgWrier.setOutput(ImageIO.createImageOutputStream(out));
                // 调用write方法，就可以向输入流写图片
                imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
            } catch (IOException e) {
                logger.error(e);
            } finally {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }
        return false;
    }
}