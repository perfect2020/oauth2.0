package org.ezt.common;

import org.base.utils.*;
import org.ezt.views.OauthClientInfo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by wangwr on 2016/4/8.
 */
public class OAuthContentStore {

    private static OAuthContentStore oauthContentStore;

    private static final String PRE_KEY = "eztoauthkey";

    private OAuthContentStore(){
        authMap = new TimerHashMap<String, String>();
    }

    public synchronized static OAuthContentStore getInstance(){
        if(oauthContentStore == null){
            oauthContentStore = new OAuthContentStore();
        }
        return oauthContentStore;
    }

    private TimerHashMap<String ,String> authMap = null;

    public EncryptClient encrypt(OauthClientInfo clientInfo){

        String str = JsonUtil.toJson(clientInfo);

        String authkey = RandomUtil.randomWords(RandomUtil.RandomType.MIXING,32);

        String authvalue = DesUtil.encrypt(str,PRE_KEY + authkey);

        authMap.put(authkey, MD5Util.MD5(authvalue),3*60);//60秒过期

        return new EncryptClient(authkey,authvalue);
    }

    public OauthClientInfo decrypt(String authkey,String authvalue){
        //只能验证一次,失效就需要重新登陆
        if(authMap.containsKey(authkey)&&authMap.getAndRemove(authkey).equals(MD5Util.MD5(authvalue))){
            //验证通过,进行解密
            return JsonUtil.toBean(DesUtil.decrypt(authvalue,PRE_KEY+authkey),OauthClientInfo.class);

        }
        return null;
    }

    public static class EncryptClient{

        private String authkey;

        private String authvalue;

        public EncryptClient(String authkey, String authvalue) {
            this.authkey = authkey;
            this.authvalue = authvalue;
        }

        public String getAuthkey() {
            return authkey;
        }

        public void setAuthkey(String authkey) {
            this.authkey = authkey;
        }

        public String getAuthvalue() {
            return authvalue;
        }

        public void setAuthvalue(String authvalue) {
            this.authvalue = authvalue;
        }

        public Map<String,Object> getMap(){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("authkey",this.authkey);
            map.put("authvalue",this.authvalue);
            return map;
        }
    }

}
