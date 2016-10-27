package org.oauth2.server.data;

import org.oauth2.server.models.AccessToken;
import org.oauth2.server.pluging.Request;

/**
 * Created by wangwr on 2016/3/30.
 */
public interface DataHandlerFactory {

    DataHandler create(Request request);

}
