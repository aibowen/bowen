package com.hand.prod.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static final String YYYYMMDD = "yyyyMMdd";

	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	public static final String YYYYMM = "yyyyMM";

	public static final String YYYY_MM = "yyyy-MM";

	public static final String YYYY = "yyyy";

	public static final String DD_MMM_YYYY = "dd-MMM-yyyy";

	public static final String MMM_YYYY = "MMM-yyyy";

	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static Date convertStrToDate(String mask, String date)
			throws ParseException {
		return new SimpleDateFormat(mask, Locale.US).parse(date);
	}

	public static String convertDateToStr(String mask, Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(mask, Locale.US).format(date);
	}

	public static Date addDate(Date date, int field, int value) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(field, value);
		return c.getTime();
	}
}
