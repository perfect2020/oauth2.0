package org.base.utils;


import java.util.regex.Pattern;

/**
 * 字符串工具
 * Created by wangwr on 2016.3.24.
 */
public class StrUtil {

    public static final String TAB_CHAR = "\t";

    public static final String CLER = "\n";

    public static final Pattern REX_URI = Pattern.compile("^((https|http)?:\\/\\/)[^\\s]+");

    public static String repeat(String chars,int times){
        StringBuffer target = new StringBuffer();
        for(int i = 0;i<times;i++){
            target.append(chars);
        }
        return target.toString();
    }

    public static boolean isNull(Object obj){
        return obj == null;
    }

    public static boolean isEmpty(String str){
        return str==null || !(str.length()>0);
    }

    public static boolean matchUri(String uri){
        return (!isEmpty(uri) && REX_URI.matcher(uri).matches());
    }

    public static String appendUriParams(String uri,String key,String value){
        return uri.indexOf("?")>=0?String.format("%s&%s=%s",uri,key,value):String.format("%s?%s=%s",uri,key,value);
    }
}
