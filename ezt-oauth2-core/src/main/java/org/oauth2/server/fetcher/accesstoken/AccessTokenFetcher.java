package org.oauth2.server.fetcher.accesstoken;

import org.oauth2.server.pluging.Request;

import java.util.Map;

/**
 * access-token获取
 * Created by wangwr on 2016/3/30.
 */
public interface AccessTokenFetcher {


    boolean match(Request request);

    /**
     * 获取access_token结果
     * @param request
     * @return
     */
    FetchResult fetch(Request request);

    class FetchResult{

        private String accessToken;

        private Map<String,String> params;


        public FetchResult(String accessToken, Map<String, String> params) {
            this.accessToken = accessToken;
            this.params = params;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}
