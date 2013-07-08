package com.wikia.webdriver.Common.Templates;


import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxyServer;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsTestTemplate extends NewTestTemplate {

    private static DesiredCapabilities adCap;
    private static GeoEdgeProxyServer adServer;

    /**
     * Start browser with configured desired capabilities and start logging
     *
     * @param Method method
     */
    @BeforeMethod(alwaysRun=true)
    @Override
    public void start(Method method, Object[] data) {
        try {
            GeoEdgeProxy country = method.getAnnotation(GeoEdgeProxy.class);
            adServer = new GeoEdgeProxyServer(
                country.country(), 4444
            );
            adServer.runGeoEdgeServer();
            adCap = getCapsWithProxyServerSet(adServer);
            startBrowserWithCapabilities(adCap);
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
}