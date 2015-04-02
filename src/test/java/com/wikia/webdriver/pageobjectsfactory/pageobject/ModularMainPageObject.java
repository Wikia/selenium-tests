package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.google.sitebricks.client.Web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by RodriGomez on 02/04/15.
 */
public class ModularMainPageObject extends WikiBasePageObject{

  @FindBy(css = ".hero-image")
  private WebElement heroImageModule;
  @FindBy(css = ".hero-description-text")
  private WebElement heroPublishedDescription;
  @FindBy(css = ".title-edit-btn")
  private WebElement editDescriptionButton;
  @FindBy(css = ".update-text")
  private WebElement updateCoverImageButton;
  @FindBy(css = ".image-save-bar .new-btn.save-btn")
  private WebElement imagePublishButton;
  @FindBy(css = ".image-save-bar .new-btn.discard-btn")
  private WebElement imageDiscardButton;
  @FindBy(css = ".hero-description .new-btn.save-btn")
  private WebElement descriptionPublishButton;
  @FindBy(css = ".hero-description .new-btn.discard-btn")
  private WebElement descriptionDiscardButton;
  @FindBy(css = ".edited-text")
  private WebElement descriptionEditField;
  @FindBy(css = ".edit-box")
  private WebElement editBox;
  @FindBy (css = ".save-text")
  private WebElement coverImageInformativeText;
}
