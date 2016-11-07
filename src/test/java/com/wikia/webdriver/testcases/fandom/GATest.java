package com.wikia.webdriver.testcases.fandom;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.fandom.FandomTestTemplate;
import com.wikia.webdriver.elements.fandom.pages.HomePage;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.*;
import net.lightbody.bmp.core.json.ISO8601DateFormatter;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.proxy.CaptureType;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.o;

/**
 * Created by Liz Lee on 11/4/16.
 */
@Test(groups = {"Fandom"})
public class GATest extends FandomTestTemplate {

    @Test(groups = {"FandomGA"})
    @NetworkTrafficDump
    public void pageViewTrackingSent() {

//        // start the proxy
//        BrowserMobProxy proxy = new BrowserMobProxyServer();
//        proxy.start(0);
//
//        // get the Selenium proxy object
//        org.openqa.selenium.Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
//
//        // configure it as a desired capability
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
//
//        // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
//        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
//        // create a new HAR with the label "yahoo.com"
//        proxy.newHar("fandom-home-page");
//
//        HomePage homePageObject = new HomePage();
//        homePageObject.open();
//
//        // get the HAR data
//        Har har = proxy.getHar();
//        HarLog log = har.getLog();
//
//        List<HarEntry> entries = log.getEntries();
//        for (HarEntry entry : entries)
//        {
//            HarRequest request = entry.getRequest();
//            System.out.println("--");
//            System.out.println(request.getUrl());
//            System.out.println("--");
//        }








        networkTrafficInterceptor.startIntercepting();
        networkTrafficInterceptor.enableHarCaptureTypes(
                CaptureType.REQUEST_CONTENT,
                CaptureType.RESPONSE_CONTENT,
                CaptureType.REQUEST_BINARY_CONTENT,
                CaptureType.RESPONSE_BINARY_CONTENT
        );
        HomePage homePageObject = new HomePage();

        homePageObject.open();
        Har har = networkTrafficInterceptor.getHar();

        File harFile = new File("/Users/liz_lux/fandom-har.har");
        try {
            har.writeTo(harFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        HarLog log = har.getLog();

        List<HarEntry> entries = log.getEntries();
        for (HarEntry entry : entries)
        {
            HarRequest request = entry.getRequest();
            System.out.println("--");
            System.out.println(request.getUrl());
            System.out.println("--");
        }

        Assertion.assertTrue(true, "True is true");

    }
}
