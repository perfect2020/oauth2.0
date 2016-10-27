package org.base.runtime;

import java.text.SimpleDateFormat;

/**
 * 日期时间格式化器。
 */
public class HttpServiceDateFormat extends SimpleDateFormat {
    public HttpServiceDateFormat() {
        super(HttpServiceContext.Default_Date_Format_Pattern);
    }
}
