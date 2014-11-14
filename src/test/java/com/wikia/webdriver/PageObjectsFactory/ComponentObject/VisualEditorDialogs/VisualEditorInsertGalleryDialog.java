package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class VisualEditorInsertGalleryDialog extends VisualEditorDialog {

	public VisualEditorInsertGalleryDialog(WebDriver driver) {
		super(driver);
	}
}
