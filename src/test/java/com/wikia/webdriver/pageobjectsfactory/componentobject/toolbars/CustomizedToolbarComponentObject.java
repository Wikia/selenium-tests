package com.wikia.webdriver.pageobjectsfactory.componentobject.toolbars;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.oasis.components.notifications.NotificationType;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CustomizedToolbarComponentObject extends WikiBasePageObject {

  @FindBy(css = "div[class*='wikia-bar'] a.tools-customize[data-name='customize']")
  private WebElement customizeButton;
  @FindBy(css = "div.msg")
  private WebElement pageWatchlistStatusMessage;
  @FindBy(css = "div.search-box input.search")
  private WebElement findAToolField;
  @FindBy(css = "#MyToolsRenameItem input.input-box")
  private WebElement renameItemDialogInput;
  @FindBy(css = "#MyToolsRenameItem button.primary")
  private WebElement saveItemDialogInput;
  @FindBy(css = "#MyToolsConfigurationWrapper footer button.primary")
  private WebElement saveButton;
  @FindBy(css = "span.reset-defaults img")
  private WebElement resetDefaultsButton;
  @FindBy(css = ".overflow-menu > .tools-menu li > a[href*=Special]")
  private List<WebElement> myToolsList;
  @FindBy(css = ".tools-menu li > a[data-name='themedesigner]'")
  private WebElement themeDesignerButton;
  private By toolsList = By.cssSelector("ul.tools li");
  private By customizeToolbar = By.cssSelector("#MyToolsConfigurationWrapper");
  private String searchSuggestionToolCss = "div.autocomplete div[title=\"%s\"]";
  private String toolbarToolCss = "li.overflow a[data-name=\"%s\"]";
  private String toolsListToolCss = "ul.options-list li[data-caption=\"%s\"]";
  private String toolsListToolDeleteCss = " img.trash";
  private String toolsListToolEditCss = " img.edit-pencil";
  private String addedToolsPath = "//ul[@class='tools']//a[text() = '%s']";


  public CustomizedToolbarComponentObject(WebDriver driver) {
    super();
  }


  /**
   * Verifies that user toolbar buttons are visible
   */
  public void verifyUserToolBar() {
    wait.forElementVisible(By.cssSelector("div.toolbar ul.tools li.overflow"));
    wait.forElementVisible(By.cssSelector("div.toolbar ul.tools li.mytools"));
    wait.forElementVisible(By.cssSelector("div.toolbar ul.tools li a.tools-customize"));
    PageObjectLogging.log("verifyUserToolBar", "user toolbar verified", true);
  }

  /**
   * Clicks on "Customize" button. User must be logged in.
   */
  public void clickCustomize() {
    wait.forElementVisible(customizeButton);
    jsActions.click(customizeButton);
    PageObjectLogging.log("clickCustomize", "customize button clicked", true);
  }


  /**
   * Clicks on "ResetDefaults" button.
   */
  public void clickResetDefaults() {
    wait.forElementVisible(resetDefaultsButton);
    jsActions.click(resetDefaultsButton);
    PageObjectLogging.log("clickResetDefaults", "reset defaults button clicked", true);
  }

  /**
   * Types GivenString to Find A Tool field
   *
   * @param toolName String to be typed into search field
   */
  public void searchTool(String toolName) {
    wait.forElementVisible(findAToolField);
    findAToolField.clear();
    findAToolField.sendKeys(toolName);
    PageObjectLogging.log("searchTool", toolName + " typed into search field", true);

  }


  /**
   * Types GivenString to Find A Tool field
   *
   * @param toolNewName new name for the Tool
   */
  public void typeNewName(String toolNewName) {
    renameItemDialogInput.clear();
    renameItemDialogInput.sendKeys(toolNewName);
    PageObjectLogging.log("typeNewName", toolNewName + " typed into edit name field", true);
  }

  /**
   * Clicks on "save" button on Rename Item dialog.
   */
  public void clickSaveNewName() {
    scrollAndClick(saveItemDialogInput);
    PageObjectLogging.log("clickSaveNewName", "save name button clicked", true);

  }

  /**
   * Click on a Tool after searching for it
   *
   * @param toolName toolname appearing on the list of found tools
   */
  public void clickSearchSuggestion(String toolName) {
    scrollAndClick(
        driver.findElement(
            By.cssSelector(String.format(searchSuggestionToolCss, toolName))
        )
    );
    PageObjectLogging.log("clickSearchSuggestion",
                          toolName + " selected from search suggestions", true);
  }

  /**
   * Click on a toolbar tool.
   *
   * @param toolName data-name of the toolbar tool.
   *            You should check the data-name of the tool you want to click.
   */
  public void clickOnTool(String toolName) {
    jsActions.click(
        wait.forElementVisible(By.cssSelector(String.format(toolbarToolCss, toolName)))
    );
    PageObjectLogging.log("clickOnTool", toolName + " clicked on customized toolbar", true);
  }
//********************************************************************************************************
  /**
   * Verify if any of banner notifications has expected message stating user is following an article
   *
   */
  public void verifyFollowMessage() {
    Assertion.assertListContains(getNotifications(NotificationType.CONFIRM).stream().map(n->n.getMessage())
                    .collect(Collectors.toList()),
            "The page \"" + getArticleName().replace("_"," ") + "\" has been added to your watchlist.");
    PageObjectLogging.log("verifyFollowMessage", "follow message verified", true);
  }

  /**
   * Verify that page is followed The method should be used only after clicking on "follow" button.
   * Before that, "follow" button does not have 'title' attribute which is necessary in the method
   */
  public void verifyFollowedToolbar() {
    waitForValueToBePresentInElementsAttributeByCss(String.format(toolbarToolCss, PageContent.FOLLOW),
            "title", "Unfollow");
    PageObjectLogging.log("verifyFollowedToolbar", "follow button verified", true);
  }

  /**
   * Verify that page is unfollowed The method should be used only after clicking on "Unfollow"
   * button. Before that, "follow" button does not have 'title' attribute which is necessary in the
   * method
   */
  public void verifyUnfollowed() {
    Assertion.assertListContains(getNotifications(NotificationType.CONFIRM).stream().map(n->n.getMessage())
                    .collect(Collectors.toList()),
            "The page \"" + getArticleName().replace("_"," ") + "\" has been removed from your watchlist.");

    waitForValueToBePresentInElementsAttributeByCss(String.format(toolbarToolCss, PageContent.FOLLOW),
            "title", "Follow");
    PageObjectLogging.log("verifyUnfollowed", "unfollow button verified", true);
  }

  /**
   * Look up if Tool appears on Toolbar List
   *
   * @param toolName {Follow, Edit, History, (...)}
   */
  public void verifyToolOnList(String toolName) {
    wait.forElementVisible(By.cssSelector(String.format(toolsListToolCss, toolName)));
    PageObjectLogging.log("verifyToolOnList", toolName + " visible on the list", true);
  }

  public void verifyToolNotOnList(String toolName) {
    wait.forElementNotPresent(By.cssSelector(String.format(toolsListToolCss, toolName)));
    PageObjectLogging.log("verifyToolNotOnList", toolName + " not visible on the list", true);
  }

  /**
   * Remove a wanted Tool by its data-caption
   *
   * @param toolName ID of tool to be removed. {Follow, Edit, History, (...)}
   */
  public void clickRemove(String toolName) {
    wait.forElementVisible(By.cssSelector(String.format(toolsListToolCss, toolName)));
    jsActions.click(String.format(toolsListToolCss, toolName) + toolsListToolDeleteCss);
    PageObjectLogging.log("clickRemove", "remove button for " + toolName + " clicked", true);
  }

  /**
   * Rename the wanted Tool
   *
   * @param toolName ID of tool to be removed. {PageAction:Follow, PageAction:Edit,
   *                 PageAction:History, (...)}
   */
  public void clickRename(String toolName) {
    wait.forElementVisible(By.cssSelector(String.format(toolsListToolCss, toolName)));
    jsActions.click(String.format(toolsListToolCss, toolName) + toolsListToolEditCss);
    PageObjectLogging.log("clickRename", "rename button for " + toolName + " clicked", true);
  }

  /**
   * Click on save button on customize toolbar
   */
  public void clickSave() {
    wait.forElementVisible(saveButton);
    scrollAndClick(saveButton);
    PageObjectLogging.log("clickSave", "save button clicked", true);
  }

  public void verifyToolOnToolbar(String toolName) {
    wait.forElementVisible(By.xpath(String.format(addedToolsPath, toolName)));
    PageObjectLogging.log("verifyToolOnToolbar", "tool " + toolName + " visible on toolbar", true);
  }

  public void unfollowIfFollowed() {
    List<WebElement> list = driver.findElements(toolsList);
    for (int i = 0; i < list.size(); i++) {
      if ("Following".equals(list.get(i).getText())) {
        clickOnTool(PageContent.FOLLOW);
        verifyFollowMessage();
        wait.forTextInElement(toolsList, "Follow");
        PageObjectLogging.log("unfollowIfFollowed",
                              "page was followed, unfollow button clicked", true);
        break;
      }
    }
    PageObjectLogging.log("unfollowIfFollowed",
                          "page was unfollowed", true);
  }

  public void verifyToolRemoved(String toolName) {
    wait.forElementNotPresent(
        By.xpath(String.format(addedToolsPath, toolName))
    );
    PageObjectLogging.log("verifyToolRemoved", toolName + " removed from toolbar", true);
  }

  public void addManyItems(String name, Integer count) {
    for (int i = 0; i < count; i++) {
      searchTool(name.substring(0, 2));
      clickSearchSuggestion(name);
      verifyToolOnList(name);
    }
  }

  public void openMoreMenu() {
   jsActions.execute("$('.overflow-menu').mouseover();");
    wait.forElementVisible(By.cssSelector(".overflow-menu > .tools-menu"));
    PageObjectLogging.log("openMoreMenu", "more menu opened", true);
  }

  public void verifyToolInMoreTool(String toolName) {
    for (WebElement elem : myToolsList) {
      Assertion.assertEquals(elem.getAttribute("data-name").toLowerCase(), toolName.toLowerCase());
    }
    PageObjectLogging.log("verifyToolInMoreTool", toolName + " appears in ToolbarMoreTool.", true);
  }

  /**
   * Get text string from Theme Designer button in My Tools menu
   *
   */
  public String getThemeDesignerText() {
    return themeDesignerButton.getText();
  }

  public void waitForCustomizeToolbarModalToDisappear() {
    wait.forElementNotVisible(customizeToolbar);
  }
}
