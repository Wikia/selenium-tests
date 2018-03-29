package com.wikia.webdriver.common.core.imageutilities;

import com.wikia.webdriver.common.driverprovider.DriverProvider;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.coordinates.Coords;
import ru.yandex.qatools.ashot.coordinates.CoordsProvider;

import static java.lang.Math.toIntExact;

public class SzogiCoordsProvider extends CoordsProvider {

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

            return new Coords(
//                    offsetLeft/dpr,
//                    offsetTop/dpr,
//                    dimension.getWidth()/dpr,
//                    dimension.getHeight()/dpr);
                    offsetLeft,
                    offsetTop,
                    dimension.getWidth(),
                    dimension.getHeight());
        }
    }
