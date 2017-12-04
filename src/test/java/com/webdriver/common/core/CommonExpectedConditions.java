package com.webdriver.common.core;

import com.webdriver.common.core.imageutilities.Shooter;
import com.webdriver.common.core.imageutilities.ImageComparison;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommonExpectedConditions {

  private final static Logger LOGGER = Logger.getLogger(ExpectedConditions.class.getName());

  private CommonExpectedConditions() {

  }

  /**
   * An expectation for checking if the given text is present in the specified element.
   */
  public static ExpectedCondition<Boolean> valueToBePresentInElementsAttribute(final By locator,
      final String attribute, final String value) {

    return new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver from) {
        try {
          String elementsAttributeValue = findElement(locator, from).getAttribute(attribute);
          return elementsAttributeValue != null && elementsAttributeValue.contains(value);
        } catch (StaleElementReferenceException e) {
          return false;
        }
      }

      @Override
      public String toString() {
        return String.format("value ('%s') to be present in element found by %s", value, locator);
      }
    };
  }

  /**
   * An expectation for checking if the given text is present in the specified element.
   */
  public static ExpectedCondition<Boolean> valueToBePresentInElementsAttribute(
      final WebElement element, final String attribute, final String value) {

    return new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver from) {
        try {
          String elementsAttributeValue = element.getAttribute(attribute);
          return elementsAttributeValue.contains(value);
        } catch (StaleElementReferenceException e) {
          return null;
        }
      }

      @Override
      public String toString() {
        return String.format("value ('%s') to be present in element found by %s", value,
                             element.getTagName());
      }
    };
  }

  /**
   * An expectation for checking if the given text is present in the specified element.
   */
  public static ExpectedCondition<Boolean> valueToBeNotPresentInElementsAttribute(
      final WebElement element, final String attribute, final String value) {

    return new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver from) {
        try {
          String elementsAttributeValue = element.getAttribute(attribute);
          return !elementsAttributeValue.contains(value);
        } catch (StaleElementReferenceException e) {
          return null;
        }
      }

      @Override
      public String toString() {
        return String.format("value ('%s') to not be present in element stopped being set in %s",
                             value, element.getTagName());
      }
    };
  }

  /**
   * An expectation for checking if the page URL contains givenString
   */
  public static ExpectedCondition<Boolean> givenStringtoBePresentInURL(final String givenString) {

    return new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver driver) {
        Boolean contains;
        contains = driver.getCurrentUrl().contains(givenString);
        return contains;
      }
    };
  }

  /**
   * An Expectation for checking an element is visible and not enabled such that you can not click
   * it.
   *
   * @param givenElement element to be checked
   */
  public static ExpectedCondition<WebElement> elementNotToBeClickable(final WebElement givenElement) {
    return new ExpectedCondition<WebElement>() {

      public ExpectedCondition<WebElement> visibilityOfElement = ExpectedConditions
          .visibilityOf(givenElement);

      public WebElement apply(WebDriver driver) {
        WebElement element = visibilityOfElement.apply(driver);
        try {
          if (element != null && !element.isEnabled()) {
            return element;
          } else {
            return null;
          }
        } catch (StaleElementReferenceException e) {
          return null;
        }
      }

      @Override
      public String toString() {
        return "element to be clickable: " + givenElement.getTagName();
      }
    };
  }

  /**
   * An expectation for checking if the given text is present in the specified element.
   */
  public static ExpectedCondition<Boolean> textToBePresentInElement(final WebElement givenElement,
      final String text) {

    return new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver driver) {
        String elementText = givenElement.getText();
        return elementText.contains(text);
      }

      @Override
      public String toString() {
        return String.format("text ('%s') to be present in element %s", text,
            givenElement.getTagName());
      }
    };
  }

  /**
   * An expectation for checking if the given text is not present in the specified element.
   */
  public static ExpectedCondition<Boolean> textToBeNotPresentInElement(final WebElement givenElement,
                                                                    final String text) {

    return new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver driver) {
        String elementText = givenElement.getText();
        return !elementText.contains(text);
      }

      @Override
      public String toString() {
        return String.format("text ('%s') to be not present in element %s", text,
                             givenElement.getTagName());
      }
    };
  }

  public static ExpectedCondition<Boolean> textToBePresentInElement(
      final List<WebElement> givenElement, final int index, final String text) {

    return new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver driver) {
        String elementText = givenElement.get(index).getText();
        return elementText.contains(text);
      }

      @Override
      public String toString() {
        return String.format("text ('%s') to be present in element %s", text,
            givenElement.get(index).getTagName());
      }
    };
  }

  /**
   * An expectation for checking if the given text is present in the specified element.
   */
  public static ExpectedCondition<Boolean> textToBePresentInElement(final By selectorBy,
      final String text) {

    return new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver driver) {
        String elementText = driver.findElement(selectorBy).getText();
        return elementText.contains(text);
      }

      @Override
      public String toString() {
        return String
            .format("text ('%s') to be present in element %s", text, selectorBy.toString());
      }
    };
  }

  /**
   * An expectation for checking if the given text is not present in the specified element.
   */
  public static ExpectedCondition<Boolean> textToBeNotPresentInElement(final By selectorBy,
                                                                    final String text) {

    return new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver driver) {
        String elementText = driver.findElement(selectorBy).getText();
        return !elementText.contains(text);
      }

      @Override
      public String toString() {
        return String
            .format("text ('%s') to be not present in element %s", text, selectorBy.toString());
      }
    };
  }



  /**
   * An expectation for checking if the given text is present in the specified element.
   */
  public static ExpectedCondition<Boolean> textToBePresentInElement(final By selectorBy,
      final int index, final String text) {

    return new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver driver) {
        String elementText = driver.findElements(selectorBy).get(index).getText();
        return elementText.contains(text);
      }

      @Override
      public String toString() {
        return String
            .format("text ('%s') to be present in element %s", text, selectorBy.toString());
      }
    };
  }

  public static ExpectedCondition<Boolean> textToBePresentInElementAfterRefresh(final WebElement element,
                                                                                final String text) {

    return new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver driver) {
        driver.navigate().refresh();
        String elementText = element.getText();
        return elementText.contains(text);
      }

      @Override
      public String toString() {
        return String
                .format("text ('%s') to be present in element %s", text, element.toString());
      }
    };
  }

  public static ExpectedCondition<Boolean> textToBePresentInElementAfterRefresh(final By selectorBy,
                                                                    final String text) {

    return new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver driver) {
        driver.navigate().refresh();
        WebElement element = driver.findElement(selectorBy);
        return element.getText().contains(text);
      }

      @Override
      public String toString() {
        return String
                .format("text ('%s') to be present in element %s", text, selectorBy.toString());
      }
    };
  }

  /**
   * Looks up an element. Logs and re-throws WebDriverException if thrown.
   * <p/>
   * Method exists to gather data for http://code.google.com/p/selenium/issues/detail?id=1800
   */
  private static WebElement findElement(By by, WebDriver driver) {
    try {
      return driver.findElement(by);
    } catch (NoSuchElementException e) {
      throw e;
    } catch (WebDriverException e) {
      LOGGER.log(Level.WARNING, String.format("WebDriverException thrown by findElement(%s)", by),
          e);
      throw e;
    }
  }

  public static ExpectedCondition<Boolean> invisibilityOfElementLocated(final WebElement element) {
    return new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver driver) {
        try {
          driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
          Boolean isDisplayed = element.isDisplayed();
          driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
          return !isDisplayed;
        } catch (StaleElementReferenceException e) {
          // Returns true because stale element reference implies that element
          // is no longer visible.
          return true;
        } catch (NoSuchElementException e) {
          driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
          return true;
        }
      }

      @Override
      public String toString() {
        return "element to no longer be visible: " + element.toString();
      }
    };
  }

  public static ExpectedCondition<Boolean> elementNotPresent(final By bySelector) {
    return new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver driver) {
        return (driver.findElements(bySelector).size() < 1);
      }

      @Override
      public String toString() {
        return String.format("Element with provided selector still present!");
      }
    };
  }


  public static ExpectedCondition<Boolean> elementInViewPort(final WebElement element) {
    return new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver driver) {
        Dimension size = element.getSize();
        Point location = element.getLocation();
        if (((size.height + location.y) > -1) && (size.width + location.x > -1)) {
          return true;
        }
        return false;
      }

      @Override
      public String toString() {
        return String.format("Element ('%s') not in viewport!", element.getTagName());
      }
    };
  }

  public static ExpectedCondition<Boolean> newWindowPresent() {
    return new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver driver) {
        Object[] windows = driver.getWindowHandles().toArray();
        return (windows.length > 1);
      }

      @Override
      public String toString() {
        return String.format("New window not found");
      }
    };
  }

  public static ExpectedCondition<Boolean> elementToHaveSize(final WebElement element,
      final int width, final int height) {
    return new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver driver) {
        return element.getSize().getWidth() == width && element.getSize().getHeight() == height;
      }

      @Override
      public String toString() {
        return String.format("#%s element. Expected size: [%s, %s], Actual size: [%s, %s]", element
            .getAttribute("id"), width, height, element.getSize().getWidth(), element.getSize()
            .getHeight());
      }
    };
  }

  /**
   * @param accuracy in percentage between 0 and 100.
   */
  public static ExpectedCondition<Boolean> elementToHaveColor(final WebElement element,
      final Color color, final int accuracy) {
    final Shooter shooter = new Shooter();
    final ImageComparison imageComparison = new ImageComparison();
    return new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver driver) {
        BufferedImage image = shooter.takeScreenshot(element, driver);
        return imageComparison.isColorImage(image, color, accuracy);
      }

      @Override
      public String toString() {
        return String.format("At least %s percents of element does not have %s color",
            (100 - accuracy), color.toString());
      }
    };
  }

  public static ExpectedCondition<Boolean> cssValuePresentForElement(final By bySelector,
      final String cssProperty, final String expectedValue) {
    return new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver driver) {
        return expectedValue.equals(driver.findElement(bySelector).getCssValue(cssProperty));
      }
    };
  }
}
