package com.wikia.webdriver.pageobjectsfactory.componentobject.flag;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mariusz on 2015-07-01.
 */
public class AddFlagsComponentObject extends WikiBasePageObject {
    public AddFlagsComponentObject(WebDriver driver) {
        super(driver);
    }
    private List list = new ArrayList();
    private List<WebElement> inputs = getListFlags();
    @FindBy(css = "#WikiaPageHeader > nav.wikia-menu-button > span.drop")
    private WebElement editButtonFlag;
    @FindBy(css = "#ca-flags")
    private WebElement flagButton;
    @FindBy(css = "#flagsEditForm ul input[type=checkbox]")
    private List<WebElement> allFlags;
    @FindBy(xpath = "//footer/div/button")
    private WebElement doneButton;

    public void clickFlagButton() {
        waitForElementByElement(editButtonFlag);
        editButtonFlag.click();
        waitForElementByElement(flagButton);
        flagButton.click();
    }

    public List<WebElement> getListFlags() {
        return allFlags;
    }

    public void clickDoneButton() {
        waitForElementByElement(doneButton);
        doneButton.click();
    }

    public void selectFlagsInModal() {
        for (WebElement input : inputs) {
          if (!input.isSelected())
            input.click();
            list.add(input.getAttribute("id"));
         }
    }

    public void checkFlagsEnabling() {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            String rep;
            rep = (String) iterator.next();
            rep = rep.replaceFirst("\\D+", "");
            waitForElementByCss("[data-type-id=\"" + rep + "\"]").isEnabled();
        }
    }
}
