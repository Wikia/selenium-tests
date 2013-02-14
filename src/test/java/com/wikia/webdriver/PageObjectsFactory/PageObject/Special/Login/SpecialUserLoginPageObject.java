package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login;

import org.apache.tools.ant.taskdefs.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialPageObject;

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
		clickAndWait(loginButton);
		PageObjectLogging.log("clickLoginButton", "login button clicked", true, driver);
	}
	
	/**
	 * checks user name on main menu
	 * @param name
	 */
	public void verifyUserIsLoggedIn(String name){
		waitForElementByCss(".AccountNavigation a[href*='"
				+ name + "']");
		PageObjectLogging.log("verifyUserIsLoggedIn", name + "user logged in successfully", true, driver);
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
	 * @param name
	 * @param pass
	 */
	public void login(String name, String pass){
		typeInUserName(name);
		typeInPassword(pass);
		clickLoginButton();
	}
	
	/**
	 * Special:UserLogin reset password 
	 * @param name
	 * @param pass
	 */
	public void resetPassword(String pass){
		typeInNewPassword(pass);
		retypeInNewPassword(pass);
		clickLoginButton();
	}
	
	/**
	 * 	/**
	 * Special:UserLogin forgot password
	 * @param name
	 */
	public void forgotPassword(String name){
		typeInUserName(name);
		clickForgotPasswordLink();
		waitForElementByXPath("//div[@class='error-msg' and contains(text(), \"We've sent a new password to the email address for "+name+".\")]");
	}
	
	/**
	 * opens Special:UserLogin page
	 */
	public void openSpecialUserLogin(){
		getUrl(Global.DOMAIN+"wiki/Special:UserLogin");
		PageObjectLogging.log("openSpecialUserLogin", "Special:UserLogin page opened", true, driver);
	}
}
