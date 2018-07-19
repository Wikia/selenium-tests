package com.wikia.webdriver.common.core.imageutilities;

import static ru.yandex.qatools.ashot.coordinates.Coords.setReferenceCoords;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.Coords;
import ru.yandex.qatools.ashot.cropper.DefaultCropper;
import ru.yandex.qatools.ashot.cropper.ImageCropper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Set;

public class CustomImageCropper extends DefaultCropper {

  Coords customCoords;

  @Override
  public Screenshot cropScreenshot(BufferedImage image, Set<Coords> coordsToCompare) {
    Coords cropArea = customCoords;

    Coords imageIntersection = Coords.ofImage(image).intersection(cropArea);

    if (imageIntersection.isEmpty()) {
      return new Screenshot(image);
    }

    BufferedImage cropped = new BufferedImage(imageIntersection.width,
                                              imageIntersection.height,
                                              image.getType()
    );
    Graphics g = cropped.getGraphics();
    g.drawImage(image,
                0,
                0,
                imageIntersection.width,
                imageIntersection.height,
                cropArea.x,
                cropArea.y,
                cropArea.x + imageIntersection.width,
                cropArea.y + imageIntersection.height,
                null
    );
    g.dispose();
    Screenshot screenshot = new Screenshot(cropped);
    screenshot.setOriginShift(cropArea);
    screenshot.setCoordsToCompare(setReferenceCoords(screenshot.getOriginShift(),
                                                     Collections.singleton(customCoords)
    ));

    return screenshot;
  }

  public Screenshot customCropScreenshot(BufferedImage image, Point start, Dimension size) {
    Coords cropArea = new Coords(start.x, start.y, size.width, size.height);

    Coords imageIntersection = Coords.ofImage(image).intersection(cropArea);

    if (imageIntersection.isEmpty()) {
      return new Screenshot(image);
    }

    BufferedImage cropped = new BufferedImage(imageIntersection.width,
                                              imageIntersection.height,
                                              image.getType()
    );
    Graphics g = cropped.getGraphics();
    g.drawImage(image,
                0,
                0,
                imageIntersection.width,
                imageIntersection.height,
                cropArea.x,
                cropArea.y,
                cropArea.x + imageIntersection.width,
                cropArea.y + imageIntersection.height,
                null
    );
    g.dispose();
    Screenshot screenshot = new Screenshot(cropped);
    screenshot.setOriginShift(cropArea);
    screenshot.setCoordsToCompare(setReferenceCoords(screenshot.getOriginShift(),
                                                     Collections.singleton(cropArea)
    ));
    return screenshot;
  }

  public ImageCropper setCoordinates(Point start, Dimension size) {
    customCoords = new Coords(start.x, start.y, size.getWidth(), size.getHeight());
    return this;
  }
}
