package com.wikia.webdriver.pageobjectsfactory.pageobject.signup;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.AlertHandler;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.editprofile.AvatarComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UserProfilePageObject extends WikiBasePageObject {

  @FindBy(css = "li[data-id='blog'] a")
  private WebElement blogTab;
  @FindBy(css = "a[data-id='createblogpost']")
  private WebElement createBlogPostButton;
  @FindBy(css = ".WikiaBlogListingPost h2>a")
  private List<WebElement> blogPostList;
  @FindBy(css = ".masthead-avatar")
  private WebElement avatarWrapper;
  @FindBy(css = "#userAvatarEdit")
  private WebElement avatarEditButton;
  @FindBy(css = "#UserAvatarRemove")
  private WebElement avatarRemoveButton;
  @FindBy(css = ".masthead-avatar img.avatar")
  private WebElement avatar;

  private String avatarChangedSelector = ".masthead-avatar img.avatar[src*='/%imageName%']";

  private By avatarImage = By.cssSelector("img.avatar");

  public UserProfilePageObject(WebDriver driver) {
    super();
  }

  public void clickOnBlogTab() {
    wait.forElementVisible(blogTab);
    wait.forElementClickable(blogTab);
    blogTab.click();
    PageObjectLogging.log("clickOnBlogTab", "Click on blog tab", true);
  }

  public BlogPageObject openBlogPage(int blogNumber) {
    String blogURL = blogPostList.get(blogNumber).getAttribute("href");
    getUrl(blogURL);
    PageObjectLogging.log("openBlogPage",
                          "blog post " + blogURL + " opened",
                          true);
    return new BlogPageObject(driver);
  }

  public BlogPageObject openFirstPost() {
    for (int i = 0; i < blogPostList.size(); i++) {
      BlogPageObject blogPage = openBlogPage(i);
      String pageContent = blogPage.getAtricleTextRaw().toLowerCase();
      if (!(pageContent.contains("deleted") || pageContent.contains("redirected"))) {
        PageObjectLogging.log("openFirstPost", "valid post found on " + i + " position", true);
        break;
      }
      PageObjectLogging.log("openFirstPost", "deleted post found on " + i
                                             + " position, trying next one", true);
      driver.navigate().back();
    }
    return new BlogPageObject(driver);
  }

  public SpecialCreatePage clickOnCreateBlogPost() {
    wait.forElementVisible(createBlogPostButton);
    wait.forElementClickable(createBlogPostButton);
    scrollAndClick(createBlogPostButton);
    PageObjectLogging.log("clickOnCreateBlogPost", "Click on create blog post button",
                          true, driver);
    return new SpecialCreatePage();
  }

  private void showAvatarControls() {
    setDisplayStyle(".avatar-controls", "block");
  }

  private void hideAvatarControls() {
    setDisplayStyle(".avatar-controls", "none");
  }

  public AvatarComponentObject clickEditAvatar() {
    showAvatarControls();
    avatarEditButton.click();
    hideAvatarControls();
    PageObjectLogging.log("clickEditAvatar", "avatar edit button clicked", true);
    return new AvatarComponentObject(driver);
  }

  public void clickRemoveAvatar() {
    showAvatarControls();
    wait.forElementClickable(avatarRemoveButton);
    avatarRemoveButton.click();
    AlertHandler.acceptPopupWindow(driver, 10);
    hideAvatarControls();
    wait.forElementVisible(avatarWrapper);
    PageObjectLogging.log("clickRemoveAvatar", "avatar remove button clicked", true);
  }

  public void verifyAvatar() {
    wait.forElementVisible(avatar);
    PageObjectLogging.log("verifyAvatar", "Desired avatar is visible on user profile page", true);
  }

  public void verifyAvatarChanged(String url) {
    wait.forValueToBeNotPresentInElementsAttribute(avatar, "src", url);
    PageObjectLogging.log("verifyAvatarChanged", "avatar src value has changed", true);
  }

  public String getAvatarImageSrc() {
    return avatarWrapper.findElement(avatarImage).getAttribute("src");
  }

  public void verifyProfilePage(String userName) {
    verifyUrlContains(URLsContent.USER_PROFILE.replace("%userName%", userName), 30);
    PageObjectLogging.log("verifyProfilePage", userName + " user profile page verified", true);
  }
}
