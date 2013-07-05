package com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Core.MailFunctions;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class SignUpPageObject extends BasePageObject {

	public SignUpPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "form#WikiaSignupForm input[name='username']")
	private WebElement userNameField;
	@FindBy(css = "form#WikiaSignupForm input[name='email']")
	private WebElement emailField;
	@FindBy(css = "form#WikiaSignupForm input[name='password']")
	private WebElement passwordField;
	@FindBy(css = "form#WikiaSignupForm select[name='birthmonth']")
	private WebElement birthMonthField;
	@FindBy(css = "form#WikiaSignupForm select[name='birthday']")
	private WebElement birthDayField;
	@FindBy(css = "form#WikiaSignupForm select[name='birthyear']")
	private WebElement birthYearField;
	@FindBy(css = "input#wpCaptchaWord")
	private WebElement blurryWordField;
	@FindBy(css = "div.fancy-captcha input[type='hidden']")
	private WebElement blurryWordHidden;
	@FindBy(css = "input.big")
	private WebElement createAccountButton;
	
	 @FindBy(xpath="//div[@class='error-msg' and contains(text(), 'Oops, please fill in the username field.')]")
	 private WebElement emptyUserNameValidationError;
	 @FindBy(xpath="//div[@class='error-msg' and contains(text(), 'Someone already has this username. Try a different one!')]")
	 private WebElement occupiedUserNameValidationError;
	 @FindBy(css=".input-group.captcha.error.required .error-msg")
	 private WebElement WrongBlurryWordValidationError;
	// @FindBy(css="")
	// private WebElement;
	// @FindBy(css="")
	// private WebElement;
	// @FindBy(css="")
	// private WebElement;
	// @FindBy(css="")
	// private WebElement;
	// @FindBy(css="")
	// private WebElement;

	/**
	 * @author Karol Kujawiak
	 */
	public void openSignUpPage()
	{
		getUrl(Global.DOMAIN+"wiki/Special:UserSignup");
		waitForElementByElement(blurryWordField);
		PageObjectLogging.log("openSignUpPage ", "Sign up page opened " +driver.getCurrentUrl(), true, driver);
	}
	
	/**
	 * @author Karol Kujawiak
	 * @param userName
	 */
	public void typeInUserName(String userName)
	{
		userNameField.sendKeys(userName);
		userNameField.sendKeys(Keys.TAB);
		PageObjectLogging.log("typeInUserName ", "User name field populated " +userName, true, driver);
	}
	
	
	/**
	 * @author Karol Kujawiak
	 */
	public void typeInEmail(String email)
	{
		emailField.sendKeys(email);
		PageObjectLogging.log("typeInEmail ", "Email field populated", true, driver);
	}
	
	
	/**
	 * @author Karol Kujawiak
	 * @param password
	 */
	 
	public void typeInPassword(String password)
	{
		passwordField.sendKeys(password);
		PageObjectLogging.log("typeInPassword ", "Password field populated", true, driver);
	}
	
	/**
	 * @author Karol Kujawiak
	 * @param month
	 * @param day
	 * @param year
	 */
	public void enterBirthDate(String month, String day, String year)
	{
			Select m = new Select(birthMonthField);
			Select d = new Select(birthDayField);
			Select y = new Select(birthYearField);
			m.selectByVisibleText(month);
			d.selectByVisibleText(day);
			y.selectByVisibleText(year);
			d.selectByVisibleText(day);
			y.selectByVisibleText(year);
			m.selectByVisibleText(month);
			PageObjectLogging.log("enterBirthDate ", "Birth date selected", true, driver);			
		}
	
	
	/**
	 * @author Karol Kujawiak
	 */
	public void enterBlurryWord()
	{
		String word = getWordFromCaptcha();
		blurryWordField.sendKeys(word);
		PageObjectLogging.log("enterBlurryWord ", "Blurry word field populated", true, driver);
	}

	public void enterWrongBlurryWord(){
		String word = getTimeStamp();
		blurryWordField.sendKeys(word);
		PageObjectLogging.log("enterWrongBlurryWord ", "Blurry word field populated incorrectly", true);
	}

	public void clickCreateAccountButton(){
		waitForElementClickableByElement(createAccountButton);
		createAccountButton.click();
	}
	
	
	/**
	 * @author Karol Kujawiak
	 */
	public AlmostTherePageObject submit(String email, String password)
	{
		MailFunctions.deleteAllMails(email, password);
		clickAndWait(createAccountButton);
		PageObjectLogging.log("submit ", "Submit button clicked", true, driver);
		return new AlmostTherePageObject(driver);
	}
	
	/**
	 * @author Karol Kujawiak
	 */
	public void verifyEmptyUserNameValidation()
	{
		waitForElementByElement(emptyUserNameValidationError);
		PageObjectLogging.log("verifyUserNameValidation ", "empty user name validation verified", true);
	}
	
	/**
	 * @author Karol Kujawiak
	 */
	public void verifyOccupiedUserNameValidation()
	{
		waitForElementByElement(occupiedUserNameValidationError);
		PageObjectLogging.log("verifyUserNameValidation ", "occupied user name validation verified", true);
	}

	public void verifyWrongBlurryWordValidation(){
		waitForElementByElement(WrongBlurryWordValidationError);
		PageObjectLogging.log(
			"verifyWrongBlurryWordValidation ", 
			"wrong blurry word validation verified", 
			true, driver
		);
	}
	
	/**
	 * @author Karol Kujawiak
	 */
	private String getWordFromCaptcha() 
	{
		try
		{
			String captchaId = CommonFunctions.getAttributeValue(blurryWordHidden, "value");
			String urlAd = Global.DOMAIN+ "wiki/Special:Captcha/image?wpCaptchaId="+ captchaId;
			URL url = new URL(urlAd);
			
			
			String md5 = md5(url.openStream());
			if (md5 == null) 
			{
				PageObjectLogging.log("getWordFromCaptcha", "mdp error", false);
			}
	
			File file = Global.CAPTCHA_FILE;
			BufferedReader in = new BufferedReader(new FileReader(file));
			String strLine;
			while ((strLine = in.readLine()) != null) 
			{
				String[] field = strLine.split(" ");
				if (field[1].equals(md5)) 
				{
					in.close();
					PageObjectLogging.log("getWordFromCaptcha", "Captcha word decoded", true);
					return field[0];
				}
			}
			in.close();
			PageObjectLogging.log("getWordFromCaptcha", "Captcha word not decoded", false);
			return null;
		}
		catch(IOException e)
		{
			PageObjectLogging.log("getWordFromCaptcha", e.toString(), false);
			e.printStackTrace();
			return null;
		} 
		
	}

	/**
	 * @author Karol Kujawiak
	 */
	private static String md5(InputStream is) 
		{
		try
		{
			String output;
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[8192];
			int read = 0;
			try {
				while ((read = is.read(buffer)) > 0) {
					digest.update(buffer, 0, read);
				}
				byte[] md5sum = digest.digest();
				BigInteger bigInt = new BigInteger(1, md5sum);
				output = String.format("%0" + (md5sum.length << 1) + "x", bigInt);
			} finally {
				is.close();
			}
			return output;
		}
		catch(NoSuchAlgorithmException e)
		{
			PageObjectLogging.log("md5", e.toString(), false);
			e.printStackTrace();
			return null;
		}
		catch(IOException e)
		{
			PageObjectLogging.log("md5", e.toString(), false);
			e.printStackTrace();
			return null;
		}
	}

}
