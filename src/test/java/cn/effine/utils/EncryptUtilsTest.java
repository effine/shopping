package cn.effine.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 加密工具类测试
 * Created by effine on 14/12/2016.
 */
public class EncryptUtilsTest {

    @Test
    public void testEncryptionString() {
        String target = EncryptUtils.encryptString("password", AlgorithmEnum.MD5);
        assertEquals("9DBB300E28BC21C8DAB41B01883918EB", target);
    }


}
