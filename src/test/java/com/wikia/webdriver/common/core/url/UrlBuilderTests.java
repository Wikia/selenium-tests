package com.wikia.webdriver.common.core.url;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.configuration.EnvType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UrlBuilderTests {

    @BeforeTest
    public void setConfig() {
        Configuration.setTestValue("env", "sandbox-s1");
        Configuration.setTestValue("forceHttps", "false");
        Configuration.setTestValue("forceLanguageInPath", "false");
        Configuration.setTestValue("forceHttps", "false");
        Configuration.setTestValue("language", "en");
    }

    @Test
    public void shouldReturnWikiUrlTest() {
        UrlBuilder urlBuilder = UrlBuilder.createUrlBuilder();
        Assert.assertEquals(urlBuilder.envType, EnvType.SANDBOX);
    }

    @Test
    public void shouldReturn() {
        Constructor<UrlBuilder> c = null;
        try {
            try {
                Constructor<?>[] test = UrlBuilder.class.getDeclaredConstructors();
                c = UrlBuilder.class.getConstructor();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            c.setAccessible(true);
            UrlBuilder urlBuilder = c.newInstance(); // Hello sailor
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void clearCustomTestProperties() {
        Configuration.clearCustomTestProperties();
        System.out.println(Configuration.getEnv());
    }
}
