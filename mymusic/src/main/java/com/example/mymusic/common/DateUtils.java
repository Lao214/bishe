package com.example.mymusic.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {
	public static final String PATTERN_MMdd = "MMdd";
	public static final String PATTERN_yyyyMMdd = "yyyyMMdd";
	public static final String PATTERN_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static final String PATTERN_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	public static final String PATTERN_yyyy_MM_dd = "yyyy-MM-dd";
	public static final String PATTERN_yyyy_M_d = "yyyy-M-d";
	public static final String PATTERN_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
	public static final String PATTERN_HH_mm_ss = "HH:mm:ss";
	public static final Map<String, String> mapChDate = new HashMap<String, String>();

	public static Date getNextday(Date now) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(5, 1);
		return calendar.getTime();
	}

	public static Date getPreday(Date now) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(5, -1);
		return calendar.getTime();
	}

	public static String formatDate_ch(Date date) {
		if (date == null) {
			throw new IllegalArgumentException("需要转换的对象为空");
		}
		String str = formatDate(date, "MMdd");
		str = (String) mapChDate.get(str.substring(0, 2)) + str.substring(2);
		return str;
	}

	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat sdf = null;
		if (pattern == null)
			sdf = new SimpleDateFormat();
		else {
			sdf = new SimpleDateFormat(pattern);
		}
		return sdf.format(date);
	}

	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		return sdf.format(date);
	}

	public static Date parseDate(String source, String pattern) throws ParseException {
		SimpleDateFormat sdf = null;
		if (pattern == null)
			sdf = new SimpleDateFormat();
		else {
			sdf = new SimpleDateFormat(pattern);
		}
		return sdf.parse(source);
	}

	public static Date parseDate(String source) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat();
		return sdf.parse(source);
	}

	public static Date parseDate(String source, Date defaultDate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat();
			return sdf.parse(source);
		} catch (ParseException e) {
		}
		return defaultDate;
	}

	public static Date parseDate(String source, String pattern, Date defaultDate) {
		try {
			SimpleDateFormat sdf = null;
			if (pattern == null)
				sdf = new SimpleDateFormat();
			else {
				sdf = new SimpleDateFormat(pattern);
			}
			return sdf.parse(source);
		} catch (ParseException e) {
		}
		return defaultDate;
	}

	public static boolean tryParse(String source) throws ParseException {
		try {
			Integer.parseInt(source);
			return true;
		} catch (NumberFormatException e) {
		}
		return false;
	}

	public static Date changeDate(Date date, TimeType timeType, int amount) {
		if ((date == null) || (timeType == null) || (amount == 0)) {
			return date;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(timeType.getValue(), amount);
		return c.getTime();
	}

	public static boolean isSameDay(Date date1, Date date2) {
		if ((date1 == null) || (date2 == null)) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}

	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if ((cal1 == null) || (cal2 == null)) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return (cal1.get(0) == cal2.get(0)) && (cal1.get(1) == cal2.get(1)) && (cal1.get(6) == cal2.get(6));
	}

	/** @deprecated */
	public static Date add(Date date, int calendarField, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	public static Date addYears(Date date, int amount) {
		return add(date, Calendar.YEAR, amount);
	}

	public static Date addMonths(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}

	public static Date addWeeks(Date date, int amount) {
		return add(date, 3, amount);
	}

	public static Date addDays(Date date, int amount) {
		return add(date, 5, amount);
	}

	public static Date addHours(Date date, int amount) {
		return add(date, 11, amount);
	}

	public static Date addMinutes(Date date, int amount) {
		return add(date, 12, amount);
	}

	public static Date addSeconds(Date date, int amount) {
		return add(date, 13, amount);
	}

	public static Date addMilliseconds(Date date, int amount) {
		return add(date, 14, amount);
	}

	static {
		mapChDate.put("01", "一月");
		mapChDate.put("02", "二月");
		mapChDate.put("03", "三月");
		mapChDate.put("04", "四月");
		mapChDate.put("05", "五月");
		mapChDate.put("06", "六月");
		mapChDate.put("07", "七月");
		mapChDate.put("08", "八月");
		mapChDate.put("09", "九月");
		mapChDate.put("10", "十月");
		mapChDate.put("11", "十一月");
		mapChDate.put("12", "十二月");
	}

	public static enum TimeType {
		SECOND(13), MINUTE(12), HOUR(10), DAY(5), WEEK(4), MONTH(2), YEAR(1);

		private int value;

		private TimeType(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	/**
	 * 通过时间秒毫秒数判断两个时间的间隔
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differDays(Date date1, Date date2) {
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
		return days;
	}

	/**
	 * 
	 * 毛龙飞
	 * 
	 * @Description 计算两个日期相差的月数
	 * @param d1
	 * @param d2
	 * @return d2 - d1
	 */
	public static Integer differMonth(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);

		Integer differMonth = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		Integer differYear = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);

		return differYear * 12 + differMonth;
	}

	public static Integer differYear(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);

		Integer differYear = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);

		return differYear;
	}
	/**
	 *
	 * 刘逸飞
	 *
	 * @Description 计算两个日期相差的分钟
	 * @param d1
	 * @param d2
	 * @return d2 - d1
	 */
	public static Integer differMinute(Date d1, Date d2) {

		Long cTime = (d2.getTime()-d1.getTime())/1000;
		cTime= cTime%3600/60;
		return cTime.intValue();
	}
	/**
	 * 方法说明：获取下一天开始时间
	 * @author huim
	 * 2018年6月26日下午6:14:43
	 * @param datetime
	 * @return
	 */
	public static Date getNextDate(Date datetime) {
		if(datetime == null) {
			return null;
		}
		Calendar now = Calendar.getInstance();
		now.setTime(datetime);
		now.add(Calendar.DAY_OF_MONTH, 1);
		now.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		return now.getTime();
	}

	/**
	 * 判断当前日期是星期几<br>
	 * <br>
	 * 
	 * @param pTime
	 *            修要判断的时间<br>
	 * @return dayForWeek 判断结果<br>
	 * @Exception 发生异常<br>
	 */
	public static int dayForWeek(String pTime) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 方法说明：获取今日 2018年9月30日下午9:32:38
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getToday(String pattern) {
		return formatDate(new Date(), pattern);
	}



	/**判断是否超过24小时
	 *
	 * @param date1
	 * @param date2
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean judgmentDate(String date1, String date2) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");

		Date start = sdf.parse(date1);

		Date end = sdf.parse(date2);

		long cha = end.getTime() - start.getTime();

		if(cha<0){

			return false;

		}
		double result = cha * 1.0 / (1000 * 60 * 60);
		if(result<=24){
			return true;
		}else{
			return false;
		}
	}
	/**判断是否超过24小时
	 *
	 * @param start
	 * @param end
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean judgmentDate(Date start, Date end) throws Exception {

		long cha = end.getTime() - start.getTime();

		if(cha<0){

			return false;

		}
		double result = cha * 1.0 / (1000 * 60 * 60);
		if(result<=24){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 判断今日是星期几<br>
	 * <br>
	 *
	 * @param
	 *
	 * @return todayForWeek 判断结果<br>
	 * @Exception 发生异常<br>
	 */
	public static int todayForWeek() throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}
	public static Date parseDateQuietly(String source, String pattern) {
		try {
			return parseDate(source, pattern);
		} catch (Exception e) {
		}
		return null;
	}
	/**
	 * 方法说明：获取日期开始时间
	 * 2020年1月16日下午8:20:02
	 */
	public static Date getDateStart(Date datetime) {
		if (datetime == null) {
			return null;
		}
		String date = formatDate(datetime, PATTERN_yyyyMMdd);
		return parseDateQuietly(date, PATTERN_yyyyMMdd);
	}

}