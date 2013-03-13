package com.wikia.webdriver.Common.Properties;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Core.XMLFunctions;

public class Properties {

	private static void setPropertiesManually()
	{
		Global.BROWSER = "FF";
//		Global.DOMAIN = "http://mediawiki116.karol.wikia-dev.com/";
		Global.DOMAIN = "http://preview.mediawiki119.wikia.com/";
		Global.LIVE_DOMAIN = "http://www.wikia.com/";
		Global.CONFIG_FILE = new File("c:"+File.separator+"selenium-config"+File.separator+"config.xml");
		Global.CAPTCHA_FILE = new File("c:"+File.separator+"selenium-config"+File.separator+"captcha.txt");
		Global.LOG_VERBOSE = 2;
		if (Global.DOMAIN.contains("dev"))
		{
			Global.LOGIN_BY_COOKIE = false;
		}
		else{
			Global.LOGIN_BY_COOKIE = true;
		}
		Global.LOG_ENABLED = true;
	}
	
	public static String userName;
	public static String password;
	
	public static String userName2;
	public static String password2;
	
	public static String userNameNonLatin;
	public static String userNameNonLatinEncoded;
	public static String passwordNonLatin;
	
	public static String userNameWithUnderScore;
	public static String passwordWithUnderScore;
	
	public static String userNameWithBackwardSlash;
	public static String userNameWithBackwardSlashEncoded;
	public static String passwordWithBackwardSlash;
	
	public static String userNameLong;
	public static String passwordLong;
	
	public static String email;
	public static String emailPassword;
	public static String emailQaart1;
	public static String emailPasswordQaart1;
	public static String emailQaart2;
	public static String emailPasswordQaart2;
	public static String emailQaart3;
	public static String emailPasswordQaart3;
	public static String emailQaart4;
	public static String emailPasswordQaart4;

	public static String userNameStaff;
	public static String passwordStaff;

	public static String userNameFB;
	public static String passwordFB;
        public static String emailFB;

	public static String userNameBlocked;
	public static String passwordBlocked;

        public static String geoEdgeUserName;
        public static String geoEdgeUserPass;

        private static void setVariables() {
		userName = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular.username");
		password = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular.password");
		
		userName2 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular2.username");
		password2 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular2.password");
		
		userNameNonLatin = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.nonLatin.username");
		userNameNonLatinEncoded = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.nonLatin.usernameenc");
		passwordNonLatin = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.nonLatin.password");
		
		userNameWithUnderScore = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.underscore.username");
		passwordWithUnderScore = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.underscore.password");
		
		userNameWithBackwardSlash = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.backwardslash.username");
		userNameWithBackwardSlashEncoded = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.backwardslash.usernameenc");
		passwordWithBackwardSlash = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.backwardslash.password");
		
		userNameLong = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.long.username");
		passwordLong = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.long.password");
		
		userNameStaff = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.wikiastaff.username");
		passwordStaff = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.wikiastaff.password");

		emailFB = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.facebook.email");
		passwordFB = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.facebook.password");
                userNameFB = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.facebook.username");

		email = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.generic.username");
		emailPassword = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.generic.password");

		emailQaart1 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.qawikia1.username");
		emailPasswordQaart1 =XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.qawikia1.password");
		emailQaart2 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.qawikia2.username");
		emailPasswordQaart2 =XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.qawikia2.password");
		emailQaart3 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.qawikia3.username");
		emailPasswordQaart3 =XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.qawikia3.password");
		emailQaart4 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.qawikia4.username");
		emailPasswordQaart4 =XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.qawikia4.password");
		
		userNameBlocked = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.tooManyLoginAttempts.username");
		passwordBlocked = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.tooManyLoginAttempts.password");
                geoEdgeUserName = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.AdsConfig.GeoEdgeCredentials.userName");
                geoEdgeUserPass = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.AdsConfig.GeoEdgeCredentials.password");
	
	}

        public static void setProperties() {
            Global.RUN_BY_MAVEN = "true".equals(System.getProperty("run_mvn"));
            if (Global.RUN_BY_MAVEN) {
                getPropertiesFromPom();
            } else {
                setPropertiesManually();
            }
            setVariables();
	}

	private static void getPropertiesFromPom()
	{
		Global.BROWSER = System.getProperty("browser");
		Global.CONFIG_FILE = new File(System.getProperty("config"));
		Global.CAPTCHA_FILE = new File(System.getProperty("captcha"));
		Global.DOMAIN = System.getProperty("base-address");
		Global.LIVE_DOMAIN = System.getProperty("live-domain");
		Global.LOG_VERBOSE = 2;
		if (Global.DOMAIN.contains("dev"))
		{
			Global.LOGIN_BY_COOKIE = false;
		}
		else{
			Global.LOGIN_BY_COOKIE = true;
		}
		Global.LOG_ENABLED = true; 
	}

	private static void getWikiVersion()
	{
		WebDriver versionDriver = new HtmlUnitDriver(true);
		versionDriver.get(Global.DOMAIN+"wiki/Special:Version");
		WebElement versionTable = versionDriver.findElement(By.xpath("//td[contains(text(), 'Code')]"));
		Global.WIKI_VERSION = versionTable.getText();
		versionDriver.close();
	}
}
