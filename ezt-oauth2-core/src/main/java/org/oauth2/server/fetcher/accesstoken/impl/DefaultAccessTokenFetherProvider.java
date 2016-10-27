package org.oauth2.server.fetcher.accesstoken.impl;

import org.oauth2.server.fetcher.accesstoken.AccessTokenFetcher;
import org.oauth2.server.fetcher.accesstoken.AccessTokenFetcherProvider;

/**
 * 默认的accessToken provider
 * Created by wangwr on 2016/3/30.
 */
public class DefaultAccessTokenFetherProvider extends AccessTokenFetcherProvider {


    public DefaultAccessTokenFetherProvider() {
        super(new AccessTokenFetcher[]{new RequestParameter()});
    }
}
