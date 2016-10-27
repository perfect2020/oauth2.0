package org.ezt.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.base.utils.beannote.Note;

/**
 * 用户基本信息
 * Created by wangwr on 2016/4/8.
 */
public class UserInfo {

    @Note("用户唯一标石")
    @JsonProperty("openid")
    private String openid;

    @Note("用户的昵称")
    @JsonProperty("nick_name")
    private String nickName;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
