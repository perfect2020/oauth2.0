package org.ezt.handler;

import org.base.constants.ExecuteStatus;
import org.base.pluging.ModelView;
import org.base.utils.*;
import org.ezt.common.OAuthContentStore;
import org.ezt.service.OAuthService;
import org.ezt.views.OauthClientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangwr on 2016/4/5.
 */
@Component
public class OauthLoginHandler implements OauthHandler {

    @Autowired
    private OAuthService oauthService;

    @Override
    public ActionType getAction() {
        return ActionType.login;
    }

    @Override
    public ModelView handlerRequest(HttpServletRequest request, HttpServletResponse response) {
        String userAccount = request.getParameter("user_account");
        String passworld = request.getParameter("user_password");
        String clientId = request.getParameter("client_id");
        String redirectUri = request.getParameter("redirect_uri");
        String pageType = request.getParameter("page_type");

        Assert.expr(!StrUtil.matchUri(redirectUri),ExecuteStatus.invalid_redirect_uri);

        String scope = request.getParameter("scope");

        String openid = oauthService.userLogin(userAccount,passworld);

        OauthClientInfo clientInfo = oauthService.getClientInfo(clientId);

        clientInfo.setRedirectUri(redirectUri);

        Assert.expr(StrUtil.isNull(clientInfo), ExecuteStatus.unknown_client_id);

        clientInfo.setOpenid(openid);
        clientInfo.setScope(StrUtil.isEmpty(scope)?"user_info":scope);

        OAuthContentStore.EncryptClient encryptClient = OAuthContentStore.getInstance().encrypt(clientInfo);

        Map<String,Object> model = encryptClient.getMap();
        model.put("client_name",clientInfo.getClientName());

        return new ModelView(String.format("oauth_authorize_%s.vm",pageType),model);
    }
}
