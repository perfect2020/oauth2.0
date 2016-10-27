package org.oauth2.server.pluging;

import java.util.Map;

/**
 * 请求获取参数
 * Created by wangwr on 2016/3/30.
 */
public interface Request {

    /**
     *  获取参数对于的value
     * @param name
     * @return
     */
    String getParameter(String name);

    /**
     *  获取参数集合
     * @return
     */
    Map<String, String> getParameterMap();

    /**
     * 获取头对应的value
     * @param name
     * @return
     */
    String getHeader(String name);

}
