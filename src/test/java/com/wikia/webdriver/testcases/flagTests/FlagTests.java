package com.wikia.webdriver.testcases.flagtests;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.flag.AddFlagsComponentObject;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mariusz on 2015-06-28.
 */
public class FlagTests extends NewTestTemplate {

    Credentials credentials = config.getCredentials();

    @Test(
            groups = {"addFlag_01"})
    public void addFlag_01() {
        List list = new ArrayList();
        AddFlagsComponentObject flag = new AddFlagsComponentObject(driver);
        flag.logInCookie(credentials.userNameStaff, credentials.passwordStaff,URLsContent.EXTERNAL_RUNESCAPE);
        flag.openArticleByName(URLsContent.EXTERNAL_RUNESCAPE,URLsContent.FLAGTEST);
        flag.openFlagModal();
        List<WebElement> inputs = flag.getListFlags();
        //take flags ID which are enabled on current wiki
        //we need to take id because next we check if flag is enabled on page and this need ID flag
        for (WebElement input : inputs){
              if(!input.isSelected())
                input.click();
              list.add(input.getAttribute("id"));
        }
        flag.doneButton();
        // change ID flags on numerical ID and check if it's enabled
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            String rep;
            rep = (String) iterator.next();
            rep = rep.replaceFirst("\\D+", "");
            flag.waitForElementByCss("[data-type-id=\"" + rep + "\"]").isEnabled();
        }
        PageObjectLogging.log("Verify flags", "All flags are visible", true);
    }



}
