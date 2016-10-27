package org.base.utils;

import org.base.constants.ExecuteStatus;
import org.base.exception.RuntimeExceptionWarning;

/**
 * Created by wanwgr on 2016/4/7.
 */
public class Assert {

    public static void notNull(Object obj,String msg){
        if(StrUtil.isNull(obj)){
            throw new RuntimeExceptionWarning(ExecuteStatus.param_is_null,msg);
        }
    }

    public static void notEmpty(String str,String msg){
        if(StrUtil.isEmpty(str)){
            throw new RuntimeExceptionWarning(ExecuteStatus.param_is_null,msg);
        }
    }

    public static void expr(Boolean expr,String msg){
        if(expr){
            throw new RuntimeExceptionWarning(ExecuteStatus.execute_failure,msg);
        }
    }

    public static void expr(Boolean expr,ExecuteStatus status){
        if(expr){
            throw new RuntimeExceptionWarning(status);
        }
    }

    public static void expr(Boolean expr,ExecuteStatus status,String msg){
        if(expr){
            throw new RuntimeExceptionWarning(status,msg);
        }
    }
}
