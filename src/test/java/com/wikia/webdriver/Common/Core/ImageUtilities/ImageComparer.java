package com.wikia.webdriver.Common.Core.ImageUtilities;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class ImageComparer {

	public boolean compareImagesBasedOnBytes(File file1, File file2) {
		byte[] fileInBytes1 = null;
		byte[] fileInBytes2 = null;
		try {
			fileInBytes1 = FileUtils.readFileToByteArray(file1);
			fileInBytes2 = FileUtils.readFileToByteArray(file2);
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
		boolean isTheSame = false;
		if (Arrays.equals(fileInBytes1, fileInBytes2)) {
			isTheSame = true;
		}
		return isTheSame;
	}
}
