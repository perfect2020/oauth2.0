package org.base.common;

import org.base.annotation.API;
import org.base.utils.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;

/**
 * 请求路由信息
 * Created by wangwr on 2015/6/4.
 */
public class RequestRouting {

    private String reqUrl;

    private String reqMethod;

    private String apiName;

    private boolean apiAuth;

    private String[] params;

    private Class<?> reqClass;

    private Class<?> respClass;

    private String respData;

    public boolean isApiAuth() {
        return apiAuth;
    }

    public void setApiAuth(boolean apiAuth) {
        this.apiAuth = apiAuth;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public String getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(String reqMethod) {
        this.reqMethod = reqMethod;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getRespData() {
        return respData;
    }

    public void setRespData(String respData) {
        this.respData = respData;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public Class<?> getReqClass() {
        return reqClass;
    }

    public void setReqClass(Class<?> reqClass) {
        this.reqClass = reqClass;
    }

    public Class<?> getRespClass() {
        return respClass;
    }

    public void setRespClass(Class<?> respClass) {
        this.respClass = respClass;
    }

    public static RequestRouting getReqRouting(HandlerMethod handlerMethod){

        API api = handlerMethod.getMethodAnnotation(API.class);
        RequestMapping requestMapping = handlerMethod.getMethodAnnotation(RequestMapping.class);

        if(api == null){
            return null;
        }

        RequestRouting requestRouting = new RequestRouting();

        requestRouting.setApiAuth(api.auth());
        requestRouting.setApiName(api.name());
        requestRouting.setParams(api.params());
        requestRouting.setReqClass(api.reqClass());
        requestRouting.setRespClass(api.respClass());
        requestRouting.setRespData(api.respData());

        RequestMethod method = CollectionUtils.first(requestMapping.method());
        requestRouting.setReqMethod(method==null?"":method.name());
        requestRouting.setReqUrl(CollectionUtils.first(requestMapping.value()));

        return requestRouting;
    }
}
