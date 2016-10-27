package org.oauth2.server.intercepter;

import org.oauth2.server.endpoint.ProtectedResourceHandler;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangwr on 2016/3/30.
 */
public class ProtectedResourceInterceptor  extends HandlerInterceptorAdapter{

    private ProtectedResourceHandler protectedResourceHandler;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }

}
