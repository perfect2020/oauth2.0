package org.ezt.handler;

import org.base.constants.ExecuteStatus;
import org.base.pluging.RedirectPage;
import org.base.utils.Assert;
import org.base.utils.DesUtil;
import org.base.utils.JsonUtil;
import org.base.utils.StrUtil;
import org.ezt.common.OAuthCodeStore;
import org.ezt.common.OAuthContentStore;
import org.ezt.models.OAuthRefreshToken;
import org.ezt.service.OAuthService;
import org.ezt.views.OauthClientInfo;
import org.oauth2.server.models.AuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户授权模块
 * Created by wangwr on 2016/4/5.
 */
@Component
public class OauthAuthorizeHandler implements OauthHandler {

    @Override
    public ActionType getAction() {
        return ActionType.authorize;
    }

    @Autowired
    private OAuthService oAuthService;

    @Override
    public RedirectPage handlerRequest(HttpServletRequest request, HttpServletResponse response) {
        String authkey = request.getParameter("authkey");
        String authvalue = request.getParameter("authvalue");

        Assert.expr(StrUtil.isEmpty(authkey), ExecuteStatus.post_invalid_data);

        Assert.expr(StrUtil.isEmpty(authvalue), ExecuteStatus.post_invalid_data);

        OauthClientInfo oauthClientInfo = OAuthContentStore.getInstance().decrypt(authkey,authvalue);

        Assert.notNull(oauthClientInfo,"授权失败,授权数据不合法");

        OAuthRefreshToken refreshToken = oAuthService.createRefreshToken(oauthClientInfo);

        AuthInfo authInfo = refreshToken.parseAuthInfo();
        authInfo.setRedirectUri(oauthClientInfo.getRedirectUri());

        String code = OAuthCodeStore.getInstance().addAuthInfo(authInfo);

        String uri = StrUtil.appendUriParams(oauthClientInfo.getRedirectUri(),"code",code);

        return new RedirectPage(uri);
    }
}
