package org.base.constants;

/**
 * 执行的状态吗
 * Created by wangwr on 2016/4/7.
 */
public enum ExecuteStatus {

    invalid_param("00000001","参数不合法"),

    invalid_token("00000002","无效的token"),

    expired_token("00000003","token过期"),

    unsupported_grant_type("00000004","不支持的授权方式"),

    unknown_client_id("00000005","未知的client_id"),

    invalid_client_secret("00000006","无效的client_secret"),

    unknown_user_account("00000007","账号不存在"),

    password_error("00000008","密码错误"),

    param_is_null("00000009","参数不能为空"),

    execute_failure("00000010","执行失败"),

    post_invalid_data("00000011","失效或无效的数据"),

    invalid_code("00000012","无效的code"),

    client_id_not_match("00000013","不匹配的client_id"),

    redirect_uri_not_match("00000014","跳转地址不匹配"),

    invalid_refresh_token("00000015","无效的refresh_token"),

    resource_not_exist("00000016","资源不存在"),

    expired_refresh_token("00000017","refresh_token过期"),

    invalid_redirect_uri("00000018","无效的redirect_uri"),

    server_busy("99999999","服务器繁忙,请稍后再试");


    private String code;

    private String description;

    ExecuteStatus(String code, String description){
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
