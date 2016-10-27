package org.base.utils.mock;

/**
 *
 * Created by wangwr on 2016.3.23.
 */
public class BooleanMockHandler implements MockHandler {
    @Override
    public <T> T mock(Class<T> clazz) {
        return (T) Boolean.valueOf((Math.random()>0.5?1:0)+"");
    }
}
