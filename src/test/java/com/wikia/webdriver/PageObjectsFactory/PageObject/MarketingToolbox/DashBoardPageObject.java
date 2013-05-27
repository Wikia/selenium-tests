package com.wikia.webdriver.PageObjectsFactory.PageObject.MarketingToolbox;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created with IntelliJ IDEA.
 * User: kvas
 * Date: 15.03.13
 * Time: 14:47
 * To change this template use File | Settings | File Templates.
 */
public class DashBoardPageObject extends BasePageObject {

    @FindBy(id="marketingToolboxRegionSelect")
    private WebElement regionDropDown;

    @FindBy(css="input[data-section-id=\"1\"]")
    private WebElement HubButton;

    @FindBy(css="input[data-vertical-id=\"2\"]")
    private WebElement VideoGamesButton;
    @FindBy(css="input[data-vertical-id=\"3\"]")
    private WebElement EntertainmentButton;
    @FindBy(css="input[data-vertical-id=\"9\"]")
    private WebElement LifestyleButton;

    @FindBys(@FindBy(css="#date-picker td:not(.published):not(.ui-datepicker-other-month):not(.inProg) a"))
    private WebElement ClearDaysLinks;

    public enum vertical {
        Video_games, Entertainment, LifeStyle
    }

    public DashBoardPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openDashboard() {
        getUrl(Global.DOMAIN + "Special:MarketingToolbox");
        PageObjectLogging.log("openDashboard", "dashboard opened", true);
    }

    public void selectLang(String langCode) {
        Select select = new Select(regionDropDown);
        select.selectByValue(langCode);
    }

    public void clickHub() {
        HubButton.click();
    }

    public void clickVertical(vertical vert) {
        WebElement inp = this.getVerticalInput(vert);
        inp.click();
    }

    public Boolean checkActiveVertical(vertical vert) {
        WebElement inp = this.getVerticalInput(vert);
        String className = inp.getAttribute("class");
        return !className.contains("secondary");
    }

    private WebElement getVerticalInput(vertical vert) {
        WebElement inp;
        switch (vert) {
            case Video_games:
                inp = VideoGamesButton;
                break;
            case Entertainment:
                inp = EntertainmentButton;
                break;
            case LifeStyle:
            default:
                inp = LifestyleButton;
                break;
        }
        return inp;
    }
}