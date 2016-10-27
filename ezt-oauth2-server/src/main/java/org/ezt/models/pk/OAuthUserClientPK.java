package org.ezt.models.pk;

import java.io.Serializable;

/**
 * Created by wangwr on 2016/4/1.
 */
public class OAuthUserClientPK implements Serializable {

    private String openid;

    private String clientId;

    public OAuthUserClientPK() {
    }

    private OAuthUserClientPK(String openid, String clientId) {
        this.openid = openid;
        this.clientId = clientId;
    }

    public static OAuthUserClientPK valueOf(String openid, String clientId){
        return new OAuthUserClientPK(openid,clientId);
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
