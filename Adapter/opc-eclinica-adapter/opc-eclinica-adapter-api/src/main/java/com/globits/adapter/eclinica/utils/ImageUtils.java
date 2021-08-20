package com.globits.adapter.eclinica.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.springframework.http.MediaType;

/**
 * Image utilities functions
 * 
 * @author bizic
 */
public class ImageUtils
{

	private static final String EMPTY_IMAGE_NAME = "blank.jpg";

	private static final byte[] EMPTY_IMAGE_CONTENT;

	static {
		String filePath = ImageUtils.class.getClassLoader().getResource(EMPTY_IMAGE_NAME).getPath();
		File mediaFile = new File(filePath);

		EMPTY_IMAGE_CONTENT = FileUtils.readFile(mediaFile);
	}

	/**
	 * Resize an image to a given size
	 * 
	 * @param input
	 * @param size
	 * @return
	 */
	public static byte[] resize(File input, int size) {

		byte[] output = null;

		try {
			BufferedImage bi = ImageIO.read(input);
			bi = Scalr.resize(bi, Method.ULTRA_QUALITY, size);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", baos);

			baos.flush();
			output = baos.toByteArray();
			baos.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return output;
	}

	/**
	 * Crop an image...
	 * 
	 * @param input
	 * @param x
	 * @param y
	 * @param with
	 * @param height
	 * @return
	 */
	public static byte[] crop(byte[] input, int x, int y, int with, int height) {

		byte[] output = null;
		ByteArrayInputStream bais = new ByteArrayInputStream(input);

		try {
			BufferedImage bi = ImageIO.read(bais);
			bi = Scalr.crop(bi, x, y, with, height);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", baos);

			baos.flush();
			output = baos.toByteArray();
			baos.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return output;
	}

	/**
	 * Check if a file of contentType is an image file
	 * 
	 * @param contentType
	 * @return
	 */
	public static boolean isImage(String contentType) {
		return !CommonUtils.isEmpty(contentType) && (contentType.equalsIgnoreCase(MediaType.IMAGE_PNG_VALUE)
				|| contentType.equalsIgnoreCase(MediaType.IMAGE_JPEG_VALUE)
				|| contentType.equalsIgnoreCase(MediaType.IMAGE_GIF_VALUE));
	}

	public static byte[] getEmptyImage() {
		return EMPTY_IMAGE_CONTENT;
	}

}
