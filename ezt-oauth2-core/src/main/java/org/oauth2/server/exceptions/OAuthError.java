package org.oauth2.server.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.xpath.internal.operations.String;

/**
 * 授权异常
 * Created by Administrator on 2016/3/30.
 */
public abstract class OAuthError extends RuntimeException {

    private String error;

    @JsonProperty("error_code")
    private String code;

    @JsonProperty("error_description")
    private String description;

    protected OAuthError(String code, String description) {
        super();
        this.code = code;
        this.description = description;
    }

}
