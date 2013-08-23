package com.wikia.webdriver.Common.Templates;


import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxyServer;
import com.wikia.webdriver.Common.DriverProvider.NewDriverProvider;
import java.lang.reflect.Method;
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
	private boolean isGeoEdgeSet = true;

	/**
     * Start browser with configured desired capabilities and start logging
     *
     * @param Method method
     */
	@BeforeMethod(alwaysRun=true)
	@Override
	public void start(Method method, Object[] data) {
		try {
			if (method.getAnnotation(GeoEdgeProxy.class) != null) {
				GeoEdgeProxy country = method.getAnnotation(GeoEdgeProxy.class);
				adServer = new GeoEdgeProxyServer(
					country.country(), 4444
				);
				adServer.runGeoEdgeServer();
				adCap = getCapsWithProxyServerSet(adServer);
				startBrowserWithCapabilities(adCap);
			} else {
				isGeoEdgeSet = false;
				startBrowser();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun=true)
	public void stopServer() {
		if (isGeoEdgeSet) {
			try {
				adServer.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void startBrowserWithCapabilities(DesiredCapabilities caps) {
		NewDriverProvider.setDriverCapabilities(caps);
		startBrowser();
	}
}