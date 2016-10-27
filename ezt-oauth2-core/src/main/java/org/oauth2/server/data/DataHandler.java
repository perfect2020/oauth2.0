package org.oauth2.server.data;

import org.oauth2.server.models.AccessToken;
import org.oauth2.server.models.AuthInfo;
import org.oauth2.server.pluging.Request;

/**
 * Created by wangwr on 2016/3/30.
 */
public abstract class DataHandler {


    private Request request;

    public DataHandler(Request request) {
        this.request = request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }

    public abstract AccessToken getAccessToken(String accessToken);

    public abstract AuthInfo getAuthInfo(Long authId);

    public abstract AuthInfo getAuthInfoByCode(String code);

    public abstract AccessToken createOrUpdateAccessToken(AuthInfo authInfo);

    public abstract AuthInfo getAuthInfoByRefreshToken(String refreshToken);

    public abstract boolean validateClient(String clientId,String clientSecret,String grantType);

}
