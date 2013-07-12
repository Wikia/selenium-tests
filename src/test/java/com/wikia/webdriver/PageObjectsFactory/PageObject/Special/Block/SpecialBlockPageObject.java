package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Block;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class SpecialBlockPageObject extends WikiBasePageObject{

	@FindBy(xpath="//h1[contains(text(), 'Block')]")
	private WebElement blockPageHeader;
	@FindBy(css="input[name='wpTarget']")
	private WebElement userNameField;
	@FindBy(css="select#mw-input-wpExpiry")
	private WebElement expiry;
	@FindBy(css=".mw-htmlform-submit")
	private WebElement blockButton;

	public SpecialBlockPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public SpecialBlockPageObject openSpecialBlockPage(){
		getUrl(Global.DOMAIN+"wiki/Special:Block");
		waitForElementByElement(blockPageHeader);
		Assertion.assertEquals(Global.DOMAIN+"wiki/Special:Block", driver.getCurrentUrl());
		return new SpecialBlockPageObject(driver);
	}

	public void typeInUserName(String userName){
		waitForElementByElement(userNameField);
		userNameField.sendKeys(userName);
	}

	public void selectExpiration(String period){
		waitForElementByElement(expiry);
		Select exp = new Select(expiry);
		exp.selectByValue(period);
	}

	public void clickBlockButton(){
		waitForElementByElement(blockButton);
		clickAndWait(blockButton);
	}

	public void deselectAllSelections(){
		List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".mw-htmlform-field-HTMLCheckField .mw-input [type='checkbox']"));
		for (WebElement checkBox:checkBoxes){
			if(checkBox.isSelected()){
				checkBox.click();
			}
		}
		for (WebElement checkBox:checkBoxes){
			Assertion.assertFalse(checkBox.isSelected());
		}
	}

	public void verifyBlockedUserSubmitPage(String userName, String password){
		waitForElementByXPath("//p/a[contains(text(), '"+userName+"')]");
		waitForElementByXPath("//p[contains(text(), 'has been blocked')]");
		logOut(driver);
	}

	public void verifyUserBlocked(){

	}
}
