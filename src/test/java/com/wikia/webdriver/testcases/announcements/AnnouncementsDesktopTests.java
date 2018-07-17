package com.wikia.webdriver.testcases.announcements;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import org.testng.annotations.Test;


@InBrowser(browser = Browser.CHROME)
@Execute(onWikia = MercuryWikis.DISCUSSIONS_7)
@Test(groups = "Announcements_Desktop")
public class AnnouncementsDesktopTests extends NewTestTemplate {

    @Test
    public void communityNewUserCanGetFreshAnnouncement() {



    }

}
