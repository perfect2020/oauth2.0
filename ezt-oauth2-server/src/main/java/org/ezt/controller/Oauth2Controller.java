package org.ezt.controller;

import org.base.annotation.API;
import org.base.constants.ExecuteStatus;
import org.base.exception.RuntimeExceptionWarning;
import org.base.pluging.APIProcessor;
import org.base.pluging.RequestHandler;
import org.base.pluging.ModelView;
import org.base.runtime.HttpServiceContext;
import org.base.utils.Assert;
import org.base.utils.JsonUtil;
import org.base.utils.StrUtil;
import org.ezt.handler.OauthHandler;
import org.ezt.service.OAuthService;
import org.ezt.views.OauthClientInfo;
import org.ezt.views.UserInfo;
import org.oauth2.server.data.DataHandlerFactory;
import org.oauth2.server.endpoint.AccessTokenHandler;
import org.oauth2.server.fetcher.clientcredential.ClientCredentialFetcher;
import org.oauth2.server.fetcher.clientcredential.ClientCredentialFetcherImpl;
import org.oauth2.server.grant.GrantHandlerProvider;
import org.oauth2.server.grant.GrantResult;
import org.oauth2.server.grant.impl.DefaultGrantHandlerProvider;
import org.oauth2.server.pluging.HttpServletRequestAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

/**
 * oauth2.0授权
 * Created by wangwr on 2016.3.25.
 */
@RestController
public class Oauth2Controller extends APIProcessor {

    @Autowired
    private OAuthService oauthService;

    @Autowired
    private DataHandlerFactory dataHandlerFactory;

    private ClientCredentialFetcher clientCredentialFetcher;

    private GrantHandlerProvider grantHandlerProvider;

    private AccessTokenHandler accessTokenHandler;


    public Oauth2Controller() {
        super();
        clientCredentialFetcher = new ClientCredentialFetcherImpl();
        grantHandlerProvider = new DefaultGrantHandlerProvider();
        accessTokenHandler = new AccessTokenHandler();
        accessTokenHandler.setClientCredentialFetcher(clientCredentialFetcher);
        accessTokenHandler.setGrantHandlerProvider(grantHandlerProvider);
    }

    @API(name = "oauth2用户登录授权Code",auth = false,params = {"client_id:第三方应用标识ID",
            "response_type:目前只支持code","redirect_uri:登录后跳转的第三方地址"},respData = "登录界面")
    @RequestMapping(value = "/oauth2/authorize",method = RequestMethod.GET)
    public Object authorize(){
        return handleRequest(new RequestHandler() {
            @Override
            public Object execute() {

                HttpServletRequestAdapter requestAdapter = new HttpServletRequestAdapter(HttpServiceContext.getRequest());

                String pageType = requestAdapter.getParameter("page_type");

                if(StrUtil.isEmpty(pageType)){//适配识别网页
                    return new ModelView("intelligent.vm");
                }

                String clientId = requestAdapter.getParameter("client_id");
                String redirectUri = requestAdapter.getParameter("redirect_uri");

                Assert.expr(StrUtil.isNull(clientId),ExecuteStatus.unknown_client_id);

                Assert.expr(!StrUtil.matchUri(redirectUri),ExecuteStatus.invalid_redirect_uri);

                OauthClientInfo clientInfo = oauthService.getClientInfo(clientId);
                Map<String, Object> model = JsonUtil.toMap(JsonUtil.toJson(clientInfo));
                model.put("response_type", requestAdapter.getParameter("response_type"));
                model.put("redirect_uri", redirectUri);
                return new ModelView(String.format("oauth_login_%s.vm",pageType),model);
            }
        },true);
    }

    @API(name = "oauth2用户登录",auth = false,respData = "用户授权界面")
    @RequestMapping(value = "/oauth2/authorize",method = RequestMethod.POST)
    public Object authorizeLogin(final HttpServletRequest request, final HttpServletResponse response){
        return handleRequest(new RequestHandler() {
            @Override
            public Object execute() {
                String action = request.getParameter("action");
                Iterator<OauthHandler> handlers = HttpServiceContext.getBeans(OauthHandler.class);
                while (handlers.hasNext()){
                    OauthHandler handler = handlers.next();
                    if(handler.getAction().match(action)){
                        return handler.handlerRequest(request,response);
                    }
                }
                throw new RuntimeExceptionWarning(ExecuteStatus.execute_failure);
            }
        },true);

    }

    @API(name = "第三方平台获取token",auth = false,params = {"client_id:第三方应用标识ID",
            "client_secret:第三方秘钥","grant_type:authorization_code",
            "response_type:目前只支持code","redirect_uri:登录后跳转的第三方地址"},respClass = GrantResult.class)
    @RequestMapping(value = "/oauth2/access_token",method = RequestMethod.GET)
    public Object getAccessToken(){
        return handleRequest(new RequestHandler() {
            @Override
            public Object execute() {
                HttpServletRequestAdapter requestAdapter = new HttpServletRequestAdapter(HttpServiceContext.getRequest());
                accessTokenHandler.setDataHandlerFactory(dataHandlerFactory);
                return accessTokenHandler.handlerRequest(requestAdapter);
            }
        });
    }

    @API(name = "第三方平台获取用户基本信息",auth = true,params = {"access_token:授权的token"},respClass = UserInfo.class)
    @RequestMapping(value = "/oauth2/user_info",method = RequestMethod.GET)
    public Object getUserInfo(){
        return handleRequest(new RequestHandler() {
            @Override
            public Object execute() {
                return oauthService.getUserInfo(HttpServiceContext.getOpenId());
            }
        });
    }
}
