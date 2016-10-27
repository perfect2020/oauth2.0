package org.ezt.service;

import org.ezt.models.OAuthAccessToken;
import org.ezt.models.OAuthClient;
import org.ezt.models.OAuthRefreshToken;
import org.ezt.views.OauthClientInfo;
import org.ezt.views.UserInfo;
import org.oauth2.server.models.AuthInfo;

/**
 * 授权服务逻辑层
 * Created by wangwr on 2016/4/5.
 */
public interface OAuthService {

    /**
     * 获取第三方平台信息
     * @param clientId 第三方平台标识
     * @return 第三方平台信息
     */
    OauthClientInfo getClientInfo(String clientId);

    /**
     * 获取客户端信息
     * @param clientId
     * @return
     */
    OAuthClient getOauthClient(String clientId);

    /**
     * 用户登陆
     * @param userAccount 用户账号
     * @param password 用户密码
     * @return 用户openid
     */
    String userLogin(String userAccount,String password);

    /**
     * 新建或更新access_token
     * @return
     */
    OAuthAccessToken createOrUpdateAccessToken(AuthInfo authInfo);


    /**
     * 新建refresh_token
     * @param clientInfo
     * @return
     */
    OAuthRefreshToken createRefreshToken(OauthClientInfo clientInfo);

    /**
     * 查询refreshToken
     * @param refreshToken
     * @return
     */
    OAuthRefreshToken getRefreshToken(String refreshToken);


    UserInfo getUserInfo(String openid);


    OAuthAccessToken getAccessToken(String accessToken);

    OAuthAccessToken getAccessToken(Long authId);

}
