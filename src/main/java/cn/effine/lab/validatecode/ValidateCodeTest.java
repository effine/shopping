package cn.effine.lab.validatecode;

import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 */
public class ValidateCodeTest {

    private static Logger logger = Logger.getLogger(ValidateCodeTest.class);


    /**
     * @param args
     */
    public static void main(String[] args) {
        ValidateCode vCode = new ValidateCode(120, 40, 5, 100);
        try {
            String path = "D:/t/" + System.currentTimeMillis() + ".png";
            System.out.println(vCode.getCode() + " >" + path);
            vCode.write(path);
        } catch (IOException e) {
            logger.error(e);
        }
    }

}
