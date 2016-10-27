package org.base.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wangwr on 2016/4/8.
 */
public class ErrorBody {

    @JsonProperty("error")
    private String error;

    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("error_description")
    private String errorDescription;

    public ErrorBody(String error, String errorCode, String errorDescription) {
        this.error = error;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
