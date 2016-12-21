package cn.effine.utils;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.Assert.assertEquals;

/**
 * 加密工具类测试
 * Created by effine on 14/12/2016.
 */
public class EncryptUtilsTest {

    @Test
    public void testEncryptionString() {
        String md5Hash = EncryptUtils.encryptString("password", AlgorithmEnum.MD5);
        assertEquals("9DBB300E28BC21C8DAB41B01883918EB", md5Hash);

        String bcrypptHash = EncryptUtils.encryptString("password", AlgorithmEnum.BCRYPT);
        boolean flag = BCrypt.checkpw("password", bcrypptHash);
        assertEquals(true, flag);
    }


}
