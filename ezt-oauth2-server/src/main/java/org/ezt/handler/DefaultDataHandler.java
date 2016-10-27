package org.ezt.handler;

import org.base.constants.ExecuteStatus;
import org.base.exception.RuntimeExceptionWarning;
import org.base.runtime.HttpServiceContext;
import org.base.utils.RandomUtil;
import org.base.utils.StrUtil;
import org.ezt.common.OAuthCodeStore;
import org.ezt.models.OAuthAccessToken;
import org.ezt.models.OAuthClient;
import org.ezt.models.OAuthRefreshToken;
import org.ezt.service.OAuthService;
import org.ezt.views.OauthClientInfo;
import org.oauth2.server.data.DataHandler;
import org.oauth2.server.models.AccessToken;
import org.oauth2.server.models.AuthInfo;
import org.oauth2.server.pluging.Request;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by wangwr on 2016/4/5.
 */
public class DefaultDataHandler extends DataHandler {

    private OAuthService oauthService;

    public DefaultDataHandler(Request request) {
        super(request);
    }

    public void setOauthService(OAuthService oauthService) {
        this.oauthService = oauthService;
    }

    @Override
    public AccessToken getAccessToken(String accessToken) {
        OAuthAccessToken authAccessToken = oauthService.getAccessToken(accessToken);
        if(StrUtil.isNull(authAccessToken)){
            return null;
        }
        return authAccessToken.parseAccessToken();
    }

    @Override
    public AuthInfo getAuthInfo(Long authId) {
        OAuthAccessToken authAccessToken = oauthService.getAccessToken(authId);
        if(authAccessToken==null){
            return null;
        }
        AuthInfo authInfo = new AuthInfo();
        authInfo.setClientId(authAccessToken.getClientId());
        authInfo.setUserId(authAccessToken.getOpenid());
        authInfo.setRefreshToken(authAccessToken.getRefreshToken());
        authInfo.setScope(authAccessToken.getScope());
        return authInfo;
    }

    @Override
    public AuthInfo getAuthInfoByCode(String code) {
        return OAuthCodeStore.getInstance().getAuthInfo(code);
    }

    @Override
    public AccessToken createOrUpdateAccessToken(AuthInfo authInfo) {
        OAuthAccessToken authAccessToken = oauthService.createOrUpdateAccessToken(authInfo);
        return authAccessToken.parseAccessToken();
    }

    @Override
    public AuthInfo getAuthInfoByRefreshToken(String refreshToken) {
        OAuthRefreshToken token = oauthService.getRefreshToken(refreshToken);
        if (StrUtil.isNull(token)){
            return null;
        }
        long now = System.currentTimeMillis();
        if (token.getCreateTime().getTime() + token.getExpiresIn() * 1000 <= now) {
            throw new RuntimeExceptionWarning(ExecuteStatus.expired_refresh_token);
        }
        return token.parseAuthInfo();
    }

    @Override
    public boolean validateClient(String clientId, String clientSecret, String grantType) {
        OAuthClient clientInfo = oauthService.getOauthClient(clientId);
        if(clientInfo==null){
            throw new RuntimeExceptionWarning(ExecuteStatus.unknown_client_id);
        }
        return clientInfo.getClientSecret().equals(clientSecret);
    }
}
