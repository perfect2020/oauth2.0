package org.oauth2.server.grant;

import org.oauth2.server.data.DataHandler;

/**
 * Created by wangwr on 2016/3/30.
 */
public interface GrantHandler {

    Object handleRequest(DataHandler dataHandler);

}
