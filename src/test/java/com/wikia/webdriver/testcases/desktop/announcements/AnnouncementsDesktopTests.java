package com.wikia.webdriver.testcases.desktop.announcements;

import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.api.UserRegistration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.SignUpUser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.desktop.components.navigation.global.GlobalNavigation;
import com.wikia.webdriver.elements.communities.desktop.components.navigation.global.OnSiteNotifications;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.AnnouncementsPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.time.LocalDate;

/**
 * Announcements is a feature page for admins & mods to create notifications for community's active users.
 * Active user is a person who contributed on wiki or discussion in the last 90 days.
 * Announcements are available via <wiki>/announcements URL, entry point is in the Admin Dashboard.
 * More info: https://community.wikia.com/wiki/Help:Notifications
 */

@InBrowser(browser = Browser.CHROME)
@Execute(onWikia = MobileWikis.DISCUSSIONS_7)
public class AnnouncementsDesktopTests extends NewTestTemplate {

    @Test(groups = "Announcements_Desktop", enabled = false)
    @RelatedIssue(issueID = "IRIS-6553")
    public void communityNewUserCanGetFreshAnnouncement() {
        String articleName = "QA_article_" + Long.toString(DateTime.now().getMillis());
        String tempLogin = "QA" + Long.toString(DateTime.now().getMillis());
        String tempPass = Long.toString(DateTime.now().getMillis());
        String announcement = "Automatic announcement";
        String announcementUrl = "fandom.wikia.com";
        ArticlePageObject article = new ArticlePageObject();

        //Create regular user account and edit an article on a wiki
        SignUpUser newUser = new SignUpUser(
                tempLogin,
                new Credentials().emailQaart1,
                tempPass,
                LocalDate.of(1990, 3, 19)
        );
        UserRegistration.registerUserEmailConfirmed(newUser);
        GlobalNavigation nav = new PostsListPage().open().getGlobalNavigation();
        nav.clickOnSignIn().login(tempLogin, tempPass);

        new ArticleContent().push(articleName, articleName);
        article.open(articleName).openCKModeWithMainEditButton().clearContent().clickPublishButton().logOut();

        //Staff user creates an announcements
        article.loginAs(User.STAFF);
        new AnnouncementsPage().open().postNewAnnouncement(announcement, announcementUrl);
        nav.clickAvatarAndSignOut();

        //Regular user logs in and gets an announcement
        article.open(articleName).getGlobalNavigation().clickOnSignIn().login(tempLogin, tempPass);
        Assertion.assertTrue(
                new OnSiteNotifications().showMessagesNotifications().isNotificationWithGivenTitleVisible(announcement)
        );
    }

    @Test(groups = "Announcements_Desktop")
    @Execute(asUser = User.ANONYMOUS)
    public void regularUserDoesNotHaveAccessToAnnouncements() {
        Assertion.assertTrue(new AnnouncementsPage().open().isErrorMessageVisible());
    }

    @Test(groups = "Announcements_Desktop")
    @Execute(asUser = User.STAFF)
    public void staffHasAccessToAnnouncements() {
        verifyLayout();
    }

    @Test(groups = "Announcements_Desktop")
    @Execute(asUser = User.VSTF)
    public void vstfHasAccessToAnnouncements() {
        verifyLayout();
    }

    @Test(groups = "Announcements_Desktop")
    @Execute(asUser = User.HELPER)
    public void helperHasAccessToAnnouncements() {
        verifyLayout();
    }

    @Test(groups = "Announcements_Desktop")
    @Execute(asUser = User.DISCUSSIONS_MODERATOR, onWikia = MobileWikis.DISCUSSIONS_2)
    public void discussionsModHasAccessToAnnouncements() {
        verifyLayout();
    }

    @Test(groups = "Announcements_Desktop")
    @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR, onWikia = MobileWikis.DISCUSSIONS_5)
    public void discussionsAdminHasAccessToAnnouncements() {
        verifyLayout();
    }

    /*
        HELPERS
     */

    public void verifyLayout() {
        AnnouncementsPage page = new AnnouncementsPage().open();
        Assertion.assertTrue(page.isPageHeaderVisible());
        Assertion.assertTrue(page.isAboutAnnouncementsVisible());
        Assertion.assertTrue(page.isAnnouncementFormVisible());
        Assertion.assertTrue(page.isPastAnnouncementsListVisible());
    }

}
