package org.base.utils.mock;

/**
 * Created by wangwr on 2016.3.23.
 */
public interface MockHandler {

    <T> T mock(Class<T> clazz);
}
