package org.base.utils.mock;

import java.util.*;

/**
 * Created by wangwr on 2016.3.23.
 */
public class MockHandlerFactory {

    private static Map<String,MockHandler> mockHandlerMap = new HashMap<String, MockHandler>();

    private static final String STRING_MOCK_HANDLER = "stringMockHandler";

    private static final String NUMBER_MOCK_HANDLER = "numberMockHandler";

    private static final String DATE_MOCK_HANDLER = "dateMockHandler";

    private static final String ENTITY_MOCK_HANDLER = "entityMockHandler";

    private static final String BOOLEAN_MOCK_HANDLER = "booleanMockHandler";

    private static Set<String> numberClazz = new HashSet<String>();

    static {

        numberClazz.add("java.lang.Integer");
        numberClazz.add("java.lang.Float");
        numberClazz.add("java.math.BigDecimal");
        numberClazz.add("java.lang.Long");
        numberClazz.add("java.lang.Double");

        mockHandlerMap.put(STRING_MOCK_HANDLER,new StringMockHandler());
        mockHandlerMap.put(NUMBER_MOCK_HANDLER,new NumberMockHandler());
        mockHandlerMap.put(DATE_MOCK_HANDLER,new DateMockHandler());
        mockHandlerMap.put(ENTITY_MOCK_HANDLER,new EntityMockHandler());
        mockHandlerMap.put(BOOLEAN_MOCK_HANDLER,new BooleanMockHandler());
    }

    /**
     * 获取对于的Mock处理器
     * @param clazz
     * @return
     */
    public static MockHandler getMockHandler(Class<?> clazz){
        if("java.lang.String".equals(clazz.getName())){
            return mockHandlerMap.get(STRING_MOCK_HANDLER);
        }
        if(numberClazz.contains(clazz.getName())){
            return mockHandlerMap.get(NUMBER_MOCK_HANDLER);
        }
        if("java.lang.Boolean".equals(clazz.getName())){
            return mockHandlerMap.get(BOOLEAN_MOCK_HANDLER);
        }
        return mockHandlerMap.get(ENTITY_MOCK_HANDLER);
    }
}
