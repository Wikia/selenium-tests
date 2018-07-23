package com.wikia.webdriver.common.core.imageutilities;

import com.wikia.webdriver.common.logging.Log;

import org.openqa.selenium.*;
import ru.yandex.qatools.ashot.coordinates.Coords;
import ru.yandex.qatools.ashot.coordinates.CoordsProvider;

import java.util.ArrayList;

public class SimpleViewportRelativeCoordsProvider extends CoordsProvider {

  @Override
  public Coords ofElement(WebDriver driver, WebElement element) {

    Dimension dimension = element.getSize();
    Point start = (Point) getBoundingClientRect(element, driver)[0];

    Coords coords = new Coords(start.x, start.y, dimension.getWidth(), dimension.getHeight());

    Log.log(
        "SimpleViewportRelativeCoordsProvider",
        "Start: " + coords.x + "x" + coords.y + " , size: " + coords.width + "x" + coords.height,
        true
    );
    return coords;
  }

  private Object[] getBoundingClientRect(WebElement element, WebDriver driver) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    ArrayList<String> list = (ArrayList<String>) js.executeScript(
        "var rect =  arguments[0].getBoundingClientRect();"
        + "return [ '' + parseInt(rect.left), '' + parseInt(rect.top), '' + parseInt(rect.width), '' + parseInt(rect.height) ]",
        element
    );
    Point start = new Point(Integer.parseInt(list.get(0)), Integer.parseInt(list.get(1)));
    Dimension size = new Dimension(Integer.parseInt(list.get(2)), Integer.parseInt(list.get(3)));
    return new Object[]{start, size};
  }
}
