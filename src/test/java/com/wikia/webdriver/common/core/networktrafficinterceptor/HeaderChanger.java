package com.wikia.webdriver.common.core.networktrafficinterceptor;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.http.BrowserMobHttpRequest;
import net.lightbody.bmp.proxy.http.RequestInterceptor;

import com.wikia.webdriver.common.logging.PageObjectLogging;

/**
 * Created by Ludwik on 2015-04-03.
 */
public class HeaderChanger implements RequestInterceptor {

    private String headerName;
    private String newValue;

    public HeaderChanger(String headerName, String newValue) {
        this.headerName = headerName;
        this.newValue = newValue;
    }

    @Override public void process(BrowserMobHttpRequest browserMobHttpRequest, Har har) {
        browserMobHttpRequest.getMethod().removeHeaders(headerName);
        try {
            browserMobHttpRequest.getMethod().addHeader(
                headerName,
                newValue
            );
        } catch (Exception e) {
            PageObjectLogging.log("changeHeader", e.getMessage(), false);
        }
    }
}
