package com.wikia.webdriver.testcases.announcements;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.api.UserRegistration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.SignUpUser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.OnSiteNotifications;
import com.wikia.webdriver.pageobjectsfactory.pageobject.AnnouncementsPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigation;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.time.LocalDate;


@InBrowser(browser = Browser.CHROME)
@Execute(onWikia = MercuryWikis.DISCUSSIONS_7)
@Test(groups = "Announcements_Desktop")
public class AnnouncementsDesktopTests extends NewTestTemplate {

    @Test()
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

}
