package com.xs.bqx.community.utils;


import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.UUID;

public class DESUtils {

    public static final String ALGORITHM = "DES";

    /**
     * 这是默认模式
     * public static final String transformation = "DES/ECB/PKCS5Padding";
     * 使用CBC模式, 在初始化Cipher对象时, 需要增加参数, 初始化向量IV : IvParameterSpec iv = new IvParameterSpec(key.getBytes());
     * public static final String transformation = "DES/CBC/PKCS5Padding";
     * NOPadding: 使用NOPadding模式时, 原文长度必须是8byte的整数倍
     * */
    public static final String TRANSFORMATION = "DES/ECB/NOPadding";
    public static final String KEY = "12345678";

    /**
     * QO2klVpoYT8=
     * QO2klVpoYT8=
     * */
    public static void main(String[] args) throws Exception {

        String encryptByDes = encryptByDes("17a3062b77704d779a98b6ef610caba7");

        System.out.println("加密："+encryptByDes);
        String decryptByDES = decryptByDES("ASmsvQ5PH/Z54bXJQeMyWKVmsYQZbHss6LUmtjCRmDo=");
        System.out.println( "解密：" + decryptByDES);

    }

    public static String encryptByDes(String original) throws Exception {
        // 获取Cipher
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);

      // 指定密钥规则
      SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);

        // 指定模式(加密)和密钥
        // 创建初始向量
        IvParameterSpec iv = new IvParameterSpec(KEY.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        //  cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
        // 加密
        byte[] bytes = cipher.doFinal(original.getBytes());
        // 输出加密后的数据
        // com.sun.org.apache.xml.internal.security.utils.Base64
        return Base64.encodeBase64String(bytes);
    }

    public static String decryptByDES(String encrypted) throws Exception {
        // 获取Cipher
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);

      // 指定密钥规则
      SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);

        // 指定模式(解密)和密钥
        // 创建初始向量
        IvParameterSpec iv = new IvParameterSpec(KEY.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        //  cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
        // 解码密文
        // com.sun.org.apache.xml.internal.security.utils.Base64
        byte[] decode = Base64.decodeBase64(encrypted);
        // 解密
        byte[] bytes = cipher.doFinal(decode);
        // 输出解密后的数据
        return new String(bytes);
    }

    /**
     *  已投递 公司id 使用DES 加密
     */
    public static String encryptByDesCompanyId(String original) throws Exception {
        // 获取Cipher
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // 指定密钥规则
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);

        // 指定模式(加密)和密钥
        // 创建初始向量
        IvParameterSpec iv = new IvParameterSpec(KEY.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        //  cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
        // 加密
        byte[] bytes = cipher.doFinal(original.getBytes());
        // 输出加密后的数据
        // com.sun.org.apache.xml.internal.security.utils.Base64
        return Base64.encodeBase64String(bytes);
    }

    /**
     *  已投递 公司id 使用DES 解密
     */
    public static String decryptByDESCompanyId(String encrypted) throws Exception {
        // 获取Cipher
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // 指定密钥规则
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);

        // 指定模式(解密)和密钥
        // 创建初始向量
        IvParameterSpec iv = new IvParameterSpec(KEY.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        //  cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
        // 解码密文
        // com.sun.org.apache.xml.internal.security.utils.Base64
        byte[] decode = Base64.decodeBase64(encrypted);
        // 解密
        byte[] bytes = cipher.doFinal(decode);
        // 输出解密后的数据
        return new String(bytes);
    }


}