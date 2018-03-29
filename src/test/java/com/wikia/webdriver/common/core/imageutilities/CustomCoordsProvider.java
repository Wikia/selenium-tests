package com.wikia.webdriver.common.core.imageutilities;

import com.wikia.webdriver.common.driverprovider.DriverProvider;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.coordinates.Coords;
import ru.yandex.qatools.ashot.coordinates.CoordsProvider;

import static java.lang.Math.toIntExact;

public class CustomCoordsProvider extends CoordsProvider {

        private WebDriver driver = DriverProvider.getActiveDriver();
        private JavascriptExecutor js = (JavascriptExecutor) driver;
        //TODO:Rewrite it
        private ImageEditor imageEditor = new ImageEditor();
        private int dpr = imageEditor.getDevicePixelRatio();

        @Override
        public Coords ofElement(WebDriver driver, WebElement element) {

            Dimension dimension = element.getSize();
            int offsetTop = toIntExact((Long)js.executeScript("return parseInt(arguments[0].getBoundingClientRect().top)", element));
            int offsetLeft = toIntExact((Long)js.executeScript("return parseInt(arguments[0].getBoundingClientRect().left)", element));

            //  private Object[] getBoundingClientRect(WebElement element, WebDriver driver) {
//    JavascriptExecutor js = (JavascriptExecutor) driver;
//    ArrayList<String> list = (ArrayList<String>) js.executeScript(
//        "var rect =  arguments[0].getBoundingClientRect();" +
//        "return [ '' + parseInt(rect.left), '' + parseInt(rect.top), '' + parseInt(rect.width), '' + parseInt(rect.height) ]",
//        element
//    );
//    Point start = new Point(Integer.parseInt(list.get(0)), Integer.parseInt(list.get(1)));
//    Dimension size = new Dimension(Integer.parseInt(list.get(2)), Integer.parseInt(list.get(3)));
//    return new Object[]{start, size};

            return new Coords(
                    offsetLeft,
                    offsetTop,
                    dimension.getWidth(),
                    dimension.getHeight());
        }
    }
