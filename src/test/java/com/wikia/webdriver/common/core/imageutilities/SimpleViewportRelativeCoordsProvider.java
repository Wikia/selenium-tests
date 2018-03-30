package com.wikia.webdriver.common.core.imageutilities;

import com.wikia.webdriver.common.driverprovider.DriverProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.coordinates.Coords;
import ru.yandex.qatools.ashot.coordinates.CoordsProvider;

import static java.lang.Math.toIntExact;

public class SimpleViewportRelativeCoordsProvider extends CoordsProvider {

    private WebDriver driver = DriverProvider.getActiveDriver();
    private JavascriptExecutor js = (JavascriptExecutor) driver;

    @Override
    public Coords ofElement(WebDriver driver, WebElement element) {

        Dimension dimension = element.getSize();
        int offsetTop = toIntExact((Long) js.executeScript("return parseInt(arguments[0].getBoundingClientRect().top)", element));
        int offsetLeft = toIntExact((Long) js.executeScript("return parseInt(arguments[0].getBoundingClientRect().left)", element));

        Coords coords = new Coords(
                offsetLeft,
                offsetTop,
                dimension.getWidth(),
                dimension.getHeight()
        );

        PageObjectLogging.log("SzogiJqueryCoordsProvider",
                "Start: " + coords.x + "x" + coords.y + " , size: " + coords.width + "x" + coords.height,
                true);

        return coords;
    }
}
