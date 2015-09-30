package com.wikia.webdriver.pageobjectsfactory.componentobject.toolbars;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


/**
 * @author Michal 'justnpT' Nowierski
 * @author Karol 'kkarolk' Kujawiak
 */
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
  private String searchSuggestionToolCss = "div.autocomplete div[title=\"%s\"]";
  private String toolbarToolCss = "li.overflow a[data-name=\"%s\"]";
  private String toolsListToolCss = "ul.options-list li[data-caption=\"%s\"]";
  private String toolsListToolDeleteCss = " img.trash";
  private String toolsListToolEditCss = " img.edit-pencil";
  private String addedToolsPath = "//ul[@class='tools']//a[text() = '%s']";


  public CustomizedToolbarComponentObject(WebDriver driver) {
    super(driver);
  }


  /**
   * Verifies that user toolbar buttons are visible
   */
  public void verifyUserToolBar() {
    wait.forElementVisible(By.cssSelector("div.toolbar ul.tools li.overflow"));
    wait.forElementVisible(By.cssSelector("div.toolbar ul.tools li.mytools"));
    wait.forElementVisible(By.cssSelector("div.toolbar ul.tools li a.tools-customize"));
    LOG.success("verifyUserToolBar", "user toolbar verified");
  }

  /**
   * Clicks on "Customize" button. User must be logged in.
   *
   * @author Michal Nowierski
   */
  public void clickCustomize() {
    wait.forElementVisible(customizeButton);
    jsActions.click(customizeButton);
    LOG.success("clickCustomize", "customize button clicked");
  }


  /**
   * Clicks on "ResetDefaults" button.
   *
   * @author Michal Nowierski
   */
  public void clickResetDefaults() {
    wait.forElementVisible(resetDefaultsButton);
    jsActions.click(resetDefaultsButton);
    LOG.success("clickResetDefaults", "reset defaults button clicked");
  }

  /**
   * Types GivenString to Find A Tool field
   *
   * @param toolName String to be typed into search field
   * @author Michal Nowierski
   */
  public void searchTool(String toolName) {
    wait.forElementVisible(findAToolField);
    findAToolField.clear();
    findAToolField.sendKeys(toolName);
    LOG.success("searchTool", toolName + " typed into search field");

  }


  /**
   * Types GivenString to Find A Tool field
   *
   * @param toolNewName new name for the Tool
   * @author Michal Nowierski
   */
  public void typeNewName(String toolNewName) {
    renameItemDialogInput.clear();
    renameItemDialogInput.sendKeys(toolNewName);
    LOG.success("typeNewName", toolNewName + " typed into edit name field");
  }

  /**
   * Clicks on "save" button on Rename Item dialog.
   *
   * @author Michal Nowierski
   */
  public void clickSaveNewName() {
    scrollAndClick(saveItemDialogInput);
    LOG.success("clickSaveNewName", "save name button clicked");

  }

  /**
   * Click on a Tool after searching for it
   *
   * @param toolName toolname appearing on the list of found tools
   * @author Michal Nowierski
   */
  public void clickSearchSuggestion(String toolName) {
    scrollAndClick(
        driver.findElement(
            By.cssSelector(String.format(searchSuggestionToolCss, toolName))
        )
    );
    LOG.success("clickSearchSuggestion",
                toolName + " selected from search suggestions");
  }

  /**
   * Click on a toolbar tool.
   *
   * @param data-name data-name of the toolbar tool. <br> You should check the data-name of the tool
   *                  you want to click.
   * @author Michal Nowierski
   */
  public void clickOnTool(String toolName) {
    jsActions.click(
        wait.forElementVisible(By.cssSelector(String.format(toolbarToolCss, toolName)))
    );
    LOG.success("clickOnTool", toolName + " clicked on customized toolbar");
  }

  /**
   * Click on a toolbar tool.
   *
   * @param data-name data-name of the toolbar tool. <br> You should check the data-name of the tool
   *                  you want to click.
   * @author Michal Nowierski
   */
  public void verifyFollowMessage() {
    wait.forElementVisible(pageWatchlistStatusMessage);
    LOG.success("verifyFollowMessage", "follow message verified");

  }

  /**
   * Verify that page is followed The method should be used only after clicking on "follow" button.
   * Before that, "follow" button does not have 'title' attribute which is necessary in the method
   *
   * @author Michal Nowierski
   */
  public void verifyFollowedToolbar() {
    waitForValueToBePresentInElementsAttributeByCss(String.format(toolbarToolCss, PageContent.FOLLOW),
            "title", "Unfollow");
    LOG.success("verifyFollowedToolbar", "follow button verified");

  }

  /**
   * Verify that page is unfollowed The method should be used only after clicking on "Unfollow"
   * button. Before that, "follow" button does not have 'title' attribute which is necessary in the
   * method
   *
   * @author Michal Nowierski
   */
  public void verifyUnfollowed() {
    wait.forElementClickable(pageWatchlistStatusMessage);
    waitForValueToBePresentInElementsAttributeByCss(String.format(toolbarToolCss, PageContent.FOLLOW),
                                                    "title", "Follow");
    LOG.success("verifyUnfollowed", "unfollow button verified");

  }

  /**
   * Look up if Tool appears on Toolbar List
   *
   * @param toolName {Follow, Edit, History, (...)}
   * @author Michal Nowierski
   */
  public void verifyToolOnList(String toolName) {
    wait.forElementVisible(By.cssSelector(String.format(toolsListToolCss, toolName)));
    LOG.success("verifyToolOnList", toolName + " visible on the list");

  }

  public void verifyToolNotOnList(String toolName) {
    wait.forElementNotPresent(By.cssSelector(String.format(toolsListToolCss, toolName)));
    LOG.success("verifyToolNotOnList", toolName + " not visible on the list");

  }

  /**
   * Remove a wanted Tool by its data-caption
   *
   * @param toolName ID of tool to be removed. {Follow, Edit, History, (...)}
   * @author Michal Nowierski
   */
  public void clickRemove(String toolName) {
    wait.forElementVisible(By.cssSelector(String.format(toolsListToolCss, toolName)));
    jsActions.click(String.format(toolsListToolCss, toolName) + toolsListToolDeleteCss);
    LOG.success("clickRemove", "remove button for " + toolName + " clicked");
  }

  /**
   * Rename the wanted Tool
   *
   * @param toolName ID of tool to be removed. {PageAction:Follow, PageAction:Edit,
   *                 PageAction:History, (...)}
   * @author Michal Nowierski
   */
  public void clickRename(String toolName) {
    wait.forElementVisible(By.cssSelector(String.format(toolsListToolCss, toolName)));
    jsActions.click(String.format(toolsListToolCss, toolName) + toolsListToolEditCss);
    LOG.success("clickRename", "rename button for " + toolName + " clicked");
  }

  /**
   * Click on save button on customize toolbar
   *
   * @author Michal Nowierski
   */
  public void clickSave() {
    wait.forElementVisible(saveButton);
    scrollAndClick(saveButton);
    LOG.success("clickSave", "save button clicked");

  }

  public void verifyToolOnToolbar(String toolName) {
    wait.forElementVisible(By.xpath(String.format(addedToolsPath, toolName)));
    LOG.success("verifyToolOnToolbar", "tool " + toolName + " visible on toolbar");
  }

  /**
   * <p> Verify that wanted Tool appears in Toolbar. <br> The method finds all of Tools appearing in
   * Toolbar (by their name), and checks if there is at least one name which fits the given param
   * (ToolName)
   *
   * @param ToolName Tool to be verified (name that should appear on toolbar)
   * @author Michal Nowierski
   */
  public void unfollowIfFollowed() {
    List<WebElement> list = driver.findElements(toolsList);
    for (int i = 0; i < list.size(); i++) {
      if ("Following".equals(list.get(i).getText())) {
        clickOnTool(PageContent.FOLLOW);
        verifyFollowMessage();
        wait.forTextInElement(toolsList, "Follow");
        LOG.result("unfollowIfFollowed",
                   "page was followed, unfollow button clicked", true);
        break;
      }
    }
    LOG.success("unfollowIfFollowed",
                "page was unfollowed");
  }

  public void verifyToolRemoved(String toolName) {
    wait.forElementNotPresent(
        By.xpath(String.format(addedToolsPath, toolName))
    );
    LOG.success("verifyToolRemoved", toolName + " removed from toolbar");
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
    LOG.success("openMoreMenu", "more menu opened");
  }

  public void verifyToolInMoreTool(String toolName) {
    for (WebElement elem : myToolsList) {
      Assertion.assertEquals(elem.getAttribute("data-name").toLowerCase(), toolName.toLowerCase());
    }
    LOG.success("verifyToolInMoreTool", toolName + " appears in ToolbarMoreTool.");
  }

  /**
   * Get text string from Theme Designer button in My Tools menu
   *
   */

  public String getThemeDesignerText() {
    return themeDesignerButton.getText();
  }

}
