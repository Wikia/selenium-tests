package com.wikia.webdriver.Common.Core;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.wikia.webdriver.Common.DriverProvider.DriverProvider;

public class CommonExpectedConditions {

	private final static Logger log = Logger.getLogger(ExpectedConditions.class.getName());

	private static WebDriver driver = DriverProvider.getWebDriver();


	/**
	 * An expectation for checking if the given text is present in the specified
	 * element.
	 *
	 * @author Michal Nowierski
	 */
	public static ExpectedCondition<Boolean> valueToBePresentInElementsAttribute(
			final By locator, final String attribute, final String value) {

		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver from) {
				try {
					String elementsAttributeValue = findElement(locator, from).getAttribute(attribute);
					return elementsAttributeValue.contains(value);
				} catch (StaleElementReferenceException e) {
					return null;
				}
			}

			@Override
			public String toString() {
				return String.format("value ('%s') to be present in element found by %s",
						value, locator);
			}
		};
	}

	/**
	 * An expectation for checking if the given text is present in the specified
	 * element.
	 *
	 * @author Michal Nowierski
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
				return String.format("value ('%s') to be present in element found by %s",
						value, element.getTagName());
			}
		};
	}

	/**
	 * An expectation for checking if the page URL contains givenString
	 *
	 * @author Michal Nowierski
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
	 * An Expectation for checking an element is visible and enabled such that you
	 * can click it.
	 *
	 * @param GivenElement element to be checked
	 * @author Michal Nowierski
	 */
	public static ExpectedCondition<WebElement> elementToBeClickable(
			final WebElement GivenElement) {
		return new ExpectedCondition<WebElement>() {

			public ExpectedCondition<WebElement> visibilityOfElement =
					ExpectedConditions.visibilityOf(GivenElement);

			public WebElement apply(WebDriver driver) {
				WebElement element = visibilityOfElement.apply(driver);
				try {
					if (element != null && element.isEnabled()) {
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
				return "element to be clickable: " + GivenElement.getTagName();
			}
		};
	}

	/**
	 * An Expectation for checking an element is visible and not enabled such that you
	 * can not click it.
	 *
	 * @param GivenElement element to be checked
	 * @author Michal Nowierski
	 */
	public static ExpectedCondition<WebElement> elementNotToBeClickable(
			final WebElement GivenElement) {
		return new ExpectedCondition<WebElement>() {

			public ExpectedCondition<WebElement> visibilityOfElement =
					ExpectedConditions.visibilityOf(GivenElement);

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
				return "element to be clickable: " + GivenElement.getTagName();
			}
		};
	}

	/**
	 * An expectation for checking if the given text is present in the specified
	 * element.
	 */
	public static ExpectedCondition<Boolean> textToBePresentInElement(
			final WebElement GivenElement, final String text) {

		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				String elementText = GivenElement.getText();
				return elementText.contains(text);
			}

			@Override
			public String toString() {
				return String.format("text ('%s') to be present in element %s",
						text, GivenElement.getTagName());
			}
		};
	}

	/**
	 * An expectation for checking if the given text is present in the specified
	 * element.
	 */
	public static ExpectedCondition<Boolean> textToBePresentInElement(
			final By selectorBy, final String text) {

		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				String elementText = driver.findElement(selectorBy).getText();
				return elementText.contains(text);
			}

			@Override
			public String toString() {
				return String.format("text ('%s') to be present in element %s",
						text, selectorBy.toString());
			}
		};
	}
	/**
	 * An expectation for checking if the given text is present in one of matched elements
	 */
	public static ExpectedCondition<Boolean> textToBePresentInOneOfTElements(
			final By selectorBy, final String text) {

		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				List<WebElement> elements = driver.findElements(selectorBy);
				for( WebElement element: elements ) {
					if ( element.getText().contains(text) ) {
						return true;
					}
				}
				return false;
			}

			@Override
			public String toString() {
				return String.format("text ('%s') to be present in on of elements matched by %s",
						text, selectorBy.toString());
			}
		};
	}
	/**
	 * An expectation for checking if the given text is present in the specified
	 * element.
	 */
	public static ExpectedCondition<Boolean> textNotPresentInElement(
			final WebElement GivenElement, final String text) {

		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				String elementText = GivenElement.getText();
				return !elementText.contains(text);
			}

			@Override
			public String toString() {
				return String.format("text ('%s') to be present in element %s",
						text, GivenElement.getTagName());
			}
		};
	}



	/**
	 * Looks up an element. Logs and re-throws WebDriverException if thrown. <p/>
	 * Method exists to gather data for http://code.google.com/p/selenium/issues/detail?id=1800
	 */
	private static WebElement findElement(By by, WebDriver driver) {
		try {
			return driver.findElement(by);
		} catch (NoSuchElementException e) {
			throw e;
		} catch (WebDriverException e) {
			log.log(Level.WARNING,
					String.format("WebDriverException thrown by findElement(%s)", by), e);
			throw e;
		}
	}

	public static ExpectedCondition<Boolean> invisibilityOfElementLocated(
			final WebElement element) {
		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
					Boolean isDisplayed = element.isDisplayed();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					return !isDisplayed;
				} catch (StaleElementReferenceException e) {
					// Returns true because stale element reference implies that element
					// is no longer visible.
					return true;
				} catch (NoSuchElementException e) {
					return true;
				}
			}

			@Override
			public String toString() {
				return "element to no longer be visible: " + element.toString();
			}
		};
	}

	/**
	 * @param bySelector
	 * @return
	 */
	public static ExpectedCondition<Boolean> elementNotPresent(
			final By bySelector
	) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (driver.findElements(bySelector).size() < 1);
			}

			@Override
			public String toString() {
				return String.format(
						"Element with provided selector still present!"
				);
			}
		};
	}


	public static ExpectedCondition<Boolean> elementVisible(
			final WebElement element
	) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return element.isDisplayed();
				} catch (StaleElementReferenceException e) {
					// Returns false because stale element implies that element
					// is still not visible.
					return false;
				} catch (NoSuchElementException e) {
					return false;
				}
			}

			@Override
			public String toString() {
				return String.format(
						"Element ('%s') not visisble!",
						element.getTagName()
				);
			}
		};
	}

	public static ExpectedCondition<Boolean> elementInViewPort(
			final WebElement element
	) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				Dimension size = element.getSize();
				Point location = element.getLocation();
				if (((size.height + location.y) > -1)
						&& (size.width + location.x > -1)) {
					return true;
				}
				return false;
			}

			@Override
			public String toString() {
				return String.format(
						"Element ('%s') not in viewport!",
						element.getTagName()
				);
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
				return String.format(
						"New window not found"
				);
			}
		};
	}

	public static ExpectedCondition<Boolean> oneOfTagsPresentInElement(
			final WebElement slot, final String tagNameOne, final String tagNameTwo
	) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				Dimension zero = new Dimension(0, 0);
				Dimension one = new Dimension(1, 1);
				List<WebElement> tagsNodes = slot.findElements(
						By.cssSelector(tagNameOne + "," + tagNameTwo));
				for (WebElement tagNode : tagsNodes) {
					if (
							tagNode.getSize() != zero
									&& tagNode.getSize() != one
									&& tagNode.isDisplayed()
							) {
						return true;
					}
				}
				return false;
			}

			@Override
			public String toString() {
				return String.format(
						"%s tag or %s that matches the criteria were not found!",
						tagNameOne, tagNameTwo
				);
			}
		};
	}

	public static ExpectedCondition<Boolean> pageLoaded() {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}

			@Override
			public String toString() {
				return "Page loaded";
			}
		};
	}
}
