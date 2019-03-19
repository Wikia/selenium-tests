package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.AlertHandler;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.componentobject.editprofile.AvatarComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPage;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UserProfilePage extends WikiBasePageObject {

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
  @FindBy(css = ".mw-userpage-userdoesnotexist")
  private WebElement notExistsMessage;
  @FindBy(css = "div.masthead-info h1")
  @Getter
  private WebElement userNameTextBox;

  private By avatarImage = By.cssSelector("img.avatar");

  /**
   * Open User Profile Page
   */
  public UserProfilePage open(String userName) {
    getUrl(urlBuilder.getUrlForWikiPage(URLsContent.USER_PROFILE.replace("%userName%", userName)));

    return this;
  }

  public void clickOnBlogTab() {
    wait.forElementVisible(blogTab);
    wait.forElementClickable(blogTab);
    blogTab.click();
    Log.log("clickOnBlogTab", "Click on blog tab", true);
  }

  public BlogPage openBlogPage(int blogNumber) {
    String blogURL = blogPostList.get(blogNumber).getAttribute("href");
    getUrl(blogURL);
    Log.log("openBlogPage", "blog post " + blogURL + " opened", true);
    return new BlogPage();
  }

  public BlogPage openFirstPost() {
    for (int i = 0; i < blogPostList.size(); i++) {
      BlogPage blogPage = openBlogPage(i);
      String pageContent = blogPage.getArticleTextRaw().toLowerCase();
      if (!(pageContent.contains("deleted") || pageContent.contains("redirected"))) {
        Log.log("openFirstPost", "valid post found on " + i + " position", true);
        break;
      }
      Log.log("openFirstPost", "deleted post found on " + i + " position, trying next one", true);
      driver.navigate().back();
    }
    return new BlogPage();
  }

  public SpecialCreatePage clickOnCreateBlogPost() {
    wait.forElementVisible(createBlogPostButton);
    wait.forElementClickable(createBlogPostButton);
    scrollAndClick(createBlogPostButton);
    Log.log("clickOnCreateBlogPost", "Click on create blog post button", true, driver);
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
    Log.log("clickEditAvatar", "avatar edit button clicked", true);
    return new AvatarComponentObject();
  }

  public void clickRemoveAvatar() {
    showAvatarControls();
    wait.forElementClickable(avatarRemoveButton);
    avatarRemoveButton.click();
    AlertHandler.acceptPopupWindow(driver, 10);
    hideAvatarControls();
    wait.forElementVisible(avatarWrapper);
    Log.log("clickRemoveAvatar", "avatar remove button clicked", true);
  }

  public void verifyAvatar() {
    wait.forElementVisible(avatar);
    Log.log("verifyAvatar", "Desired avatar is visible on user profile page", true);
  }

  public void verifyAvatarChanged(String url) {
    wait.forValueToBeNotPresentInElementsAttribute(avatar, "src", url);
    Log.log("verifyAvatarChanged", "avatar src value has changed", true);
  }

  public String getAvatarImageSrc() {
    return avatarWrapper.findElement(avatarImage).getAttribute("src");
  }

  public void verifyProfilePage(String userName) {
    verifyUrlContains(URLsContent.USER_PROFILE.replace("%userName%", userName), 30);
    Log.log("verifyProfilePage", userName + " user profile page verified", true);
  }

  public String getUserName() {
    return userNameTextBox.getText();
  }

  public String getNotExistsMessage() { return notExistsMessage.getText(); }
}
