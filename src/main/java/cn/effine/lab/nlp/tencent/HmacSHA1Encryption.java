package cn.effine.lab.nlp.tencent;


import org.apache.commons.codec.binary.Hex;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class HmacSHA1Encryption {

    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    /**
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     *
     * @param encryptText 被签名的字符串
     * @param encryptKey  密钥
     * @return 返回被加密后的字符串
     * @throws Exception
     */
    public static String HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        // 完成 Mac 操作
        byte[] digest = mac.doFinal(text);

        return new BASE64Encoder().encode(byte2hex(digest).getBytes(ENCODING));
        // StringBuilder sBuilder = bytesToHexString(digest);
        //return sBuilder.toString();
    }

    /**
     * 转换成Hex
     *
     * @param bytesArray
     */
    public static StringBuilder bytesToHexString(byte[] bytesArray) {
        if (bytesArray == null) {
            return null;
        }
        StringBuilder sBuilder = new StringBuilder();
        for (byte b : bytesArray) {
            String hv = String.format("%02x", b);
            sBuilder.append(hv);
        }
        return sBuilder;
    }


    private static String byte2hex(final byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0xFF));
            if (stmp.length() == 1) hs = hs + "0" + stmp;
            else hs = hs + stmp;
        }
        return hs;
    }

    /**
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     *
     * @param encryptData 被签名的字符串
     * @param encryptKey  密钥
     * @return 返回被加密后的字符串
     * @throws Exception
     */
    public static String hmacSHA1Encrypt(byte[] encryptData, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        // 完成 Mac 操作
        byte[] digest = mac.doFinal(encryptData);
        StringBuilder sBuilder = bytesToHexString(digest);
        return sBuilder.toString();
    }


    public static void main(String[] args) throws Exception {

        String secretKey1 = "41pRkz3Um02SnxBKWWsLeoqIqRxHPNCw";
        String srcStr = "GETwenzhi.api.qcloud.com/v2/index.php?Action=TextDependency&Nonce=46108672&Region=bj&SecretId=AKID9A3HOLgi8sZ4MSpfFqGUVKyb7jATrw69&Timestamp=1504187073&content=JASON捷森五种水果麦片";

        byte[] data = secretKey1.getBytes();
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);

        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);

        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = srcStr.getBytes();
        // 完成 Mac 操作
        byte[] digest = mac.doFinal(text);
        for (byte b : digest) {
            System.out.format("%02x", b);
        }

//        byte[] hex = new Hex().encode(digest);

        String str = new String(digest);

        System.out.println();
        System.out.println(new BASE64Encoder().encode(str.getBytes()));

        System.out.println(new BASE64Encoder().encode("d4ffb02fa9cdc8d73beb0c9f22959b12d0417dd2".getBytes()));

        // 1P+wL6nNyNc76wyfIpWbEtBBfdI=
    }

}
