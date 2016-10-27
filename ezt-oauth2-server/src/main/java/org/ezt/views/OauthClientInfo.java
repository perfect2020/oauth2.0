package org.ezt.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ezt.models.OAuthClient;

/**
 * Created by wangwr on 2016/4/5.
 */
public class OauthClientInfo {

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_name")
    private String clientName;

    @JsonProperty("response_type")
    private String responseType;

    @JsonProperty("redirect_uri")
    private String redirectUri;

    @JsonProperty("openid")
    private String openid;

    @JsonProperty("scope")
    private String scope;

    public OauthClientInfo() {
    }

    public OauthClientInfo(OAuthClient oAuthClient){
        this.setClientId(oAuthClient.getClientId());
        this.setClientName(oAuthClient.getClientName());
        this.setRedirectUri(oAuthClient.getRedirectUri());
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
