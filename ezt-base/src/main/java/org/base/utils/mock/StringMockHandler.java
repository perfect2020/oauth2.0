package org.base.utils.mock;

import org.base.utils.RandomUtil;

/**
 * Created by Administrator on 2016.3.23.
 */
public class StringMockHandler implements MockHandler {
    @Override
    public <T> T mock(Class<T> clazz) {
        return (T) RandomUtil.randomWords(RandomUtil.RandomType.STRING,6);
    }
}
