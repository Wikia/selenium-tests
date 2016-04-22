package com.wikia.webdriver.pageobjectsfactory.pageobject.chatpageobject;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ChatPage extends WikiBasePageObject {

  @FindBy(css = "textarea[name='message']")
  private WebElement messageWritingArea;
  @FindBy(css = "div.Rail")
  private WebElement sideBar;
  @FindBy(css = "div.User span.username")
  private WebElement userName;
  @FindBy(css = "[id*='Chat'] .inline-alert[id*='entry']")
  private WebElement chatInlineAlert;
  @FindBy(css = "div.User img")
  private WebElement userAvatar;
  @FindBy(css = "#UserStatsMenu .private")
  private WebElement privateMassageButton;
  @FindBy(css = "#UserStatsMenu")
  private WebElement userStatsMenu;
  @FindBy(css = "li.private-allow")
  private WebElement allowPrivateMassageButton;
  @FindBy(css = "li.kick")
  private WebElement kickUserButton;
  @FindBy(css = "li.private-block")
  private WebElement blockPrivateMassageButton;
  @FindBy(css = "h1.public.wordmark.selected")
  private WebElement mainChatSelection;
  @FindBy(css = "ul.PrivateChatList span.splotch")
  private WebElement privateMessageNotification;
  @FindBy(css = ".continued.inline-alert")
  private WebElement chatInlineAlertContinued;
  @FindBy(css = "#UserStatsMenu li.ban")
  private WebElement banUserButton;
  @FindBy(css = "#ChatBanModal")
  private WebElement chatBanModal;
  @FindBy(css = "#ChatBanModal button.primary")
  private WebElement chatBanModalButton;
  @FindBy(css = "#UserStatsMenu .regular-actions li")
  private List<WebElement> userDropDownActionsElements;
  @FindBy(css = "#UserStatsMenu .admin-actions li")
  private List<WebElement> adminDropDownActionsElements;
  @FindBy(css = "#Rail h1.private")
  private WebElement privateMessagesHeader;
  @FindBy(css = "#Rail h1.wordmark")
  private WebElement chatWordmarkImage;
  @FindBy(css = "#WikiChatList li")
  private WebElement chatLoadedIndicator;
  @FindBy(css = "#ChatHeader h1.private")
  private WebElement privateChatHeader;
  @FindBy(css = "div.actions .admin-actions .give-chat-mod")
  private WebElement addUserModStatusButton;
  @FindBy(css = "div#UserStatsMenu.UserStatsMenu")
  private WebElement userOptions;
  @FindBy(css = "ul.admin-actions")
  private WebElement userModOptions;
  @FindBy(css = "li.message-wall")
  private WebElement userMessageWallButton;
  @FindBy(xpath = "//li[@data-id='wall']")
  private WebElement userPageMessageWallTab;
  @FindBy(css = "li.contribs")
  private WebElement userContributionsButton;
  @FindBy(xpath = "//li[@data-id='contribs']")
  private WebElement userPageContributionsTab;
  @FindBy(css = "form#Write.Write.blocked")
  private WebElement userBlockedMessageField;
  @FindBy(css = "div.header-column.header-title")
  private WebElement permissionsErrorTitle;
  @FindBy(xpath = "//h1")
  private WebElement userNameTitle;

  private static final String USER_UNBAN_LINK = "//a[@data-type='ban-undo' and @data-user='%s']";
  private static final String USER_UNBAN_CONFIRM_MESSAGE =
      "//div[@class='Chat']//li[contains(text(), 'has ended the Chat ban for %s')]";
  private static final String USER_SELECTOR = "#user-%s";
  private static final String PRIVATE_MESSAGE_USER_SELECTOR = "#priv-user-%s";
  private static final String PRIVATE_MESSAGE_SELECTED_USER_SELECTOR =
      "#priv-user-%s.User.selected";
  private static final String PATH_MESSAGE_ON_CHAT = "//span[@class='message'][contains(text(), '%s')]";
  private static final String NOTIFICATION_COUNTER =
      "//span[@class='splotch' and contains(text(), '%s')]";
  private final String USER_NAME_ON_USER_PROFILE_SCREEN = userNameTitle.getText();
  private final String USER_MESSAGE_ON_CHAT = String.format("Hello this is test message timestamp: ") + getTimeStamp();

  private static final int REGULAR_USER_DROBDOWN_ELEMENTS = 3;

  public ChatPage open(){
    getUrl(urlBuilder.getUrlForWiki() + URLsContent.SPECIAL_CHAT);

    return this;
  }

  public boolean isUserOnChat() {
    try {
      return chatInlineAlert.isDisplayed();
    }catch (ElementNotFoundException e){
      return false;
    }
  }

  public boolean isMessageOnChat() {
    WebElement postedMessage = (WebElement) By.xpath(String.format(PATH_MESSAGE_ON_CHAT, USER_MESSAGE_ON_CHAT));
    try {
      return postedMessage.isDisplayed();
    }catch (ElementNotFoundException e){
      return false;
    }
  }

  public boolean isUserWelcomeMessageDisplayed(String userName) {
    WebElement welcomeMessage = (WebElement) By.cssSelector(String.format(USER_SELECTOR, userName));
    try {
      return welcomeMessage.isDisplayed();
    }catch (ElementNotFoundException e){
      return false;
    }
  }

  public void verifyUserIsVisibleOnContactsList(String userName) {
    wait.forElementVisible(By.cssSelector(String.format(USER_SELECTOR, userName)));
    PageObjectLogging.log("verifyUserIsVisibleOnContactsList", userName
        + " is visible on contacts list", true, driver);
  }

  public boolean isPrivateMessageHeaderDispayed() {
    try {
      return privateMessagesHeader.isDisplayed();
    }catch (ElementNotFoundException e){
      return false;
    }
  }

  public boolean isPermissionsErrorTitleDisplayed() {
    try {
      return permissionsErrorTitle.isDisplayed();
    }catch (ElementNotFoundException e){
      return false;
    }
  }

  public boolean isPrivateMessageNotificationDisplayed() {
    try {
      return privateMessageNotification.isDisplayed();
    }catch (ElementNotFoundException e){
      return false;
    }
  }

  public void verifyPrivateMessageNotification(int notificationCount) {
    wait.forElementVisible(By.xpath(String.format(NOTIFICATION_COUNTER, notificationCount)));
    PageObjectLogging.log("verifyPrivateMessageNotification",
        "private message notification number " + notificationCount + " is visible", true);
  }

  public void verifyPrivateMessageIsHighlighted(String user) {
    getElementForUser(user, PRIVATE_MESSAGE_SELECTED_USER_SELECTOR);
    PageObjectLogging.log("verifyPrivateMessageIsHighlighted",
        "private message section is highlighted", true, driver);
  }

  public boolean isPrivateMessageHiglighted(String userName){
    try {
      return userWebElement(userName, PRIVATE_MESSAGE_SELECTED_USER_SELECTOR).isDisplayed();
    }catch (ElementNotFoundException e){
      return false;
    }
  }

  public boolean isPrivateChatOpen() {
    try {
      return privateChatHeader.isDisplayed();
    }catch (ElementNotFoundException e){
      return false;
    }
  }

  public void verifyMainChatIsHighlighted() {
    wait.forElementVisible(mainChatSelection);
    PageObjectLogging.log("verifyPrivateMessageIsHighlighted",
            "private message section is highlighted", true);
  }

  public boolean isRegularUserDropdownDisplayed() {
    if(userDropDownActionsElements.size() == REGULAR_USER_DROBDOWN_ELEMENTS){
      return true;
    }else{
      return false;
    }
  }

  public void verifyBlockingUserDropdown(String userName) {
    clickOnDifferentUser(userName);
    // This check is needed because chat has some lags when it comes to loading dropdown content
    // We need to click it more then once sometimes to actually load everything
    waitForProperNumberOfElementsInUserDropdown(userName);
    List<WebElement> list = userDropDownActionsElements;
    Assertion.assertNumber(list.size(), 3, "Checking number of elements in the drop-down");
    Assertion.assertEquals(list.get(0).getAttribute("class"), "message-wall");
    Assertion.assertEquals(list.get(1).getAttribute("class"), "contribs");
    Assertion.assertEquals(list.get(2).getAttribute("class"), "private-allow");
  }

  public void verifyPrivateUserDropdown(String userName) {
    openUserDropDownInPrivateMessageSection(userName);
    Assertion.assertNumber(userDropDownActionsElements.size(), 3,
        "Checking number of elements in the drop-down");
    Assertion
        .assertEquals(userDropDownActionsElements.get(0).getAttribute("class"), "message-wall");
    Assertion.assertEquals(userDropDownActionsElements.get(1).getAttribute("class"), "contribs");
    Assertion.assertEquals(userDropDownActionsElements.get(2).getAttribute("class"),
        "private-block");
  }

  public void verifyAdminUserDropdown(String userName) {
    // Admin dropDown consists of two parts: regular dropdown
    verifyNormalUserDropdown(userName);

    // and admin dropDown
    Assertion.assertNumber(adminDropDownActionsElements.size(), 3,
        "Checking number of elements in the drop-down");
    Assertion.assertEquals(adminDropDownActionsElements.get(0).getAttribute("class"),
        "give-chat-mod");
    Assertion.assertEquals(adminDropDownActionsElements.get(1).getAttribute("class"), "kick");
    Assertion.assertEquals(adminDropDownActionsElements.get(2).getAttribute("class"), "ban");
  }

  public void writeOnChat() {
    wait.forElementVisible(chatLoadedIndicator);
    messageWritingArea.sendKeys(USER_MESSAGE_ON_CHAT);
    new Actions(driver).sendKeys(messageWritingArea, Keys.ENTER).perform();
  }

  public void selectPrivateMessageToUser(String userName) {
    // This check is needed in case some of the previous tests failed
    // We need to do the clean up in this place - allow provided user to send private messages
    if (checkIfPrivateMessagesNotAllowed(userName)) {
      allowPrivateMessageFromUser(userName);
    }
    clickOnDifferentUser(userName);
    wait.forElementVisible(privateMassageButton);
    privateMassageButton.click();
    WebElement userInPrivateMessageSection =
        getElementForUser(userName, PRIVATE_MESSAGE_USER_SELECTOR);
    wait.forElementVisible(userInPrivateMessageSection);
    PageObjectLogging.log("selectPrivateMessageToUser", "private message selected from dropdown",
        true);
  }

  public void clickOnMainChat() {
    chatWordmarkImage.click();
    PageObjectLogging.log("clickOnMainChat", "main chat is clicked", true);
  }

  public void clickOnUserInPrivateMessageSection(String userName) {
    userOnPrivateMessageSection(userName).click();
    PageObjectLogging.log("clickOnUserInPrivateMessageSection", "private messages user " + userName
        + " is clicked", true);
  }

  private void clickBanUser(String userName) {
    banUserButton.click();
    wait.forElementVisible(chatBanModal);
    PageObjectLogging.log("clickBanUser", "ban user " + userName + " is clicked", true);
  }

  public void banUser(String userName) {
    clickOnDifferentUser(userName);
    clickBanUser(userName);
    chatBanModalButton.click();
    waitForElementNotVisibleByElement(chatBanModal);
    PageObjectLogging.log("clickBanUser", userName + " ban modal is closed", true);
  }

  public void clickOpenUserMessageWall() {
    userMessageWallButton.click();
    PageObjectLogging.log("clickOpenUserMessageWall", " Message wall button is clicked", true);
  }

  public void clickOpenUserContributions() {
    userContributionsButton.click();
    PageObjectLogging.log("clickOpenUserContributions", " Cotributions button is clicked", true);
  }

  public void clickOnUserOptionsKickButton() {
    kickUserButton.click();
    PageObjectLogging.log("clickOnUserOptionsKikButton", " kick user button is clicked", true);
  }

  public void switchToSecondTab(String firstTab){
    ArrayList<String> openTabs = new ArrayList(driver.getWindowHandles());
    for (String window:openTabs) {
      if (window != firstTab) {
        driver.switchTo().window(window);
      }
    }

  }

  public boolean isMessageWallOpened(String userName) {
    try {
      Assertion.assertEquals(userName, USER_NAME_ON_USER_PROFILE_SCREEN);
      return userPageMessageWallTab.isDisplayed();
    }catch (ElementNotFoundException e){
      return false;
    }
  }

  public boolean isPrivateMessageHederDisplayed() {
    try {
      return privateMessagesHeader.isDisplayed();
    }catch (ElementNotFoundException e){
      return false;
    }
  }

  public boolean isContributionsPageOpened (String userName) {
    try {
      Assertion.assertEquals(userName, USER_NAME_ON_USER_PROFILE_SCREEN);
      return userPageContributionsTab.isDisplayed();
    }catch (ElementNotFoundException e){
      return false;
    }
  }

  public boolean isUserKickedFromChat() {
    try {
      return userBlockedMessageField.isDisplayed();
    }catch (ElementNotFoundException e){
      return false;
    }
  }

  public boolean isBlockedUserAbleToWritePrivateMesage() {
    try {
      return privateMassageButton.isDisplayed();
    }catch (ElementNotFoundException e){
      return false;
    }
  }

  public boolean isBlockPrivateMessageButtonDisplayed() {
    try {
      return blockPrivateMassageButton.isDisplayed();
    }catch (ElementNotFoundException e){
      return false;
    }
  }

  public void verifyStaffOptionsNotDisplayed(String userName){
    clickOnDifferentUser(userName);
    waitForElementNotVisibleByElement(userModOptions);
    PageObjectLogging.log("verifyStaffOptionsNotDisplayed", userName + " mod options not displayed", true);
  }

  public boolean verifyUserCanNotBlockPrivateMessagesFromStaff(String userName){


    waitForElementNotVisibleByElement(blockPrivateMassageButton);
    PageObjectLogging.log("verifyUserCanNotBlockPrivateMessagesFromStaff",
            userName + " can not block private messages from staff", true);
  }

  public boolean isChatUnbanMessageDispalyed() {
    WebElement unBanMessage = (WebElement) By.xpath(String.format(USER_UNBAN_CONFIRM_MESSAGE, userName));
    try {
      return unBanMessage.isDisplayed();
    }catch (ElementNotFoundException e){
      return false;
    }
  }

  public void unBanUser(String userName) {
    WebElement unbanLink = driver.findElement(By.xpath(String.format(USER_UNBAN_LINK, userName)));
    wait.forElementVisible(unbanLink);
    unbanLink.click();
    PageObjectLogging.log("unBanUser", userName + " is no longer banned", true);
  }

  public void clickOnDifferentUser(String userName) {
    WebElement userOnGuestList = getElementForUser(userName, USER_SELECTOR);
    boolean hidden = !userStatsMenu.isDisplayed();
    int i = 0;
    // we need this loop because of chat problems - sometimes we need to click more then once
    // to open user dropdown. To avoid infinite loop i (threshold) was introduced
    while (hidden) {
      userOnGuestList.click();
      if (userStatsMenu.isDisplayed() || i >= 10) {
        hidden = false;
      }
      i++;
    }
    PageObjectLogging.log("clickOnDifferentUser", userName + " button clicked", true);
  }

  private void waitForProperNumberOfElementsInUserDropdown(String userName) {
    int i = 0;
    boolean dropdownLoaded = false;
    while (!dropdownLoaded) {
      clickOnDifferentUser(userName);
      if (userDropDownActionsElements.size() == 3 || i >= 10) {
        dropdownLoaded = true;
      } else {
        userAvatar.click();
      }
      i++;
    }
  }

  public void openUserDropDownInPrivateMessageSection(String userName) {
    boolean hidden = !userStatsMenu.isDisplayed();
    int i = 0;
    while (hidden) {
      userWebElement(userName, PRIVATE_MESSAGE_USER_SELECTOR).click();
      if (userStatsMenu.isDisplayed() || i >= 10) {
        hidden = false;
      }
      i++;
    }
    PageObjectLogging.log("openUserDropDownInPrivateMessageSection", userName + " button clicked",
        true);
  }

  public void blockPrivateMessageFromUser(String userName) {
    openUserDropDownInPrivateMessageSection(userName);
    blockPrivateMassageButton.click();
    waitForElementNotVisibleByElement(userStatsMenu);
  }

  public void unBlockPrivateMessageFromUser(String userName) {
    openUserDropDownInPrivateMessageSection(userName);
    blockPrivateMassageButton.click();
    waitForElementNotVisibleByElement(userStatsMenu);
  }

  public void allowPrivateMessageFromUser(String userName) {
    boolean blocked = true;
    int i = 0;
    // Open user stats dropdown and check if private messages are allowed
    // if are not allowed - allow
    // we need this loop because of chat problems - sometimes we need to click more then once
    // to open user dropdown and allow private messages.
    // To avoid infinite loop i (threshold) was introduced
    while (blocked) {
      clickOnDifferentUser(userName);
      if (isElementOnPage(allowPrivateMassageButton) || i >= 10) {
        allowPrivateMassageButton.click();
        clickOnDifferentUser(userName);
        if (!isElementOnPage(allowPrivateMassageButton)) {
          blocked = false;
        }
      }
      i++;
    }
    PageObjectLogging.log("allowPrivateMessageFromUser", "private messages from " + userName
        + " are allowed now", true);
  }

  private boolean checkIfPrivateMessagesNotAllowed(String userName) {
    // check is user stats are already open
    // if not open them
    if (!userStatsMenu.isDisplayed()) {
      clickOnDifferentUser(userName);
    }
    return isElementOnPage(allowPrivateMassageButton);
  }

  private WebElement getElementForUser(String userName) {
    WebElement kk = (WebElement) By.cssSelector(String.format(PRIVATE_MESSAGE_USER_SELECTOR, userName));
    wait.forElementVisible(By.cssSelector(userCss));
    return driver.findElement(By.cssSelector(userCss));
  }

  private WebElement userWebElement(String userName, String message) {
    WebElement userOnPrivateSection = (WebElement) By.cssSelector(String.format(message, userName));
    return userOnPrivateSection;
  }

  public boolean isUserInPrivateSectionDisplayed(String userName) {
    try {
      return userWebElement(userName, PRIVATE_MESSAGE_USER_SELECTOR).isDisplayed();
    } catch (ElementNotFoundException e) {
      return false;
    }
  }

  public List<String> sendMultipleMessagesFromUser(String userName, int messagesCount) {
    List<String> messagesSent = new ArrayList<>();
    for (int i = 0; i < messagesCount; i++) {
      String message = generateMessageFromUser(userName);
      writeOnChat(message);
      messagesSent.add(message);
    }
    return messagesSent;
  }

  public void verifyMultiplePrivateMessages(List<String> messagesReceived, String senderUser) {
    verifyPrivateMessageNotification(messagesReceived.size());
    clickOnUserInPrivateMessageSection(senderUser);
    for (String message : messagesReceived) {
      verifyMessageOnChat(message);
    }
  }
}
