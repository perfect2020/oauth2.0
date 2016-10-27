package org.oauth2.server.fetcher.clientcredential;

import org.oauth2.server.pluging.Request;

/**
 * 第三方凭证获取
 * Created by wangwr on 2016/3/30.
 */
public interface ClientCredentialFetcher {


    ClientCredential fetch(Request request);


    class ClientCredential {

        private String clientId;
        private String clientSecret;


        public ClientCredential(String clientId, String clientSecret) {
            super();
            this.clientId = clientId;
            this.clientSecret = clientSecret;
        }


        public String getClientId() {
            return clientId;
        }


        public String getClientSecret() {
            return clientSecret;
        }

    }

}
