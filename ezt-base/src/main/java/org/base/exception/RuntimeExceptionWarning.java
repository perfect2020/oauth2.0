package org.base.exception;

import org.base.constants.ExecuteStatus;
import org.base.utils.StackTraceUtil;
import org.base.utils.StrUtil;

/**
 * 警告级别异常
 * Created by wangwr on 2016/4/7.
 */
public class RuntimeExceptionWarning extends RuntimeException{

    private String explain;

    private ExecuteStatus executeStatus;

    public String throwClass;

    public RuntimeExceptionWarning(ExecuteStatus executeStatus ) {
        super(String.format("[code:%s,description:%s]",executeStatus.getCode(),executeStatus.getDescription()));
        this.executeStatus = executeStatus;
        this.throwClass = getThrowClass();
    }

    /**
     * explain将覆盖所status的description
     * @param executeStatus
     * @param explain
     */
    public RuntimeExceptionWarning(ExecuteStatus executeStatus,String explain) {
        super(String.format("[code:%s, description:%s, explain:%s]",executeStatus.getCode(),executeStatus.getDescription(),explain));
        this.explain = explain;
        this.executeStatus = executeStatus;
        this.throwClass = getThrowClass();
    }

    public RuntimeExceptionWarning(ExecuteStatus executeStatus, Throwable cause) {
        super(String.format("[code:%s,description:%s]",executeStatus.getCode(),executeStatus.getDescription()), cause);
        this.executeStatus = executeStatus;
        this.throwClass = getThrowClass();
    }

    /**
     * explain将覆盖所status的description
     * @param executeStatus
     * @param explain
     * @param cause
     */
    public RuntimeExceptionWarning(ExecuteStatus executeStatus,String explain, Throwable cause) {
        super(String.format("[code:%s,description:%s, explain:%s]",executeStatus.getCode(),executeStatus.getDescription(),explain), cause);
        this.explain = explain;
        this.executeStatus = executeStatus;
        this.throwClass = getThrowClass();
    }

    public String getError(){
        return executeStatus.name();
    }

    public String getErrorCode(){
        return executeStatus.getCode();
    }

    public String getErrorDescription(){
        if(StrUtil.isEmpty(explain)){
            return executeStatus.getDescription();
        }
        return explain;
    }

    private String getThrowClass(){
        return StackTraceUtil.getPreCallStack(this.getClass(),"<init>").getClassName();
    }
}
