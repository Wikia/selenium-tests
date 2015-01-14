package com.wikia.webdriver.common.core.imageutilities;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Class containing methods responsible
 * for comparing images using different algorithms.
 *
 * @author Bogna 'bognix' Knychala
 */
public class ImageComparison {

	/**
	 * Compare two images after converting them into byte arrays
	 *
	 * @param File file1 - file containing first image
	 * @param File file2 - file containing second image
	 * @return boolean   - if images are the same
	 */
	public boolean areFilesTheSame(File file1, File file2) {
		byte[] fileInBytes1 = null;
		byte[] fileInBytes2 = null;
		try {
			fileInBytes1 = FileUtils.readFileToByteArray(file1);
			fileInBytes2 = FileUtils.readFileToByteArray(file2);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if (Arrays.equals(fileInBytes1, fileInBytes2)) {
			return true;
		}
		return false;
	}

	public boolean areBase64StringsTheSame(String base1, String base2) {
		Base64 coder = new Base64();
		byte[] baseInBytes1 = coder.decode(base1);
		byte[] baseInBytes2 = coder.decode(base2);
		if (Arrays.equals(baseInBytes1, baseInBytes2)) {
			return true;
		}
		return false;
	}

	/**
	 * @param accuracy in percentage between 0 and 100.
	 */
	public boolean isColorImage(BufferedImage image, Color color, int accuracy) {
		int count = image.getHeight() * image.getWidth();
		;
		int diffCount = 0;
		for (int row = 0; row < image.getWidth(); row++) {
			for (int column = 0; column < image.getHeight(); column++) {
				if (image.getRGB(row, column) != color.getRGB()) {
					diffCount += 1;
				}
			}
			if (diffCount > ((100 - accuracy) * count) / 100D) {
				return false;
			}
		}
		return true;
	}
}
