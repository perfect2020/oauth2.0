package org.base.runtime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 服务上下文处理类。
 */
public class HttpServiceContextHandler extends HandlerInterceptorAdapter {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpServiceContext.init(applicationContext, request, response,handler);
        return super.preHandle(request, response, handler);
    }
}
