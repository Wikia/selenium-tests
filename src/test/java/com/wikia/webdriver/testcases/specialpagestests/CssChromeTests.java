package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.contentpatterns.CssEditorContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCssPageObject;

import org.joda.time.DateTime;
import org.testng.annotations.Test;
@Test(enabled = false)
public class CssChromeTests extends NewTestTemplate {

  /**
   * http://wikia-inc.atlassian.net/browse/DAR-285
   */
  @Test(groups = {"CssChrome_001", "CssChrome", "AdminDashboard"}, enabled = false)
  @Execute(asUser = User.STAFF)
  public void showingErrorWhenWrongSyntax() {
    SpecialCssPageObject specialCss = new SpecialCssPageObject(driver).openEditor();

    specialCss.clearCssText();
    specialCss.sendCssText(CssEditorContent.INVALID_CSS_ERROR);
    specialCss.verifyAceError();
  }

  /**
   * http://wikia-inc.atlassian.net/browse/DAR-733
   */
  @Test(groups = {"CssChrome_002", "CssChrome", "AdminDashboard"}, enabled = false)
  @Execute(asUser = User.STAFF)
  @UseUnstablePageLoadStrategy
  public void verifyPublishButtonAppearsAndWorks() {
    SpecialCssPageObject specialCss = new SpecialCssPageObject(driver).openEditor();

    String currentTimestamp = specialCss.getTimeStamp();
    specialCss.saveCssContent(currentTimestamp);
    specialCss.open();
    String cssContent = specialCss.getWikiaCssContent();
    Assertion.assertEquals(cssContent, currentTimestamp);
  }

  /**
   * http://wikia-inc.atlassian.net/browse/DAR-733
   */
  @Test(groups = {"CssChrome_003", "CssChrome", "AdminDashboard"}, enabled = false)
  @Execute(asUser = User.STAFF)
  @UseUnstablePageLoadStrategy
  public void verifyEditSummaryAppearsAndWorks() {
    SpecialCssPageObject specialCss = new SpecialCssPageObject(driver).openEditor();

    String currentTimestamp = specialCss.getTimeStamp();
    specialCss.sendEditSummaryText(currentTimestamp);
    specialCss.saveCssContent(currentTimestamp);
    specialCss.open();
    specialCss.appendToUrl(URLsContent.ACTION_HISTORY);
    String editSummary = specialCss.getFirstCssRevision();
    Assertion.assertStringContains(editSummary, currentTimestamp);
  }

  /**
   * http://wikia-inc.atlassian.net/browse/DAR-733
   */
  @Test(groups = {"CssChrome_004", "CssChrome", "AdminDashboard"}, enabled = false)
  @Execute(asUser = User.STAFF)
  public void verifyChangesAppearsAndWorks() {
    SpecialCssPageObject specialCss = new SpecialCssPageObject(driver).openEditor();

    String currentTimestamp = specialCss.getTimeStamp();
    specialCss.insertCssText("\n" + currentTimestamp);
    specialCss.clickPublishButtonDropdown();
    specialCss.clickShowChanges();
    specialCss.showChangesModal();
    String addedLine = specialCss.getAddedLineText();
    Assertion.assertEquals(addedLine, currentTimestamp);
  }

  /**
   * http://wikia-inc.atlassian.net/browse/DAR-733
   */
  @Test(groups = {"CssChrome_005", "CssChrome", "AdminDashboard"}, enabled = false)
  @Execute(asUser = User.STAFF)
  @UseUnstablePageLoadStrategy
  public void verifyMinorEditAppearsAndWorks() {
    SpecialCssPageObject specialCss = new SpecialCssPageObject(driver).openEditor();

    String currentTimestamp = String.valueOf(DateTime.now().getMillis());
    specialCss.verifyMinorEditAppears();
    specialCss.clickMinorCheckbox();
    specialCss.saveCssContent(currentTimestamp);
    specialCss.open();
    specialCss.appendToUrl(URLsContent.ACTION_HISTORY);
    specialCss.verifyRevisionMarkedAsMinor();
  }

  @Test(groups = {"CssChrome_006", "CssChrome", "AdminDashboard"}, enabled = false)
  @Execute(asUser = User.STAFF)
  public void verifyHistoryButtonAppearsAndWorks() {
    SpecialCssPageObject specialCss = new SpecialCssPageObject(driver).openEditor();
    specialCss.clickPublishButtonDropdown();
    specialCss.clickHistoryButton();
    specialCss.verifyUrl("action=history");
  }

  @Test(groups = {"CssChrome_007", "CssChrome", "AdminDashboard"}, enabled = false)
  @Execute(asUser = User.STAFF)
  public void verifyDeleteButtonAppearsAndWorks() {
    SpecialCssPageObject specialCss = new SpecialCssPageObject(driver).openEditor();
    String testedPage = driver.getCurrentUrl();

    specialCss.verifyAceEditorPresence();
    specialCss.verifyArticleIsNotRemoved(testedPage);
    specialCss.clickPublishButtonDropdown();
    specialCss.verifyDeleteButtonPresence();
    specialCss.clickDeleteButton();
    specialCss.confirmDelete();

    specialCss.openSpecialCss(wikiURL);
    specialCss.verifyAceEditorPresence();
    specialCss.verifyArticleIsRemoved();
    specialCss.undeleteArticle(testedPage);

    specialCss.openSpecialCss(wikiURL);
    specialCss.verifyAceEditorPresence();
    specialCss.verifyArticleIsNotRemoved(testedPage);
  }

  /**
   * https://wikia-inc.atlassian.net/browse/DAR-731 story description
   * https://wikia-inc.atlassian.net/browse/DAR-880 development ticket
   */
  @Test(groups = {"CssChrome_008", "CssChrome", "AdminDashboard"}, enabled = false)
  @Execute(asUser = User.STAFF)
  public void verifyOnLeaveMessageWorks() {
    SpecialCssPageObject specialCss = new SpecialCssPageObject(driver).openEditor();

    specialCss.verifyAceEditorPresence();
    specialCss.sendCssText(CssEditorContent.VALID_CSS);
    driver.get(wikiURL);
    specialCss.waitForAlertAndAccept();
  }

  /**
   * https://wikia-inc.atlassian.net/browse/DAR-999
   */
  @Test(groups = {"CssChrome_009", "CssChrome", "AdminDashboard"}, enabled = false)
  @Execute(asUser = User.STAFF)
  public void verifyTalkButtonWorks() {
    SpecialCssPageObject specialCss = new SpecialCssPageObject(driver).openEditor();

    specialCss.verifyTalkBubblePresence();
    int commentsFromSpecialCss = specialCss.getNumberFromCssTalkBubble();
    specialCss.clickTalkButton();
    Assertion.assertEquals(commentsFromSpecialCss, specialCss.getNumberFromWikaiCssTalkBubble());
  }
}
