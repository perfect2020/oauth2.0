package org.base.utils;

/**
 * 堆栈工具类
 * Created by wangwr on 2016/4/13.
 */
public class StackTraceUtil {

    /**
     * 获取指定类和方法的调用前一个堆栈
     * @param clazz
     * @param method
     * @return
     */
    public static StackTraceElement getPreCallStack(Class<?> clazz,String method){
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        String clazzMethod = clazz.getName()+method;
        boolean target = false;
        StackTraceElement preStack = null;
        for(StackTraceElement stack:stacks){
            if(target){
                preStack = stack;
                break;
            }
            if((stack.getClassName()+stack.getMethodName()).contains(clazzMethod)){
                target = true;
            }
        }
        if(preStack==null){
            preStack = stacks[0];
        }
        return preStack;
    }

}
