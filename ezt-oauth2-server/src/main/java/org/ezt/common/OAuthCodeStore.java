package org.ezt.common;

import org.base.utils.RandomUtil;
import org.base.utils.TimerHashMap;
import org.oauth2.server.models.AuthInfo;

/**
 * 授权码存储
 * Created by wangwr on 2016/4/5.
 */
public class OAuthCodeStore {

    private static OAuthCodeStore oAuthCodeStore;

    private OAuthCodeStore(){
        checkCodeStoreNull();
    }

    public synchronized static OAuthCodeStore getInstance(){
        if(oAuthCodeStore == null){
            oAuthCodeStore = new OAuthCodeStore();
        }
        return oAuthCodeStore;
    }

    private TimerHashMap<String ,AuthInfo> codeStore = null;


    protected void checkCodeStoreNull(){
        if(codeStore==null){
            codeStore = new TimerHashMap<String, AuthInfo>();
        }
    }


    protected TimerHashMap<String,AuthInfo> getCodeStore(){
        checkCodeStoreNull();
        return codeStore;
    }

    public String addAuthInfo(AuthInfo authInfo){
        String code = RandomUtil.randomWords(RandomUtil.RandomType.DIGITAL,32);
        authInfo.setCode(code);
        getCodeStore().put(code,authInfo,60);//60秒自动过期
        return code;
    }

    public AuthInfo getAuthInfo(String code){
        return getCodeStore().get(code);
    }
}
