package com.wikia.webdriver.Common.Core;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.wikia.webdriver.Common.Core.ImageUtilities.ImageComparison;
import com.wikia.webdriver.Common.Core.ImageUtilities.Shooter;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.wikia.webdriver.Common.DriverProvider.DriverProvider;

public class CommonExpectedConditions {

	private final static Logger LOG = Logger.getLogger(ExpectedConditions.class.getName());

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
			LOG.log(Level.WARNING,
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
					driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
					return !isDisplayed;
				}
				catch (StaleElementReferenceException e) {
					// Returns true because stale element reference implies that element
					// is no longer visible.
					return true;
				}
				catch (NoSuchElementException e) {
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

	/**
	 *
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

	public static ExpectedCondition<Boolean> elementInViewPort (
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
					List <WebElement> tagsNodes = slot.findElements(
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

	public static ExpectedCondition<Boolean> elementToHaveSize(
		final WebElement element, final int width, final int height) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return element.getSize().getWidth() == width && element.getSize().getHeight() == height;
			}

			@Override
			public String toString() {
				return String.format(
						"#%s element. Expected size: [%s, %s], Actual size: [%s, %s]",
						element.getAttribute("id"),
						width, height, element.getSize().getWidth(), element.getSize().getHeight()
				);
			}
		};
	}

	/**
	 * @param accuracy in percentage between 0 and 100.
	 */
	public static ExpectedCondition<Boolean> elementToHaveColor(final WebElement element, final Color color,
																final int accuracy) {
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
				return String.format(
						"At least %s percents of element does not have %s color",
						(100 - accuracy), color.toString()
				);
			}
		};
	}
}
