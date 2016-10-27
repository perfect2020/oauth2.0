package org.ezt.handler;

import org.ezt.service.OAuthService;
import org.oauth2.server.data.DataHandler;
import org.oauth2.server.data.DataHandlerFactory;
import org.oauth2.server.pluging.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * Created by wangwr on 2016/4/5.
 */
@Component
public class DefaultDataHandlerFactory implements DataHandlerFactory {

    @Autowired
    private OAuthService oauthService;

    @Override
    public DataHandler create(Request request) {
        DefaultDataHandler dataHandler = new DefaultDataHandler(request);
        dataHandler.setOauthService(oauthService);
        return dataHandler;
    }
}
