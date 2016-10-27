package org.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MVC接口注解
 * Created by wangwr on 2016.3.22.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface API {

    /**
     * 接口名称
     * @return
     */
    String name() default "";

    /**
     * 接口是否需要权限认证
     * @return
     */
    boolean auth() default true;


    Class<?> reqClass() default Undefined.class;

    /**
     * 接口参数
     * @return
     */
    String[] params() default {};

    /**
     * 返回实体类型
     * @return
     */
    Class<?> respClass() default Undefined.class;

    /**
     * 返回数据
     * @return
     */
    String respData() default "";

}
