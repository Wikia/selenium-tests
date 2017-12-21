package com.wikia.webdriver.pageobjectsfactory.componentobject.rte;

import com.wikia.webdriver.elements.mercury.pages.InfoboxBuilderPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The Infobox modal allows to insert an existing infobox (based on template classification)
 * or create a new one using the Infobox Builder.
 */
public class InfoboxChoiceModal {
  @FindBy(css = ".infobox-templates-list a")
  private List<WebElement> availableInfoboxList;

  @FindBy(css= ".infobox-dialog-button .cke_dialog_ui_button")
  private WebElement createNewInfoboxButton;

  private final WebDriver webDriver;
  private final Actions actions;

  InfoboxChoiceModal(WebDriver webDriver) {
    PageFactory.initElements(webDriver, this);
    this.webDriver = webDriver;
    this.actions = new Actions(webDriver);
  }

  /**
   * Select an infobox based on its name from the list of available infoboxes.
   * A modal window will be launched to manipulate the transclusion parameters.
   *
   * @param name name of the infobox to search for
   * @return wrapper for modal that allows editing template parameters
   * @throws NoSuchElementException if there is no available infobox with the given name
   */
  public TemplateConfigurationModal selectInfoboxByName(String name) {
    availableInfoboxList.stream()
        .filter(webElement -> webElement.getAttribute("data-infobox-name").equals(name))
        .findFirst()
        .orElseThrow(NoSuchElementException::new)
        .click();

    return new TemplateConfigurationModal(webDriver);
  }

  /**
   * Create a new infobox using the infobox builder flow.
   * @return wrapper object for infobox builder modal
   */
  public InfoboxBuilderPage clickCreateNewInfoboxButton() {
    actions.click(createNewInfoboxButton).perform();

    return new InfoboxBuilderPage();
  }
}
