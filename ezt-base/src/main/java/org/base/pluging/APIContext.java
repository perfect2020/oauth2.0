package org.base.pluging;

import org.base.common.RequestRouting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * API映射关系处理
 * Created by wangwr on 2015/6/9.
 */
public class APIContext {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static List<RequestRouting> getAllReqMappings(ApplicationContext applicationContext){
        List<RequestRouting> requestRoutings = new ArrayList<RequestRouting>();
        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, HandlerMapping.class, true, false);
        for (HandlerMapping handlerMapping : allRequestMappings.values()) {
            if (handlerMapping instanceof RequestMappingHandlerMapping) {
                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
                for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()) {
                    try {
                        HandlerMethod handlerMethod = requestMappingInfoHandlerMethodEntry.getValue();
                        RequestRouting requestRouting = RequestRouting.getReqRouting(handlerMethod);
                        if(requestRouting!=null){
                            requestRoutings.add(requestRouting);
                        }
                    }catch (Exception e){
                    }
                }
                continue;
            }
        }
        return requestRoutings;
    }
}
