package org.oauth2.server.endpoint;

import org.base.constants.ExecuteStatus;
import org.base.exception.RuntimeExceptionWarning;
import org.oauth2.server.data.DataHandler;
import org.oauth2.server.data.DataHandlerFactory;
import org.oauth2.server.fetcher.accesstoken.AccessTokenFetcher;
import org.oauth2.server.fetcher.accesstoken.AccessTokenFetcher.FetchResult;
import org.oauth2.server.fetcher.accesstoken.AccessTokenFetcherProvider;
import org.oauth2.server.models.AccessToken;
import org.oauth2.server.models.AuthInfo;
import org.oauth2.server.pluging.Request;

/**
 *
 * Created by wangwr on 2016/3/30.
 */
public class ProtectedResourceHandler {

    private AccessTokenFetcherProvider accessTokenFetcherProvider;

    private DataHandlerFactory dataHandlerFactory;

    public Response handlerRequest(Request request){
        AccessTokenFetcher accessTokenFetcher = accessTokenFetcherProvider.getFetcher(request);
        if (accessTokenFetcher == null) {
            throw new RuntimeExceptionWarning(ExecuteStatus.invalid_token);
        }

        FetchResult fetchResult = accessTokenFetcher.fetch(request);
        DataHandler dataHandler = dataHandlerFactory.create(request);
        AccessToken accessToken = dataHandler.getAccessToken(fetchResult.getAccessToken());

        if(accessToken==null){
            throw new RuntimeExceptionWarning(ExecuteStatus.invalid_token);
        }

        long now = System.currentTimeMillis();
        if (accessToken.getCreatedOn().getTime() + accessToken.getExpiresIn() * 1000 <= now) {
            throw new RuntimeExceptionWarning(ExecuteStatus.expired_token);
        }

        AuthInfo authInfo = dataHandler.getAuthInfo(accessToken.getAuthId());
        if(authInfo==null){
            throw new RuntimeExceptionWarning(ExecuteStatus.invalid_token);
        }
        return new Response(authInfo.getUserId(),authInfo.getClientId(),authInfo.getScope());
    }


    public AccessTokenFetcherProvider getAccessTokenFetcherProvider() {
        return accessTokenFetcherProvider;
    }

    public void setAccessTokenFetcherProvider(AccessTokenFetcherProvider accessTokenFetcherProvider) {
        this.accessTokenFetcherProvider = accessTokenFetcherProvider;
    }

    public DataHandlerFactory getDataHandlerFactory() {
        return dataHandlerFactory;
    }

    public void setDataHandlerFactory(DataHandlerFactory dataHandlerFactory) {
        this.dataHandlerFactory = dataHandlerFactory;
    }

    public static class Response{

        private String userId;

        private String clientId;

        private String scope;

        public Response(String userId, String clientId, String scope) {
            this.userId = userId;
            this.clientId = clientId;
            this.scope = scope;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }
    }

}
