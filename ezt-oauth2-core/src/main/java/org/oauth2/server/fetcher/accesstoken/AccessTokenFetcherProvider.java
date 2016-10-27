package org.oauth2.server.fetcher.accesstoken;

import org.oauth2.server.pluging.Request;

/**
 * access_token 获取提供者
 * Created by wangwr on 2016/3/30.
 */
public class AccessTokenFetcherProvider {

    private AccessTokenFetcher[] fetchers;

    public AccessTokenFetcherProvider(AccessTokenFetcher[] fetchers) {
        this.fetchers = fetchers;
    }

    public AccessTokenFetcher getFetcher(Request request){
        for(AccessTokenFetcher fetcher:fetchers){
            if(fetcher.match(request)){
                return fetcher;
            }
        }
        return null;
    }

    public AccessTokenFetcher[] getFetchers() {
        return fetchers;
    }

    public void setFetchers(AccessTokenFetcher[] fetchers) {
        this.fetchers = fetchers;
    }
}
