package com.example.mymusic.common;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	public static final String EMPTY = "";
	private static final char[] szVerCode = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };

	private static final int[] iW = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
	public static final String DEFAULT_CHARSET = "utf-8";

	public static String reverse(String str) {
		if (isEmpty(str))
			return str;
		StringBuilder sb = new StringBuilder();
		for (int i = str.length() - 1; i >= 0; i--) {
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}

	public static String padLeft(String str, int len, char padCharacter) {
		String padStr = pad(str, len, padCharacter);
		return new StringBuilder().append(padStr).append(str).toString();
	}

	public static String padRight(String str, int len, char padCharacter) {
		String padStr = pad(str, len, padCharacter);
		return new StringBuilder().append(str).append(padStr).toString();
	}

	private static String pad(String str, int len, char padCharacter) {
		if (str == null)
			str = String.valueOf(padCharacter);
		if (str.length() >= len)
			return "";
		int padLen = len - str.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < padLen; i++) {
			sb.append(padCharacter);
		}
		return sb.toString();
	}

	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}
		return str.length() == 0;
	}

	public static boolean isNotEmpty(String str) {
		return !isNullOrEmpty(str);
	}

	public static boolean isDigital(String str) {
		if (isEmpty(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isIDCardNo(String ID18) {
		boolean flag = false;
		if ((ID18 == null) || (ID18.length() != 18))
			return false;
		int i = 0;
		for (int k = 0; k < 18; k++) {
			char c = ID18.charAt(k);
			int j;
			if (c == 'X') {
				j = 10;
			} else {
				if ((c <= '9') || (c >= '0'))
					j = c - '0';
				else
					return flag;
			}
			i += j * iW[k];
		}
		if (i % 11 == 1)
			flag = true;
		return flag;
	}

	public static String toIDCardNO18(String ID15) {
		if ((ID15 == null) || (ID15.length() != 15)) {
			return null;
		}
		String ID17 = new StringBuilder(ID15.substring(0, 6)).append("19").append(ID15.substring(6, 15)).toString();

		int[] ID17Array = new int[17];
		for (int i = 0; i < 17; i++) {
			ID17Array[i] = Integer.parseInt(ID17.substring(i, i + 1));
		}
		int s = 0;
		for (int i = 0; i < 17; i++) {
			s += ID17Array[i] * iW[i];
		}
		s %= 11;
		return new StringBuilder().append(ID17).append(Character.toString(szVerCode[s])).toString();
	}

	public static boolean isHttp(String value) {
		return check("^(http:\\/\\/)[a-z0-9A-Z]+([\\.][a-z0-9A-Z]+)+", value, false);
	}

	public static boolean isEmail(String value) {
		return check("^([a-z0-9A-Z])+([_|\\-|\\.]?[a-z0-9A-Z])*@[a-z0-9A-Z]+(\\-[a-z0-9A-Z]+)*(\\.[a-zA-Z]+){1,2}$",
				value, true);
	}

	public static boolean hasChinese(String value) {
		return check("[一-龥]", value, true);
	}

	public static boolean isChinese(String value) {
		for (char ch : value.toCharArray()) {
			if (!check("[一-龥]", String.valueOf(ch), true))
				return false;
		}
		return true;
	}

	public static boolean check(String regex, String value, boolean matchAll) {
		Pattern p = Pattern.compile(regex);
		Matcher result = p.matcher(value);
		return matchAll ? result.matches() : result.find();
	}

	public static boolean isNullOrEmpty(String value) {
		return (value == null) || (value.trim().length() == 0);
	}

	public static boolean equal(Object a, Object b) {
		return (a == b) || ((a != null) && (a.equals(b)));
	}

	public static String format(String format, Map<String, String> paramMap) {
		if (format == null) {
			return null;
		}

		Set<String> keys = paramMap.keySet();
		Iterator<String> ite = keys.iterator();
		while (ite.hasNext()) {
			String key = (String) ite.next();
			String value = (String) paramMap.get(key);
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append(key);
			sb.append("}");
			format = format.replace(sb.toString(), value);
		}

		return format;
	}

	public static String format(String format, Object[] args) {
		if (format == null) {
			return null;
		}
		for (int i = 0; i < args.length; i++) {
			StringBuilder sb = new StringBuilder(3);
			sb.append("{");
			sb.append(i);
			sb.append("}");
			String arg = (String) args[i];
			if (arg == null) {
				arg = "";
			}
			format = format.replace(sb.toString(), arg.toString());
		}

		return format;
	}

	public static String format(String format, String arg) {
		if (format == null) {
			return null;
		}
		int beginIndex = format.indexOf("{");
		int endIndex = format.indexOf("}");
		StringBuilder sb = new StringBuilder();
		sb.append(format.substring(0, beginIndex));
		sb.append(arg);
		sb.append(format.substring(endIndex + 1));
		return sb.toString();
	}

	public static String format(String format, String[] args) {
		if (format == null) {
			return null;
		}
		for (int i = 0; i < args.length; i++) {
			StringBuilder sb = new StringBuilder(3);
			sb.append("{");
			sb.append(i);
			sb.append("}");
			String arg = args[i];
			if ((arg == null) || ("null".equals(arg.toLowerCase()))) {
				arg = "";
			}
			format = format.replace(sb.toString(), arg);
		}

		return format;
	}

	public static String formatNoReplace(String format, String[] args) {
		if (format == null) {
			return null;
		}
		for (int i = 0; i < args.length; i++) {
			StringBuilder sb = new StringBuilder(3);
			sb.append("{");
			sb.append(i);
			sb.append("}");
			String arg = args[i];
			format = format.replace(sb.toString(), arg);
		}

		return format;
	}

	public static String file2String(File file) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		int bufferLength = 1024;
		char[] c = new char[bufferLength];
		int i = 0;

		StringBuilder sb = new StringBuilder();
		while (true) {
			i = br.read(c, 0, bufferLength);
			if (i == -1)
				break;
			if (i < bufferLength) {
				sb.append(c, 0, i);
				break;
			}
			sb.append(c);
		}
		br.close();
		fr.close();

		return sb.toString();
	}

	public static String stream2String(InputStream is) throws IOException {
		if (is == null) {
			return null;
		}
		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(reader);

		int bufferLength = 1024;
		char[] c = new char[bufferLength];
		int i = 0;

		StringBuilder sb = new StringBuilder();
		while (true) {
			i = br.read(c, 0, bufferLength);
			if (i == -1)
				break;
			if (i < bufferLength) {
				sb.append(c, 0, i);
				break;
			}
			sb.append(c);
		}
		br.close();
		reader.close();

		return sb.toString();
	}

	public static String stream2String(InputStream is, String charsetName) throws IOException {
		if (is == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		InputStreamReader isr = new InputStreamReader(is, charsetName);
		BufferedReader br = new BufferedReader(isr);
		int length = 1024;
		char[] cbuf = new char[length];
		int len = 0;

		while ((len = br.read(cbuf, 0, length)) != -1) {
			sb.append(cbuf, 0, len);
		}
		if (br != null)
			br.close();
		if (isr != null)
			isr.close();
		return sb.toString();
	}

	public static String toString(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}

	public static String toString(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	public static String toStringNull(Object obj) {
		if (obj == null) {
			return null;
		}
		return obj.toString();
	}

	public static String toString(String str, String defaultValue) {
		return isEmpty(str) ? defaultValue : str;
	}

	public static String toString(Object object, String defaultValue) {
		if ((object instanceof String)) {
			return (object == null) || (object.toString().length() == 0) ? defaultValue : object.toString();
		}
		return object == null ? defaultValue : object.toString();
	}

	public static String formatInt(String str) {
		return formatInt(str, "");
	}

	public static String formatInt(String str, String defaultValue) {
		if ((str == null) || (str.length() == 0)) {
			return defaultValue;
		}

		char[] chars = str.toCharArray();
		int length = chars.length;
		double x = length / 3.0D;
		int distLength = length + (int) Math.floor(x);
		char[] dist = new char[distLength];

		int i = 1;
		for (int j = 1; i <= length; j++) {
			dist[(distLength - j)] = chars[(length - i)];
			if ((i % 3 == 0) && (i < length)) {
				j++;
				dist[(distLength - j)] = ',';
			}
			i++;
		}

		return new String(dist).trim();
	}

	public static String formatDouble(String d) {
		DecimalFormat df = new DecimalFormat("#,##0.00");
		if ((d == null) || (d.length() == 0)) {
			return "&nbsp;";
		}
		return df.format(Double.parseDouble(d));
	}

	public static String formatDouble(double d) {
		DecimalFormat df = new DecimalFormat("#,##0.00");
		return (Double.isNaN(d)) || (Double.isInfinite(d)) ? "&nbsp;" : df.format(d);
	}

	public static String formatDouble(double d, String pattern) {
		if (pattern == null) {
			return formatDouble(d);
		}
		DecimalFormat df = new DecimalFormat(pattern);

		return (Double.isNaN(d)) || (Double.isInfinite(d)) ? "&nbsp;" : df.format(d);
	}

	public static String formatAmount(long amount) {
		BigDecimal bd = new BigDecimal(amount).divide(new BigDecimal("100"));
		DecimalFormat df = new DecimalFormat("#,##0.00");
		return df.format(bd.doubleValue());
	}

	public static String formatAmount(String amount) {
		amount = toString(amount, "0");
		return formatAmount(Long.valueOf(amount).longValue());
	}

	public static String formatAmount(long amount, String pattern) {
		if (pattern == null) {
			return formatAmount(amount);
		}
		BigDecimal bd = new BigDecimal(amount).divide(new BigDecimal("100"));
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(bd.doubleValue());
	}

	public static String formatAmount(String amount, String pattern) {
		amount = toString(amount, "0");
		return formatAmount(Long.valueOf(amount).longValue(), pattern);
	}

	/**
	 * 
	 * 方法说明：支付宝支付金额转换
	 * 
	 * @author litao 2018年9月23日下午1:59:34
	 * @param amount
	 * @return
	 */
	public static String formatStrAmount(String amount) {
		BigDecimal bd = new BigDecimal(amount).divide(new BigDecimal("100"));
		DecimalFormat df = new DecimalFormat("#,##0.00");
		return df.format(bd.doubleValue());
	}

	/**
	 * 
	 * 方法说明：支付宝支付金额转换
	 * 
	 * @author litao 2018年9月23日下午1:59:38
	 * @param amount
	 * @param pattern
	 * @return
	 */
	public static String formatStrAmount(String amount, String pattern) {

		if (pattern == null) {
			return formatAmount(amount);
		}
		BigDecimal bd = new BigDecimal(amount).divide(new BigDecimal("100"));
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(bd.doubleValue());
	}

	public static String formatAmountNoDecimal(String amount) {
		amount = toString(amount, "0");
		return formatAmount(Long.valueOf(amount).longValue(), "##0");
	}

	public static String formatCertNo(String certNo) throws Exception {
		if (!isEmpty(certNo)) {
			int length = certNo.length();
			if (length >= 7) {
				return new StringBuilder().append(rpad(certNo.substring(0, 6), "*", length - 1))
						.append(certNo.charAt(length - 1)).toString();
			}
		}
		return certNo;
	}

	public static String formatFloat(float val, int percision) {
		int count = 1;
		for (int i = 0; i < percision; i++) {
			count *= 10;
		}

		double result = Math.round(val * count) / count;
		return Double.toString(result);
	}

	public static String formatFloat(Float val, int percision) {
		float valFloat = val.floatValue();
		return formatFloat(valFloat, percision);
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
			Date date = sdf.parse(source);
			return date;
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

	public static int parseInt(String source, int defaultReturn) {
		try {
			return Integer.parseInt(source);
		} catch (NumberFormatException e) {
		}
		return defaultReturn;
	}

	public static boolean regexMatch(String source, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		return matcher.matches();
	}

	public static String regexCatch(String source, String regex, int index) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		if (matcher.find()) {
			return matcher.group(index);
		}
		return null;
	}

	public static String[] regexCatchMore(String source, String regex, int[] indexs) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		String[] results = new String[indexs.length];
		if (matcher.find()) {
			for (int i = 0; i < indexs.length; i++) {
				results[i] = matcher.group(indexs[i]);
			}
		}

		return results;
	}

	public static String regexCatch(String source, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	public static String replaceMsgFenToYuan(String errorMsg) {
		String regex = "\\d+分";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(errorMsg);
		while (matcher.find()) {
			String str = matcher.group();
			errorMsg = errorMsg.replaceFirst(str, new StringBuilder()
					.append(formatAmount(str.substring(0, str.length() - 1), "#0.00")).append("元").toString());
		}
		return errorMsg;
	}

	public static String toString(Object[] params) {
		String result = "";
		if (params != null) {
			StringBuilder sb = new StringBuilder();
			for (Object param : params) {
				if (param != null)
					sb.append(param.toString());
				else {
					sb.append("null");
				}
				sb.append(",");
			}
			int length = sb.length();
			if (length > 0) {
				sb.deleteCharAt(length - 1);
			}
			result = sb.toString();
		}
		return result;
	}

	public static void print(String[] strings) {
		for (int i = 0; i < strings.length; i++) {
			System.out.print(strings[i]);
			System.out.print(",");
		}
	}

	public static void print(String[][] strings) {
		for (String[] s : strings) {
			for (String r : s) {
				System.out.print(r);
				System.out.print(",");
			}
			System.out.println();
		}
	}

	public static String showCardInfo(String cardNo) {
		if (isNullOrEmpty(cardNo))
			return cardNo;
		if (cardNo.length() != 16)
			return cardNo;
		if (!isDigital(cardNo))
			return cardNo;
		return cardNo.substring(12, 16);
	}

	public static String showIDInfo(String idNo) {
		if (isNullOrEmpty(idNo))
			return idNo;
		return new StringBuilder().append(idNo.substring(0, 1)).append("****************")
				.append(idNo.substring(17, 18)).toString();
	}

	public static String showMobileNoInfo(String mobileNO) {
		if (isNullOrEmpty(mobileNO))
			return mobileNO;
		return new StringBuilder().append(mobileNO.substring(0, 3)).append("****").append(mobileNO.substring(0, 11))
				.toString();
	}

	public static String[] distinct(String[] strings) {
		if (strings == null)
			return null;
		int size = strings.length;
		String[] temps = new String[size];

		int current = 0;

		for (int i = 0; i < size; i++) {
			String src = strings[i];

			for (int j = 0; j <= current; j++) {
				String target = temps[j];

				if (target == null) {
					temps[j] = src;
					current = j + 1;
					break;
				}
				if (src.equals(target)) {
					break;
				}
			}
		}
		String[] results = new String[current];
		System.arraycopy(temps, 0, results, 0, current);
		return results;
	}

	public static String lpad(String orgin, String fill, int length) throws Exception {
		AssertUtils.notNull(orgin);
		AssertUtils.notNullAndEmpty(Integer.valueOf(length));

		if (fill.length() != 1) {
			throw new Exception("填充字符串只支持单个字符。");
		}
		if (orgin.length() > length) {
			throw new Exception("原始字符串长度不能大于设定值！");
		}

		while (orgin.length() < length) {
			orgin = new StringBuilder().append(fill).append(orgin).toString();
		}

		return orgin;
	}

	public static String lpad(String orgin, int length) throws Exception {
		return lpad(orgin, " ", length);
	}

	public static String rpad(String orgin, String fill, int length) throws Exception {
		AssertUtils.notNull(orgin);
		AssertUtils.notNullAndEmpty(Integer.valueOf(length));

		if (fill.length() != 1) {
			throw new Exception("填充字符串只支持单个字符。");
		}
		if (orgin.length() > length) {
			throw new Exception("原始字符串长度不能大于设定值！");
		}
		while (orgin.length() < length) {
			orgin = new StringBuilder().append(orgin).append(fill).toString();
		}

		return orgin;
	}

	public static String rpad(String orgin, int length) throws Exception {
		return rpad(orgin, " ", length);
	}

	public static String replaceAll(Map<String, String> replaceMap, String content, String previousPart,
			String afterPart) {
		Set<String> keys = replaceMap.keySet();
		String key = null;
		String value = null;
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			key = (String) it.next();
			value = (String) replaceMap.get(key);
			content = content.replaceAll(
					new StringBuilder().append(previousPart).append(key).append(afterPart).toString(), value);
		}
		return content;
	}

	public static byte[] toUtf8Bytes(String str) {
		try {
			return str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	public static String toUtf8String(byte[] bytes) {
		try {
			return new String(bytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	public static String trim(String str) {
		return str == null ? "" : str.trim();
	}

	public static boolean equals(String str1, String str2) {
		// return str1 == null ? false : str2 == null ? true : str1.equals(str2);
		return str1 == null ? str2 == null : str1.equals(str2);
	}

	public static String mapToString(Map<String, Object> map) {
		if ((map == null) || (map.isEmpty())) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			buffer.append("&").append((String) entry.getKey()).append("=").append(entry.getValue());
		}
		return buffer.toString().substring(1);
	}

	private static IllegalStateException newIllegalStateException(String charsetName, UnsupportedEncodingException e) {
		return new IllegalStateException(new StringBuilder().append(charsetName).append(": ").append(e).toString());
	}

	public static String newString(byte[] bytes, String charsetName) {
		if (bytes == null)
			return null;
		try {
			return new String(bytes, charsetName);

		} catch (UnsupportedEncodingException e) {
			throw newIllegalStateException(charsetName, e);
		}
	}

	public static String newStringUtf8(byte[] bytes) {
		return newString(bytes, "UTF-8");
	}

	public static boolean isNumeric(String numStr) {
		int posDot = numStr.indexOf(".");
		if (posDot < 0) {
			return isDigital(numStr);
		}
		return (isDigital(numStr.substring(0, posDot))) && (isDigital(numStr.substring(posDot + 1)));
	}

	public static Map<String, String> getUrlParams(String param) {
		Map<String, String> map = new HashMap<>(0);
		if (isNullOrEmpty(param)) {
			return map;
		}
		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

	public static String getUrlParamsByMap(Map<String, String> map) {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sb.append(new StringBuilder().append((String) entry.getKey()).append("=").append((String) entry.getValue())
					.toString());
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = substringBeforeLast(s, "&");
		}
		return s;
	}

	public static String substringBeforeLast(String str, String separator) {
		if ((isEmpty(str)) || (isEmpty(separator))) {
			return str;
		}
		int pos = str.lastIndexOf(separator);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	public static String getPriceStr(Long price) {
		BigDecimal b = new BigDecimal(price == null ? 0 : price);
		return b.divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_EVEN).toString();
	}

	/**
	 * 将map的value值转为实体类中字段类型匹配的方法
	 * 
	 * @param value
	 * @param fieldTypeClass
	 * @return
	 */
	private static Object convertValType(Object value, Class<?> fieldTypeClass) {
		Object retVal = null;

		if (Long.class.getName().equals(fieldTypeClass.getName())
				|| long.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Long.parseLong(value.toString());
		} else if (Integer.class.getName().equals(fieldTypeClass.getName())
				|| int.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Integer.parseInt(value.toString());
		} else if (Float.class.getName().equals(fieldTypeClass.getName())
				|| float.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Float.parseFloat(value.toString());
		} else if (Double.class.getName().equals(fieldTypeClass.getName())
				|| double.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Double.parseDouble(value.toString());
		} else {
			retVal = value;
		}
		return retVal;
	}

	/**
	 * 根据给定对象类匹配对象中的特定字段
	 * 
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	private static Field getClassField(Class<?> clazz, String fieldName) {
		if (Object.class.getName().equals(clazz.getName())) {
			return null;
		}
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		Class<?> superClass = clazz.getSuperclass(); // 如果该类还有父类，将父类对象中的字段也取出
		if (superClass != null) { // 递归获取
			return getClassField(superClass, fieldName);
		}
		return null;
	}

	/**
	 * 利用反射将map集合封装成bean对象
	 *
	 * @param
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T mapToBean(Map<String, Object> map, Class<?> clazz) throws Exception {
		Object obj = clazz.newInstance();
		if (map != null && !map.isEmpty() && map.size() > 0) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String propertyName = entry.getKey(); // 属性名
				Object value = entry.getValue(); // 属性值
				String setMethodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
				Field field = getClassField(clazz, propertyName); // 获取和map的key匹配的属性名称
				if (field == null) {
					continue;
				}
				Class<?> fieldTypeClass = field.getType();
				value = convertValType(value, fieldTypeClass);
				try {
					clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}
		return (T) obj;
	}

	/**
	 * 判断一个字符串是否能转化为数字方法
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	public static void main(String[] args) throws Exception {
		System.out.println(replaceMsgFenToYuan("100000000008分ddd100440分10570分sdd 400034分400008分"));
	}
}