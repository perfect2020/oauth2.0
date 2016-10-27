package org.ezt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.base.utils.RandomUtil;
import org.base.utils.beannote.Note;
import org.oauth2.server.models.AuthInfo;

import javax.persistence.*;
import java.util.Date;

/**
 * 授权信息刷新token,过期时间比access_token的时间长
 * Created by wangwr on 2016/3/31.
 */
@Entity
@Table(name = "info_oauth_refresh_token")
public class OAuthRefreshToken {

    public static String generatedToken(){
        return RandomUtil.randomWords(RandomUtil.RandomType.MIXING,64);
    }

    public static Long expiresInTime(){
        return Long.valueOf(7*24*60*60);
    }

    @Id
    @Note("标识id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Note("用户的唯一标识")
    @Column(name = "openid",length = 48,nullable = false)
    private String openid;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "openid", insertable = false, updatable = false, nullable = false)
    private OAuthUser oauthUser;

    @JsonProperty("client_id")
    @Column(name = "client_id",length = 64,nullable = false)
    private String clientId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", insertable = false, updatable = false, nullable = false)
    private OAuthClient oAuthClient;

    @Note("该凭证可以换取新的凭证")
    @Column(name = "refresh_token",length = 64,nullable = false,unique = true)
    private String refreshToken;

    @Note("创建时间")
    @Column(name = "create_time",nullable = false,updatable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createTime;

    @Note("过期时间(秒)")
    @Column(name = "expires_in",nullable = false)
    private Long expiresIn;

    @Note("访问权限")
    @Column(name = "scope",nullable = false)
    private String scope;

    public AuthInfo parseAuthInfo(){
        AuthInfo authInfo = new AuthInfo();
        authInfo.setRefreshToken(this.refreshToken);
        authInfo.setUserId(this.getOpenid());
        authInfo.setClientId(this.clientId);
        authInfo.setScope(this.scope);
        return authInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public OAuthUser getOauthUser() {
        return oauthUser;
    }

    public void setOauthUser(OAuthUser oauthUser) {
        this.oauthUser = oauthUser;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public OAuthClient getoAuthClient() {
        return oAuthClient;
    }

    public void setoAuthClient(OAuthClient oAuthClient) {
        this.oAuthClient = oAuthClient;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @PrePersist
    private void onCreate() {
        this.setCreateTime(new Date());
    }
}
