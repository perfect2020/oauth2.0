package org.base.utils.mock;

import org.base.utils.RandomUtil;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016.3.23.
 */
public class NumberMockHandler implements MockHandler {
    @Override
    public <T> T mock(Class<T> clazz) {
        String ramd = RandomUtil.randomWords(RandomUtil.RandomType.DIGITAL,5);
        if("java.lang.Long".equals(clazz.getName())){
            return (T) Long.valueOf(ramd);
        }
        if ("java.lang.Integer".equals(clazz.getName())){
            return (T) Integer.valueOf(ramd);
        }
        if("java.lang.Float".equals(clazz.getName())){
            return (T) Float.valueOf(ramd);
        }
        if("java.lang.Double".equals(clazz.getName())){
            return (T) Double.valueOf(ramd);
        }
        if("java.math.Big".equals(clazz.getName())){
            return (T) new BigDecimal(ramd);
        }
        return (T) ramd;
    }
}
