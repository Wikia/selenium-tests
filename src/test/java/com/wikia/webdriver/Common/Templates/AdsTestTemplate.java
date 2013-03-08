package com.wikia.webdriver.Common.Templates;

import com.wikia.webdriver.Common.DriverProvider.DriverProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.AdsProperties;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.browsermob.proxy.ProxyServer;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsTestTemplate extends TestTemplate {

    private static HashMap adsConf;
    private static DesiredCapabilities adCap;
    private static ProxyServer adServer;

    /**
     * Start browser with configured desired capabilities and start logging
     *
     * @param Method method
     */
    @BeforeMethod(alwaysRun=true)
    @Override
    public void start(Method method) {
        try {
            adsConf = AdsProperties.setAdsConfiguration();
        } catch (Exception e) {
            e.printStackTrace();
        }
        GeoEdgeProxy annotation = method.getAnnotation(GeoEdgeProxy.class);
        String ip = (String) adsConf.get(annotation.country());
        adServer = startServerWithOptions(ip);
        adServer = setHeadersWithGeoEdgeCredentials(adServer);
        adCap = setServerCaps(adServer);
        startBrowserWithCapabilities(adCap);
        PageObjectLogging.startLoggingMethod(
            getClass().getSimpleName().toString(), method.getName()
        );
    }

    @AfterMethod(alwaysRun=true)
    public void stopServer() {
        try {
            adServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startBrowserWithCapabilities(DesiredCapabilities caps) {
        DriverProvider.setCapabilities(caps);
        startBrowser();
    }
}