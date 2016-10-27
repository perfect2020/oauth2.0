package org.ezt.runtime;

import org.base.common.RequestRouting;
import org.base.runtime.HttpServiceContext;
import org.base.utils.Assert;
import org.base.utils.ExceptionUtil;
import org.base.utils.JsonUtil;
import org.oauth2.server.data.DataHandlerFactory;
import org.oauth2.server.endpoint.ProtectedResourceHandler;
import org.oauth2.server.fetcher.accesstoken.impl.DefaultAccessTokenFetherProvider;
import org.oauth2.server.pluging.HttpServletRequestAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Handler;

/**
 * Created by wangwr on 2016/3/30.
 */
public class ProtectedResourceInterceptor extends HandlerInterceptorAdapter{

    private ProtectedResourceHandler protectedResourceHandler;

    @Autowired
    private DataHandlerFactory dataHandlerFactory;

    public ProtectedResourceInterceptor() {

        protectedResourceHandler = new ProtectedResourceHandler();
        protectedResourceHandler.setAccessTokenFetcherProvider(new DefaultAccessTokenFetherProvider());

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(HandlerMethod.class.isInstance(handler)){

            RequestRouting requestRouting = RequestRouting.getReqRouting((HandlerMethod) handler);

            if(requestRouting!=null && requestRouting.isApiAuth()){
                //需要权限认证
                protectedResourceHandler.setDataHandlerFactory(dataHandlerFactory);

                HttpServletRequestAdapter requestAdapter = new HttpServletRequestAdapter(HttpServiceContext.getRequest());

                try {
                    ProtectedResourceHandler.Response result = protectedResourceHandler.handlerRequest(requestAdapter);
                    Assert.notNull(request,"无效的凭证");
                    HttpServiceContext.setClientId(result.getClientId());
                    HttpServiceContext.setOpenId(result.getUserId());
                    HttpServiceContext.setScope(result.getScope());
                }catch (Exception e){
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(JsonUtil.toJson(ExceptionUtil.processException(e)));
                    response.getWriter().close();
                    return false;
                }
            }
        }

        return super.preHandle(request, response, handler);
    }

}
