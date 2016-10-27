package org.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  日志工具类
 * Created by wangwr on 2016/4/13.
 */
public class LogUtil {

    private static Logger defaultLogger = LoggerFactory.getLogger(LogUtil.class);

    /**
     * 获取调用log的类
     * @return
     */
    public static Logger getLogger(){
        return LoggerFactory.getLogger(StackTraceUtil.getPreCallStack(LogUtil.class,"getLogger").getClassName());
    }


    /**
     * 获取调用log的类
     * @return
     */
    public static Logger getLogger(String className){
        return LoggerFactory.getLogger(className);
    }


    /**
     * 获取调用log的类
     * @return
     */
    public static Logger getLogger(Class<?> preClazz,String method){
        return LoggerFactory.getLogger(StackTraceUtil.getPreCallStack(preClazz,method).getClassName());
    }
}
