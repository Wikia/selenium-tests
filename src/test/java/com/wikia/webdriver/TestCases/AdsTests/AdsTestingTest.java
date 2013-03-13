package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Templates.AdsTestTemplate;
import com.wikia.webdriver.Common.Templates.GeoEdgeProxy;
import org.testng.annotations.Test;


/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsTestingTest extends AdsTestTemplate {

    @GeoEdgeProxy(country="US")
    @Test(groups={"Proxy"})
    public void TestUS() {
        driver.get("http://gta.wikia.com");
        try {
            Thread.sleep(100000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GeoEdgeProxy(country="DE")
    @Test(groups={"Proxy"})
    public void TestDE() {
        driver.get("http://gta.wikia.com");
        try {
            Thread.sleep(100000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
