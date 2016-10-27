package org.oauth2.server.grant;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.base.utils.beannote.Note;

/**
 * Created by wangwr on 2016/3/30.
 */
@JsonPropertyOrder({
        "token_type",
        "access_token",
        "refresh_token",
        "expires_in",
        "scope"})
public class GrantResult {

    @Note("token类型")
    @JsonProperty("token_type")
    private String tokenType;

    @Note("token值")
    @JsonProperty("access_token")
    private String accessToken;

    @Note("有效时间段")
    @JsonProperty("expires_in")
    private Long expiresIn;

    @Note("刷新token凭证")
    @JsonProperty("refresh_token")
    private String refreshToken;

    private String scope;

    public GrantResult() {
    }

    public GrantResult(String tokenType, String accessToken) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
