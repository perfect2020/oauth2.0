package org.oauth2.server.pluging;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by wangwr on 2016/3/30.
 */
public class HttpServletRequestAdapter implements Request {

    private HttpServletRequest request;

    public HttpServletRequestAdapter(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        return request.getParameter(name);
    }

    @Override
    public Map<String, String> getParameterMap() {
        Enumeration<String> keys = request.getParameterNames();
        Map<String,String> params = new HashMap<String, String>();
        while (keys.hasMoreElements()){
            String key = keys.nextElement();
            params.put(key,request.getParameter(key));
        }
        return params;
    }

    @Override
    public String getHeader(String name) {
        return request.getHeader(name);
    }
}
