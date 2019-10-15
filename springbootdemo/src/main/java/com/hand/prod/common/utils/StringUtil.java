package com.hand.prod.common.utils;

import java.util.Collection;

public class StringUtil {

	public static <T> String collectionToStr(Collection<T> c) {
		return collectionToStr(c, "");
	}

	public static <T> String collectionToStr(Collection<T> c, String splitStr) {
		StringBuilder sb = new StringBuilder();
		for (T o : c) {
			if (o == null) {
				continue;
			}

			sb.append(splitStr + o);
		}

		if (sb.length() > 0) {
			return sb.substring(splitStr.length());
		}
		return "";
	}

	public static <T> String arrayToStr(T[] os) {
		return arrayToStr(os, "");
	}

	public static <T> String arrayToStr(T[] os, String splitStr) {
		StringBuilder sb = new StringBuilder();
		for (T o : os) {
			if (o == null) {
				continue;
			}

			sb.append(splitStr + o);
		}

		if (sb.length() > 0) {
			return sb.substring(splitStr.length());
		}
		return "";
	}

	public static boolean isBOM(byte[] b) {
		return b.length >= 3 && b[0] == -17 && b[1] == -69 && b[2] == -65;
	}
	
	public static boolean isEmpty(String o) {
		return o == null || o.length() == 0;
	}

	public static boolean isNotEmpty(String o) {
		return !isEmpty(o);
	}

}
