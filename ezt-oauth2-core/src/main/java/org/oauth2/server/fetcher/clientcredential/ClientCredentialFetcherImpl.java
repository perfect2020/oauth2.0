package org.oauth2.server.fetcher.clientcredential;

import org.oauth2.server.pluging.Request;

/**
 * Created by wangwr on 2016/3/30.
 */
public class ClientCredentialFetcherImpl implements ClientCredentialFetcher {

    @Override
    public ClientCredential fetch(Request request) {
        return new ClientCredential(request.getParameter("client_id"),request.getParameter("client_secret"));
    }

}
