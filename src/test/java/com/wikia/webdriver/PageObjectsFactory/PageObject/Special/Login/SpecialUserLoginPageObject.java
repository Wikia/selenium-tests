package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.ApiActions;
import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialPageObject;

/**
 *
 * @author Karol 'kkarolk' Kujawiak
 *
 */

public class SpecialUserLoginPageObject extends SpecialPageObject {

	public SpecialUserLoginPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".WikiaArticle input[name='username']")
	private WebElement userName;
	@FindBy(css=".WikiaArticle input[name='password']")
	private WebElement password;
	@FindBy(css=".WikiaArticle input[name='newpassword']")
	private WebElement newPassword;
	@FindBy(css=".WikiaArticle input[name='retype']")
	private WebElement retypeNewPassword;
	@FindBy(css=".WikiaArticle input.login-button.big")
	private WebElement loginButton;
	@FindBy(css=".WikiaArticle .forgot-password")
	private WebElement forgotPasswordLink;
        @FindBy (css=".UserLogin .error-msg")
        private WebElement messagePlaceholder;

	/**
	 * Special:UserLogin user name field
	 * @param name
	 */
	private void typeInUserName(String name){
		waitForElementByElement(userName);
		userName.clear();
		userName.sendKeys(name);
		PageObjectLogging.log("typeInUserName", name+" user name typed", true, driver);
	}

	/**
	 * Special:UserLogin password field
	 * @param pass
	 */
	private void typeInPassword(String pass){
		waitForElementByElement(password);
		password.clear();
		password.sendKeys(pass);
		PageObjectLogging.log("typeInUserPassword", "password typed", true, driver);
	}

	/**
	 * Special:UserLogin for forgot password
	 * @param pass
	 */
	private void typeInNewPassword(String pass){
		waitForElementByElement(newPassword);
		newPassword.sendKeys(pass);
		PageObjectLogging.log("typeInNewPassword", "new password retyped", true, driver);
	}

	/**
	 * Special:UserLogin for forgot password
	 * @param pass
	 */
	private void retypeInNewPassword(String pass){
		waitForElementByElement(retypeNewPassword);
		retypeNewPassword.sendKeys(pass);
		PageObjectLogging.log("typeInNewPassword", "new password retyped", true, driver);
	}


	/**
	 * Special:UserLogin login button
	 */
	private void clickLoginButton(){
		waitForElementByElement(loginButton);
		loginButton.click();
		PageObjectLogging.log("clickLoginButton", "login button clicked", true, driver);
	}

        /**
	 * Special:UserLogin forgot password
	 */
	private void clickForgotPasswordLink(){
		waitForElementByElement(forgotPasswordLink);
		clickAndWait(forgotPasswordLink);
	}

	/**
	 * Special:UserLogin
	 * use if user is not on Special:UserLogin page
	 * and verification after logging in is needed
	 *
	 * @param name
	 * @param pass
	 */
	public void loginAndVerify(String name, String pass){
		openSpecialUserLogin();
		login(name, pass);
		verifyUserLoggedIn(name);
	}

	public void loginAndVerifyOnWiki(String name, String pass, String url) {
		openSpecialUserLoginOnWiki(url);
		login(name, pass);
		verifyUserLoggedIn(name);
	}

	/**
	 * Special:UserLogin
	 * use if user is on Special:UserLogin page
	 * and no verification after logging in is needed
	 *
	 * @param name
	 * @param pass
	 */
	public void login(String name, String pass){
		typeInUserName(name);
		typeInPassword(pass);
		clickLoginButton();
	}

	/**
	 * Special:UserLogin forgot password
	 * @param name
	 */
	public void remindPassword(String name){
		Assertion.assertEquals(
			ApiActions.apiActionForgotPasswordResponse,
			resetForgotPasswordTime(name));
		typeInUserName(name);
		clickForgotPasswordLink();
	}

	/**
	 * opens Special:UserLogin page
	 */
	public void openSpecialUserLogin(){
		getUrl(Global.DOMAIN+ URLsContent.specialUserLogin);
		PageObjectLogging.log("openSpecialUserLogin", "Special:UserLogin page opened", true, driver);
	}

	public String setNewPassword() {
		String password = Properties.password + getTimeStamp();
		typeInNewPassword(password);
		retypeInNewPassword(password);
		clickLoginButton();
		PageObjectLogging.log(
			"setNewPassword",
			"new password is set",
			true, driver
		);
		return password;
	}

	public void openSpecialUserLoginOnWiki(String url) {
		getUrl(url + URLsContent.specialUserLogin);
		PageObjectLogging.log(
			"SpecialUserLoginOnWiki",
			"Special:UserLogin opened on: " + url,
			true
		);
	}

	public void verifyMessageAboutNewPassword(String userName) {
		waitForElementByElement(messagePlaceholder);
		String message = PageContent.newPasswordSentMessage.replace("%userName%", userName);
		waitForTextToBePresentInElementByElement(messagePlaceholder, message);
		PageObjectLogging.log(
			"newPasswordSentMessage",
			"Message about new password sent present",
			true, driver
		);
	}

	public String logInCookie(String userName, String password) {
		if (!Global.LOGIN_BY_COOKIE) {
			SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
			login.loginAndVerify(userName, password);
			return null;
		} else {
			try {
				DefaultHttpClient httpclient = new DefaultHttpClient();

				HttpPost httpPost = new HttpPost(Global.DOMAIN + "api.php");
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();

				nvps.add(new BasicNameValuePair("action", "login"));
				nvps.add(new BasicNameValuePair("format", "xml"));
				nvps.add(new BasicNameValuePair("lgname", userName));
				nvps.add(new BasicNameValuePair("lgpassword", password));

				httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

				HttpResponse response = null;

				response = httpclient.execute(httpPost);

				HttpEntity entity = response.getEntity();
				String xmlResponse = null;

				xmlResponse = EntityUtils.toString(entity);

				String[] xmlResponseArr = xmlResponse.split("\"");
				String token = xmlResponseArr[5];

				// System.out.println(token);

				while (xmlResponseArr.length < 11) {// sometimes first request
					// does
					// not contain full
					// information,
					// in such situation
					// xmlResponseArr.length <
					// 11
					List<NameValuePair> nvps2 = new ArrayList<NameValuePair>();

					nvps2.add(new BasicNameValuePair("action", "login"));
					nvps2.add(new BasicNameValuePair("format", "xml"));
					nvps2.add(new BasicNameValuePair("lgname", userName));
					nvps2.add(new BasicNameValuePair("lgpassword", password));
					nvps2.add(new BasicNameValuePair("lgtoken", token));

					httpPost.setEntity(new UrlEncodedFormEntity(nvps2,
							HTTP.UTF_8));

					response = httpclient.execute(httpPost);

					entity = response.getEntity();

					xmlResponse = EntityUtils.toString(entity);

					xmlResponseArr = xmlResponse.split("\"");
				}

				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("$.cookie('" + xmlResponseArr[11]
						+ "_session', '" + xmlResponseArr[13]
						+ "', {'domain': 'wikia.com'})");
				js.executeScript("$.cookie('" + xmlResponseArr[11]
						+ "UserName', '" + xmlResponseArr[7]
						+ "', {'domain': 'wikia.com'})");
				js.executeScript("$.cookie('" + xmlResponseArr[11]
						+ "UserID', '" + xmlResponseArr[5]
						+ "', {'domain': 'wikia.com'})");
				js.executeScript("$.cookie('" + xmlResponseArr[11]
						+ "Token', '" + xmlResponseArr[9]
						+ "', {'domain': 'wikia.com'})");
				try {
					driver.get(Global.DOMAIN + "Special:Random");
				} catch (TimeoutException e) {
					PageObjectLogging.log("loginCookie",
							"page timeout after login by cookie", true);
				}

				driver.findElement(By
						.cssSelector(".AccountNavigation a[href*='User:"
								+ userName + "']"));// only for verification
				PageObjectLogging.log("loginCookie",
						"user was logged in by cookie", true, driver);
				return xmlResponseArr[11];
			} catch (UnsupportedEncodingException e) {
				PageObjectLogging.log("logInCookie",
						"UnsupportedEncodingException", false);
				return null;
			} catch (ClientProtocolException e) {
				PageObjectLogging.log("logInCookie", "ClientProtocolException",
						false);
				return null;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}

	}
}
