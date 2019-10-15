package com.hand.prod.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtil {

	private static Class<FileUtil> clazz = FileUtil.class;

	private static Log log = LogFactory.getLog(clazz);

	public static List<File> getAllFiles(File f) {
		List<File> list = new ArrayList<File>();
		File[] fs = f.listFiles();
		for (File temp : fs) {
			if (temp.isFile())
				list.add(temp);
			else
				list.addAll(getAllFiles(temp));
		}
		return list;
	}

	public static void copy(InputStream is, OutputStream os) throws IOException {
		byte[] b = new byte[1024];
		int i;
		while ((i = is.read(b)) != -1)
			os.write(b, 0, i);
		os.flush();
	}

	public static void copy(File from, File to) {
		try {
		if (!to.getParentFile().exists())
			to.getParentFile().mkdirs();

		FileInputStream fis = null;
		FileOutputStream fos = null;
			fis = new FileInputStream(from);
			fos = new FileOutputStream(to);
			copy(fis, fos);
		} catch (Exception e) {
			log.error(clazz, e);
		} finally {}
	}

	public static String fullfile(Object... path) {
		return StringUtil.arrayToStr(path, "/");
	}
}
