package org.oauth2.server.grant;

import java.util.Map;

/**
 *
 * Created by wangwr on 2016/3/30.
 */
public class GrantHandlerProvider {

    private Map<String,GrantHandler> handlers;

    public GrantHandler getHandler(String type){
        return handlers.get(type);
    }

    public Map<String, GrantHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(Map<String, GrantHandler> handlers) {
        this.handlers = handlers;
    }
}
