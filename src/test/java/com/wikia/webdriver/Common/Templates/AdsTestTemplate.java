package com.wikia.webdriver.Common.Templates;

import com.wikia.webdriver.Common.DriverProvider.DriverProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.browsermob.proxy.ProxyServer;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsTestTemplate extends TestTemplate {

    private static HashMap adsConfiguration;
    private static DesiredCapabilities adCap;
    private static GeoEdgeProxyServer adServer;

    /**
     * Start browser with configured desired capabilities and start logging
     *
     * @param Method method
     */
    @BeforeMethod(alwaysRun=true)
    @Override
    public void start(Method method) {
        try {
            GeoEdgeProxy country = method.getAnnotation(GeoEdgeProxy.class);
            adServer = new GeoEdgeProxyServer(
                country.country(), 4444
            );
            adServer.runGeoEdgeServer();
            adCap = setServerCaps(adServer);
            startBrowserWithCapabilities(adCap);
            PageObjectLogging.startLoggingMethod(
                getClass().getSimpleName().toString(), method.getName()
            );
        } catch (Exception ex) {
            Logger.getLogger(AdsTestTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
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