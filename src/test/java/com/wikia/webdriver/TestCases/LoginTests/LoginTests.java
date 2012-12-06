package com.wikia.webdriver.TestCases.LoginTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.WikiBasePageObject;

public class LoginTests extends TestTemplate {

	@DataProvider
	private static final Object[][] getUserCredentials() {
		return new Object[][] {
				{ Properties.userName, Properties.password, Properties.userName },
				{ Properties.userName2, Properties.password2,
						Properties.userName2 },
				{ Properties.userNameWithUnderScore,
						Properties.passwordWithUnderScore,
						Properties.userNameWithUnderScore },
				{ Properties.userNameNonLatin, Properties.passwordNonLatin,
						Properties.userNameNonLatinEncoded },
				{ Properties.userNameWithBackwardSlash,
						Properties.passwordWithBackwardSlash,
						Properties.userNameWithBackwardSlashEncoded },
				{ Properties.userNameLong, Properties.passwordLong,
						Properties.userNameLong },
				{ Properties.userNameStaff, Properties.passwordStaff,
						Properties.userNameStaff }, };
	}

	@Test(dataProvider = "getUserCredentials", groups = { "Login_001", "Login" })
	public void Login_001_SpecialPage(String userName, String password,
			String userNameEnc) {
		PageObjectLogging.log("Login_001_SpecialPage", userName, true);
		CommonFunctions.logOut(driver);
		WikiBasePageObject base = new WikiBasePageObject(driver, Global.DOMAIN);
		base.openWikiPage();
		CommonFunctions.logInSpecialUserLogin(userName, password, userNameEnc);
		CommonFunctions.logOut(driver);
	}

	@Test(dataProvider = "getUserCredentials", groups = { "Login_002", "Login" })
	public void Login_002_DropDown(String userName, String password,
			String userNameEnc) {
		PageObjectLogging.log("Login_002_DropDown", userName, true);
		CommonFunctions.logOut(driver);
		WikiBasePageObject base = new WikiBasePageObject(driver, Global.DOMAIN);
		base.openWikiPage();
		CommonFunctions.logInDropDown(userName, password, userNameEnc);
		CommonFunctions.logOut(driver);
	}

	@Test(groups = { "Login_003", "Login" })
	public void Login_003_Facebook_dropDown() {
		CommonFunctions.logOut(driver);
//		driver.get("http://www.facebook.com/");
		WikiBasePageObject base = new WikiBasePageObject(driver, Global.DOMAIN);
//		if (base.waitForElementByCss("div#userNavigationLabel"));
//		try{
//			Global.LOG_ENABLED = false;
//			driver.findElement(By.cssSelector("div#userNavigationLabel")).isDisplayed();
//			Global.LOG_ENABLED = true;
//			WebElement fbdd = driver.findElement(By.cssSelector("div#userNavigationLabel"));
//			base.waitForElementByElement(fbdd);
//			fbdd.click();
//			WebElement fblo = driver.findElement(By.cssSelector("label input[value='Log Out']"));
//			base.waitForElementByElement(fblo);
//			fblo.click();
//			WebElement lonot = driver.findElement(By.cssSelector("input#email"));
//			base.waitForElementByElement(lonot);
//			base.openWikiPage();
//			CommonFunctions.logInDropDownFB();
//		}
//		catch(Exception e)
//		{
//			Global.LOG_ENABLED = true;
			base.openWikiPage();
			CommonFunctions.logInDropDownFB();			
//		}
		CommonFunctions.logOut(driver);
	}
}
