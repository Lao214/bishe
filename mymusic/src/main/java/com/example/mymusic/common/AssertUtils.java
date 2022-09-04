package com.example.mymusic.common;

import java.util.Collection;
import java.util.Map;

public class AssertUtils {
	public static void main(String[] args) {
		System.out.println("start");

		System.out.println("end");
	}

	public static void notNullAndEmpty(Object object, String message) {
		if (object == null)
			throw new IllegalArgumentException(message);
	}

	public static void notNullAndEmpty(String object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
		if ("".equals(object))
			throw new IllegalArgumentException(message);
	}

	public static void notNullAndEmpty(Collection object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
		notEmpty(object);
	}

	public static void notNullAndEmpty(Map object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
		notEmpty(object);
	}

	public static void notNullAndEmpty(Object object) {
		notNullAndEmpty(object, "数据不能为空！");
	}

	public static void notNullAndEmpty(Collection collection) {
		notNullAndEmpty(collection, "数据不能为空！");
	}

	public static void notNullAndEmpty(Map map) {
		notNullAndEmpty(map, "数据不能为空！");
	}

	public static void notNullAndEmpty(String str) {
		notNullAndEmpty(str, "数据不能为空！");
	}

	public static void notUpdateMoreThanOne(int count, String message) {
		if (count > 1)
			throw new IllegalArgumentException(message);
	}

	public static void notUpdateMoreThanOne(int count) {
		notUpdateMoreThanOne(count, "更新数据大于一条！");
	}

	public static void isTrue(boolean expression, String message) {
		if (!expression)
			throw new IllegalArgumentException(message);
	}

	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}

	public static void isNull(Object object, String message) {
		if (object != null)
			throw new IllegalArgumentException(message);
	}

	public static void isNull(Object object) {
		isNull(object, "[Assertion failed] - the object argument must be null");
	}

	public static void notNull(Object object, String message) {
		if (object == null)
			throw new IllegalArgumentException(message);
	}

	public static void notNull(Object object) {
		notNull(object, "[Assertion failed] - this argument is required; it must not be null");
	}

	public static void notSingleNull(Object object, Object objectSec, String message) {
		if (((object != null) || (objectSec != null)) && ((object == null) || (objectSec == null)))
			throw new IllegalArgumentException(message);
	}

	public static void notSingleNull(Object object, Object objectSec) {
		if (((object != null) || (objectSec != null)) && ((object == null) || (objectSec == null)))
			throw new IllegalArgumentException("[Assertion failed] - this argument is required; it must not be null");
	}

	public static void notSingleNullAndEmpty(String object, String objectSec, String message) {
		if (((!StringUtils.isEmpty(object)) || (!StringUtils.isEmpty(objectSec)))
				&& ((StringUtils.isEmpty(object)) || (StringUtils.isEmpty(objectSec))))
			throw new IllegalArgumentException(message);
	}

	public static void notSingleNullAndEmpty(String object, String objectSec) {
		if (((!StringUtils.isEmpty(object)) || (!StringUtils.isEmpty(objectSec)))
				&& ((StringUtils.isEmpty(object)) || (StringUtils.isEmpty(objectSec))))
			throw new IllegalArgumentException(
					"[Assertion failed] - this argument is required; it must not be null or empty");
	}

	public static void hasLength(String text, String message) {
		if (!hasLengthHelp(text))
			throw new IllegalArgumentException(message);
	}

	public static boolean hasLength(String text) {
		hasLength(text, "[Assertion failed] - this String argument must have length; it must not be null or empty");
		return false;
	}

	public static void hasText(String text, String message) {
		if (!hasLengthHelp(text))
			throw new IllegalArgumentException(message);
	}

	public static void hasText(String text) {
		hasText(text, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
	}

	public static void doesNotContain(String textToSearch, String substring, String message) {
		if ((hasLengthHelp(textToSearch)) && (hasLengthHelp(substring)) && (textToSearch.indexOf(substring) != -1)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void doesNotContain(String textToSearch, String substring) {
		doesNotContain(textToSearch, substring,
				new StringBuilder().append("[Assertion failed] - this String argument must not contain the substring [")
						.append(substring).append("]").toString());
	}

	public static void notEmpty(Object[] array, String message) {
		if (isEmpty(array))
			throw new IllegalArgumentException(message);
	}

	public static void notEmpty(Object[] array) {
		notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}

	public static void noNullElements(Object[] array, String message) {
		if (array != null)
			for (int i = 0; i < array.length; i++)
				if (array[i] == null)
					throw new IllegalArgumentException(message);
	}

	public static void noNullElements(Object[] array) {
		noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
	}

	public static void notEmpty(Collection collection, String message) {
		if (isEmpty(collection))
			throw new IllegalArgumentException(message);
	}

	public static void notEmpty(Collection collection) {
		notEmpty(collection,
				"[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
	}

	public static void notEmpty(Map map, String message) {
		if (isEmpty(map))
			throw new IllegalArgumentException(message);
	}

	public static void notEmpty(Map map) {
		notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
	}

	public static void isInstanceOf(Class clazz, Object obj) {
		isInstanceOf(clazz, obj, "");
	}

	public static void isInstanceOf(Class type, Object obj, String message) {
		notNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj))
			throw new IllegalArgumentException(new StringBuilder().append(message).append("Object of class [")
					.append(obj != null ? obj.getClass().getName() : "null").append("] must be an instance of ")
					.append(type).toString());
	}

	public static void isAssignable(Class superType, Class subType) {
		isAssignable(superType, subType, "");
	}

	public static void isAssignable(Class superType, Class subType, String message) {
		notNull(superType, "Type to check against must not be null");
		if ((subType == null) || (!superType.isAssignableFrom(subType)))
			throw new IllegalArgumentException(new StringBuilder().append(message).append(subType)
					.append(" is not assignable to ").append(superType).toString());
	}

	public static void state(boolean expression, String message) {
		if (!expression)
			throw new IllegalStateException(message);
	}

	public static void state(boolean expression) {
		state(expression, "[Assertion failed] - this state invariant must be true");
	}

	public static boolean isEmpty(Collection collection) {
		return (collection == null) || (collection.isEmpty());
	}

	public static boolean isEmpty(Object[] array) {
		return (array == null) || (array.length == 0);
	}

	public static boolean hasLength(CharSequence str) {
		return (str != null) && (str.length() > 0);
	}

	private static boolean hasLengthHelp(String str) {
		return hasLength(str);
	}

	public static boolean isEmpty(Map map) {
		return (map == null) || (map.isEmpty());
	}
}