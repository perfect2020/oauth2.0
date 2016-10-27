package org.oauth2.server.grant.impl;

import org.apache.commons.lang3.StringUtils;
import org.base.constants.ExecuteStatus;
import org.base.exception.RuntimeExceptionWarning;
import org.oauth2.server.data.DataHandler;
import org.oauth2.server.fetcher.clientcredential.ClientCredentialFetcher;
import org.oauth2.server.grant.GrantHandler;
import org.oauth2.server.grant.GrantResult;
import org.oauth2.server.models.AccessToken;
import org.oauth2.server.models.AuthInfo;
import org.oauth2.server.pluging.Request;

/**
 * Created by wangwr on 2016/3/30.
 */
public abstract class AbstractGrantHandler implements GrantHandler{

    private ClientCredentialFetcher clientCredentialFetcher;

    public ClientCredentialFetcher getClientCredentialFetcher() {
        return clientCredentialFetcher;
    }

    public void setClientCredentialFetcher(ClientCredentialFetcher clientCredentialFetcher) {
        this.clientCredentialFetcher = clientCredentialFetcher;
    }

    /**
     * 创建accessToken
     * @param dataHandler
     * @param authInfo
     * @return
     */
    protected GrantResult issueAccessToken(DataHandler dataHandler,
                                                  AuthInfo authInfo) {
        AccessToken accessToken = dataHandler.createOrUpdateAccessToken(authInfo);
        GrantResult result = new GrantResult("bearer",accessToken.getToken());
        if (accessToken.getExpiresIn() > 0) {
            result.setExpiresIn(accessToken.getExpiresIn());
        }
        if (StringUtils.isNotEmpty(authInfo.getRefreshToken())) {
            result.setRefreshToken(authInfo.getRefreshToken());
        }
        if (StringUtils.isNotEmpty(authInfo.getScope())) {
            result.setScope(authInfo.getScope());
        }
        return result;
    }

    protected String getParameter(Request request, String name) {
        String value = request.getParameter(name);
        if (StringUtils.isEmpty(value)) {
            throw new RuntimeExceptionWarning(ExecuteStatus.param_is_null,"'" + name + "' not found");
        }
        return value;
    }

}
