package com.wikia.webdriver.elements.common;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;

/**
 * This interface wraps WebElement to provide only get methods.
 * Can be used to expose WebElements to a higher level API without risking them changing from use.
 */
public interface ViewOnlyWebElement {

  /**
   * Get the tag name of this element. <b>Not</b> the value of the name attribute: will return
   * <code>"input"</code> for the element <code>&lt;input name="foo" /&gt;</code>.
   *
   * @return The tag name of this element.
   */
  String getTagName();

  /**
   * Get the value of the given attribute of the element. Will return the current value, even if
   * this has been modified after the page has been loaded.
   *
   * <p>More exactly, this method will return the value of the property with the given name, if it
   * exists. If it does not, then the value of the attribute with the given name is returned. If
   * neither exists, null is returned.
   *
   * <p>The "style" attribute is converted as best can be to a text representation with a trailing
   * semi-colon.
   *
   * <p>The following are deemed to be "boolean" attributes, and will return either "true" or null:
   *
   * <p>async, autofocus, autoplay, checked, compact, complete, controls, declare, defaultchecked,
   * defaultselected, defer, disabled, draggable, ended, formnovalidate, hidden, indeterminate,
   * iscontenteditable, ismap, itemscope, loop, multiple, muted, nohref, noresize, noshade,
   * novalidate, nowrap, open, paused, pubdate, readonly, required, reversed, scoped, seamless,
   * seeking, selected, truespeed, willvalidate
   *
   * <p>Finally, the following commonly mis-capitalized attribute/property names are evaluated as
   * expected:
   *
   * <ul>
   * <li>If the given name is "class", the "className" property is returned.
   * <li>If the given name is "readonly", the "readOnly" property is returned.
   * </ul>
   *
   * <i>Note:</i> The reason for this behavior is that users frequently confuse attributes and
   * properties. If you need to do something more precise, e.g., refer to an attribute even when a
   * property of the same name exists, then you should evaluate Javascript to obtain the result
   * you desire.
   *
   * @param name The name of the attribute.
   * @return The attribute/property's current value or null if the value is not set.
   */
  String getAttribute(String name);

  /**
   * Determine whether or not this element is selected or not. This operation only applies to input
   * elements such as checkboxes, options in a select and radio buttons.
   * For more information on which elements this method supports,
   * refer to the <a href="https://w3c.github.io/webdriver/webdriver-spec.html#is-element-selected">specification</a>.
   *
   * @return True if the element is currently selected or checked, false otherwise.
   */
  boolean isSelected();

  /**
   * Is the element currently enabled or not? This will generally return true for everything but
   * disabled input elements.
   *
   * @return True if the element is enabled, false otherwise.
   */
  boolean isEnabled();

  /**
   * Get the visible (i.e. not hidden by CSS) text of this element, including sub-elements.
   *
   * @see <a href="https://w3c.github.io/webdriver/#get-element-text">"Get Element Text" section
   * in W3C WebDriver Specification</a>
   * @return The visible text of this element.
   */
  String getText();

  /**
   * Is this element displayed or not? This method avoids the problem of having to parse an
   * element's "style" attribute.
   *
   * @return Whether or not the element is displayed
   */
  boolean isDisplayed();

  /**
   * Where on the page is the top left-hand corner of the rendered element?
   *
   * @return A point, containing the location of the top left-hand corner of the element
   */
  Point getLocation();

  /**
   * What is the width and height of the rendered element?
   *
   * @return The size of the element on the page.
   */
  Dimension getSize();

  /**
   * @return The location and size of the rendered element
   */
  Rectangle getRect();

  /**
   * Get the value of a given CSS property.
   * Color values should be returned as rgba strings, so,
   * for example if the "background-color" property is set as "green" in the
   * HTML source, the returned value will be "rgba(0, 255, 0, 1)".
   *
   * Note that shorthand CSS properties (e.g. background, font, border, border-top, margin,
   * margin-top, padding, padding-top, list-style, outline, pause, cue) are not returned,
   * in accordance with the
   * <a href="http://www.w3.org/TR/DOM-Level-2-Style/css.html#CSS-CSSStyleDeclaration">DOM CSS2 specification</a>
   * - you should directly access the longhand properties (e.g. background-color) to access the
   * desired values.
   *
   * @param propertyName the css property name of the element
   * @return The current, computed value of the property.
   */
  String getCssValue(String propertyName);
}
