package org.base.utils.mock;

import java.lang.reflect.Field;

/**
 * 实体mock数据
 * Created by wangwr on 2016.3.23.
 */
public class EntityMockHandler implements MockHandler {

    @Override
    public <T> T mock(Class<T> clazz) {
        try {
            T target = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields){
                Class<?> fc = (Class<?>) field.getGenericType();
                MockHandler handler = MockHandlerFactory.getMockHandler(fc);
                field.setAccessible(true);
                field.set(target,handler.mock(fc));
            }
            return target;
        } catch (Exception e) {
            throw new RuntimeException("mock数据异常",e);
        }
    }
}
