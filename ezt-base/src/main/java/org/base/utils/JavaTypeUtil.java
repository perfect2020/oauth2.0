package org.base.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangwr on 2016.3.24.
 */
public class JavaTypeUtil {

    public static final Set<String> PRIMITIVE_NORMAL_TYPES = new HashSet<String>();

    static {
        PRIMITIVE_NORMAL_TYPES.add("java.lang.String");
        PRIMITIVE_NORMAL_TYPES.add("java.lang.Integer");
        PRIMITIVE_NORMAL_TYPES.add("java.lang.Long");
        PRIMITIVE_NORMAL_TYPES.add("java.lang.Float");
        PRIMITIVE_NORMAL_TYPES.add("java.lang.Double");
        PRIMITIVE_NORMAL_TYPES.add("java.lang.Boolean");
        PRIMITIVE_NORMAL_TYPES.add("java.util.Date");
    }

    public static boolean isNormal(Class<?> clazz){
        return PRIMITIVE_NORMAL_TYPES.contains(clazz.getName());
    }

    public static boolean isDate(Class<?> clazz){
        return "java.util.Date".equals(clazz.getName());
    }



}
