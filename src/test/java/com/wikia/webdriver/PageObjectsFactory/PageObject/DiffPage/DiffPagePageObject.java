package com.wikia.webdriver.PageObjectsFactory.PageObject.DiffPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class DiffPagePageObject extends BasePageObject {

	public DiffPagePageObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css="#mw-imagepage-content table.diff")
	private WebElement diffTable;

}
