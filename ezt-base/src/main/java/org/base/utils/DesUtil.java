package org.base.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Locale;

/**
 * 网上copy的DES加解密算法
 * create by wangwr
 */
public class DesUtil {
 
    private final static String DES = "DES";

    private final static char[] M_CHARS = "0123456789ABCDEF".toCharArray();

    /**
     * Description 根据键值进行加密
     * @param data 
     * @return
     * @throws Exception
     */
    public static String encrypt(String data,String key) {
        try {
            if (data == null) throw new RuntimeException(" 参数为null");
            byte[] bt = encrypt(data.getBytes(), key.getBytes());
            //String strs = new BASE64Encoder().encode(bt);
            String strs = byteToHexStr(bt,bt.length);
            return strs;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
 
    /**
     * Description 根据键值进行解密
     * @param data
     * @return
     */
    public static String decrypt(String data,String key) {
        try {
            if (data == null) throw new RuntimeException(" 参数为null");
            byte[] buf = hexStrToBytes(data);
            byte[] bt = decrypt(buf,key.getBytes());
            return new String(bt);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
 
    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
 
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
 
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
 
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
 
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }
     
     
    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
 
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
 
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
 
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
 
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }

    /**
     * bytes转换成十六进制字符串
     * @param b byte[] byte数组
     * @param iLen int 取前N位处理 N=iLen
     * @return String 每个Byte值之间空格分隔
     */
    public static String byteToHexStr(byte[] b, int iLen){
        StringBuilder sb = new StringBuilder();
        for (int n=0; n<iLen; n++){
            sb.append(M_CHARS[(b[n] & 0xFF) >> 4]);
            sb.append(M_CHARS[b[n] & 0x0F]);
            //sb.append(' ');
        }
        return sb.toString().trim().toUpperCase(Locale.US);
    }

    /**
     * bytes字符串转换为Byte值
     * @param src String Byte字符串，每个Byte之间没有分隔符(字符范围:0-9 A-F)
     * @return byte[]
     */
    public static byte[] hexStrToBytes(String src) {
        /*对输入值进行规范化整理*/
        src = src.trim().toUpperCase(Locale.US);
        //处理值初始化
        int m=0,n=0;
        int iLen=src.length()/2; //计算长度
        byte[] ret = new byte[iLen]; //分配存储空间

        for (int i = 0; i < iLen; i++){
            m=i*2+1;
            n=m+1;
            ret[i] = (byte)(Integer.decode("0x"+ src.substring(i*2, m) + src.substring(m,n)) & 0xFF);
        }
        return ret;
    }

}