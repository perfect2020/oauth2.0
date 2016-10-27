package org.base.utils.mock;

import java.util.Date;

/**
 * Created by wangwr on 2016.3.23.
 */
public class DateMockHandler implements MockHandler {
    @Override
    public <T> T mock(Class<T> clazz) {
        return (T) new Date();
    }
}
