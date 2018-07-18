package com.wikia.webdriver.testcases.announcements;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.UserRegistration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.SignUpUser;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.time.LocalDate;


@InBrowser(browser = Browser.CHROME)
@Execute(onWikia = MercuryWikis.DISCUSSIONS_7)
@Test(groups = "Announcements_Desktop")
public class AnnouncementsDesktopTests extends NewTestTemplate {

    @Test
    public void communityNewUserCanGetFreshAnnouncement() {
        SignUpUser newUser = new SignUpUser(
                "QA" + Long.toString(DateTime.now().getMillis()),
                new Credentials().emailQaart1,
                Long.toString(DateTime.now().getMillis()),
                LocalDate.of(1990, 3, 19)
        );
        UserRegistration.registerUserEmailConfirmed(newUser);


    }

}
