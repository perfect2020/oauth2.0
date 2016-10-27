package org.ezt.service.impl;

import org.base.constants.ExecuteStatus;
import org.base.exception.RuntimeExceptionWarning;
import org.base.utils.Assert;
import org.base.utils.MD5Util;
import org.base.utils.StrUtil;
import org.ezt.models.*;
import org.ezt.repositories.*;
import org.ezt.service.OAuthService;
import org.ezt.views.OauthClientInfo;
import org.ezt.views.UserInfo;
import org.oauth2.server.models.AuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangwr on 2016/4/5.
 */
@Service
public class OAuthServiceImpl implements OAuthService {

    @Autowired
    private OAuthUserRepository oauthUserRepository;

    @Autowired
    private OAuthClientRepository oauthClientRepository;

    @Autowired
    private OAuthAccessTokenRepository grantAccessTokenRepository;

    @Autowired
    private OAuthRefreshTokenRepository oAuthRefreshTokenRepository;

    @Autowired
    private EztUserRepository eztUserRepository;

    @Override
    public OAuthClient getOauthClient(String clientId) {
        OAuthClient oAuthClient = oauthClientRepository.findOne(clientId);
        if(oAuthClient==null){
            throw new RuntimeExceptionWarning(ExecuteStatus.unknown_client_id);
        }
        return oAuthClient;
    }

    @Override
    public OauthClientInfo getClientInfo(String clientId) {
        return new OauthClientInfo(getOauthClient(clientId));
    }

    @Override
    public String userLogin(String userAccount, String password) {
        //验证密码
        Assert.notEmpty(userAccount,"登陆账号不能为空");

        Assert.notEmpty(password,"登陆密码不能为空");

        EztUser eztUser = eztUserRepository.findByMobileOrNumber(userAccount);

        Assert.expr(StrUtil.isNull(eztUser),ExecuteStatus.unknown_user_account);

        String md5Password = MD5Util.MD5(password);
        Assert.expr(!(eztUser.getEuPassword().equals(md5Password)),ExecuteStatus.password_error);

        OAuthUser oauthUser = oauthUserRepository.findByUserId(eztUser.getId());

        //不存在则生成新的openid与用户进行绑定
        if(StrUtil.isNull(oauthUser)){
            oauthUser=createOauthUser(eztUser);
        }
        return oauthUser.getOpenid();
    }

    @Override
    public OAuthAccessToken createOrUpdateAccessToken(AuthInfo authInfo) {
        OAuthAccessToken token = new OAuthAccessToken();
        token.setClientId(authInfo.getClientId());
        token.setRefreshToken(authInfo.getRefreshToken());
        token.setOpenid(authInfo.getUserId());
        token.setAccessToken(OAuthAccessToken.generatedToken());
        token.setExpiresIn(OAuthAccessToken.expiresInTime());
        token.setScope(authInfo.getScope());
        grantAccessTokenRepository.saveAndFlush(token);
        return token;
    }

    @Override
    public OAuthRefreshToken createRefreshToken(OauthClientInfo clientInfo) {

        OAuthRefreshToken oauthRefreshToken = new OAuthRefreshToken();
        oauthRefreshToken.setClientId(clientInfo.getClientId());
        oauthRefreshToken.setOpenid(clientInfo.getOpenid());
        oauthRefreshToken.setRefreshToken(OAuthRefreshToken.generatedToken());
        oauthRefreshToken.setExpiresIn(OAuthRefreshToken.expiresInTime());
        oauthRefreshToken.setScope(clientInfo.getScope());

        return oAuthRefreshTokenRepository.saveAndFlush(oauthRefreshToken);
    }

    @Override
    public OAuthRefreshToken getRefreshToken(String refreshToken) {
        return oAuthRefreshTokenRepository.findByRefreshToken(refreshToken);
    }

    @Override
    public UserInfo getUserInfo(String openid) {

        Assert.notEmpty(openid,"openid不能为空");

        OAuthUser user = oauthUserRepository.findOne(openid);
        Assert.expr(StrUtil.isNull(user),ExecuteStatus.resource_not_exist,"不存在该用户");

        EztUser eztUser = user.getEztUser();

        Assert.expr(StrUtil.isNull(eztUser),ExecuteStatus.execute_failure,"用户信息获取失败");

        UserInfo userInfo = new UserInfo();

        userInfo.setOpenid(user.getOpenid());

        userInfo.setNickName(eztUser.getEuNickName()==null?"":eztUser.getEuNickName());

        return userInfo;
    }

    @Override
    public OAuthAccessToken getAccessToken(String accessToken) {
        return grantAccessTokenRepository.findByAccessToken(accessToken);
    }

    @Override
    public OAuthAccessToken getAccessToken(Long authId) {
        return grantAccessTokenRepository.findOne(authId);
    }

    /**
     * 新建openid与原来的用户进行绑定
     * @param eztUser
     * @return
     */
    private OAuthUser createOauthUser(EztUser eztUser){
        OAuthUser oauthUser = new OAuthUser();
        oauthUser.setOpenid(OAuthUser.generatedOpenid());
        oauthUser.setUserId(eztUser.getId());
        oauthUserRepository.saveAndFlush(oauthUser);
        return oauthUser;
    }
}
