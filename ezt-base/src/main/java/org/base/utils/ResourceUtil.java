package org.base.utils;

import org.base.exception.ResourceNotFoundException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 资源获取
 * Created by wangwr on 2016.3.23.
 */
public class ResourceUtil {

    public static final String ENTER_NEWLINE_CODE= "\r\n";

    /**
     * 获取文件内容
     * @param resourcePackage
     * @return
     */
    public static String getResourceContent(String resourcePackage){
        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = ResourceUtil.class.getClassLoader().getResourceAsStream(resourcePackage);
        if(inputStream==null) throw new ResourceNotFoundException("资源不存在");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String line ;
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line+ENTER_NEWLINE_CODE);
            }
        } catch (Exception e) {
            throw new RuntimeException("加载资源失败",e);
        }
        return stringBuffer.toString();
    }

}
