package org.base.utils;

import org.base.common.ErrorBody;
import org.base.constants.ExecuteStatus;
import org.base.exception.RuntimeExceptionWarning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  异常处理列类
 * Created by wangwr on 2016/4/8.
 */
public class ExceptionUtil {

    private static Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);

    public static ErrorBody processException(Exception e){
        if(e instanceof RuntimeExceptionWarning){
            RuntimeExceptionWarning warning = (RuntimeExceptionWarning) e;
            LogUtil.getLogger(warning.throwClass).warn(String.format("[code:%s,description:%s]",warning.getErrorCode(),warning.getErrorDescription()));
            return new ErrorBody(warning.getError(),warning.getErrorCode(),warning.getErrorDescription());
        }
        //异常降级
        logger.error("错误预警：",e);
        return new ErrorBody(ExecuteStatus.server_busy.name(),ExecuteStatus.server_busy.getCode(),ExecuteStatus.server_busy.getDescription());
    }
}
