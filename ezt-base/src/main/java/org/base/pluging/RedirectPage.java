package org.base.pluging;

/**
 * Created by wangwr on 2016/4/7.
 */
public class RedirectPage {

    private String uri;

    public RedirectPage(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
