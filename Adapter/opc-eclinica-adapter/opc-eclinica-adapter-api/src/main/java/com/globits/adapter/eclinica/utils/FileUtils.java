package com.globits.adapter.eclinica.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

public class FileUtils
{

	/**
	 * Read and return content of a file in form of byte array
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] readFile(File file) {

		if (CommonUtils.isNull(file) || !file.exists()) {
			return null;
		}

		byte[] content = null;

		try (FileInputStream is = new FileInputStream(file);) {
			content = IOUtils.toByteArray(is);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return content;
	}

	/**
	 * 
	 * Get the file extension
	 * 
	 * @param filename
	 * @return The extension
	 */
	public static String getFileExtension(String filename) {

		if (CommonUtils.isEmpty(filename)) {
			return null;
		}

		int pos = filename.lastIndexOf(".");

		if ((pos > 0) && (pos < filename.length())) {
			return filename.substring(pos + 1);
		}

		return null;
	}

	/**
	 * Get content type of a file
	 * 
	 * @param file
	 * @return
	 */
	public static String getContentType(File file) {

		String contentType = "text/html";

		if (CommonUtils.isNull(file) || !file.exists()) {
			return contentType;
		}

		try (FileInputStream is = new FileInputStream(file);) {

			ContentHandler contenthandler = new BodyContentHandler();

			Metadata metadata = new Metadata();
			metadata.set(Metadata.RESOURCE_NAME_KEY, file.getName());

			Parser parser = new AutoDetectParser();
			parser.parse(is, contenthandler, metadata, new ParseContext());

			contentType = metadata.get(Metadata.CONTENT_TYPE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return contentType;
	}

	/**
	 * Return file icon in FontAwesome web font
	 * 
	 * @param filename
	 * @return
	 */
	public static String getFontAwesomeIcon(String fileExt) {

		String icon = "fa-file-o";

		if (CommonUtils.isEmpty(fileExt)) {
			return icon;
		}

		switch (fileExt.toLowerCase()) {
			case "doc":
			case "docx":
			case "dot":
			case "docm":
			case "dotx":
			case "dotm":
			case "wpd":
			case "wps":
			case "sdw":
			case "sgl":
			case "vor":
			case "uot":
			case "uof":
			case "psw":
				icon = "fa-file-word-o";
				break;
			case "xls":
			case "xlsx":
			case "xlw":
			case "xlt":
			case "xlsm":
			case "xltx":
			case "xltm":
			case "csv":
			case "sdc":
			case "uos":
			case "pxl":
			case "wb2":
				icon = "fa-file-excel-o";
				break;
			case "ppt":
			case "pptx":
			case "pps":
			case "pot":
			case "pptm":
			case "potm":
			case "potx":
			case "sda":
			case "sdd":
			case "sdp":
			case "uop":
				icon = "fa-file-powerpoint-o";
				break;
			case "pdf":
			case "ps":
			case "eps":
				icon = "fa-file-pdf-o";
				break;
			case "zip":
			case "rar":
			case "gz":
			case "tar":
				icon = "fa-file-zip-o";
				break;
			case "txt":
			case "rtf":
				icon = "fa-file-text-o";
				break;
			case "java":
			case "c":
			case "cpp":
			case "h":
				icon = "fa-file-code-o";
			case "png":
			case "jpg":
			case "jpeg":
			case "gif":
			case "tif":
			case "tiff":
			case "pcx":
			case "emf":
			case "jpe":
			case "jpf":
			case "jpx":
			case "jp2":
			case "j2k":
			case "j2c":
			case "jpc":
			case "psd":
			case "bmp":
			case "ai":
			case "dwg":
			case "dwt":
			case "dxf":
			case "dwf":
			case "dst":
			case "3ds":
			case "wrl":
			case "vrml":
			case "sldasm":
			case "sldprt":
			case "prt":
			case "xpr":
			case "xas":
			case "neu":
				icon = "fa-file-image-o";
				break;
			case "act":
			case "aiff":
			case "aac":
			case "amr":
			case "ape":
			case "au":
			case "awb":
			case "dct":
			case "dss":
			case "dvf":
			case "flac":
			case "gsm":
			case "iklax":
			case "ivs":
			case "m4a":
			case "mmf":
			case "mp3":
			case "mpc":
			case "msv":
			case "ogg":
			case "oga":
			case "opus":
			case "vox":
			case "wav":
			case "wma":
				icon = "fa-file-audio-o";
				break;
			case "mkv":
			case "flv":
			case "vob":
			case "ogv":
			case "mng":
			case "avi":
			case "mov":
			case "wmv":
			case "rmvb":
			case "mp4":
			case "m4p":
			case "m4v":
			case "mpg":
			case "mp2":
			case "mpeg":
			case "mpe":
			case "mpv":
			case "m2v":
			case "3gp":
			case "3g2":
				icon = "fa-file-video-o";
				break;
			default:
				icon = "fa-file-o";
				break;
		}

		return icon;
	}
}
