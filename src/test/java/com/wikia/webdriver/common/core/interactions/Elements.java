package com.wikia.webdriver.common.core.interactions;

import com.wikia.webdriver.common.logging.LOG;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Ludwik on 2015-08-03.
 */
public class Elements {

  public static WebElement getElementByValue(List<WebElement> elements, String attribute,
      String value) {
    WebElement foundElement = null;
    for (WebElement element : elements) {
      String retAttribute = element.getAttribute(attribute);
      if ("href".equals(attribute)) {
        retAttribute =
            retAttribute.substring(retAttribute.indexOf("File:") + 5).replace("%20", " ");
        if (!element.getAttribute("class").contains("video")) {
          retAttribute = retAttribute.substring(0, retAttribute.indexOf('.'));
        }
      }
      if (value.equals(retAttribute)) {
        foundElement = element;
        LOG.log("getElementByValue", "Element with attribute: " + attribute
                                     + " with the value: " + value + " is found from the list",
                LOG.Type.SUCCESS);
        break;
      }
    }
    if (foundElement == null) {
      throw new NoSuchElementException("Element with attribute: " + attribute + " with the value: "
          + value + " is not found from the list");
    }
    return foundElement;
  }

  public static WebElement getElementByText(List<WebElement> elements, String value) {
    WebElement foundElement = null;
    for (WebElement element : elements) {
      if (element.getText().equalsIgnoreCase(value)) {
        foundElement = element;
        LOG.log("getElementByText", "Element with text: " + value
                                    + " is found from the list", LOG.Type.SUCCESS);
        break;
      }
    }
    if (foundElement == null) {
      throw new NoSuchElementException("Element with text: " + value
          + " is not found from the list");
    }
    return foundElement;
  }

  public static WebElement getElementByChildText(List<WebElement> elements, By childBySelector,
      String value) {
    WebElement foundElement = null;
    for (WebElement element : elements) {
      if (element.findElement(childBySelector).getText().equalsIgnoreCase(value)) {
        foundElement = element;
        LOG.log("getElementByChildText", "Element's child with text: " + value
                                         + " is found from the list", LOG.Type.SUCCESS);
        break;
      }
    }
    if (foundElement == null) {
      throw new NoSuchElementException("Element's child with text: " + value
          + " is not found from the list");
    }
    return foundElement;
  }
}
