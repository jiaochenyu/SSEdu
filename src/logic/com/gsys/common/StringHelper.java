package com.gsys.common;

import java.util.Collection;
import java.util.Map;

public class StringHelper {

	/**
     * Checks if a String is null or empty.
     *
     * @param str the string to check
     * @return true if the String is null, or length zero
     */
	public static boolean isEmpty(String str) {
		return (str == null || str.length() == 0);
	}

	public static boolean isEmpty(Object[] array) {
		return (array == null || array.length == 0);
	}

	public static boolean isEmpty(Collection<?> collection) {
		return (collection == null || collection.isEmpty());
	}

	public static boolean isEmpty(Map<?, ?> map) {
		return (map == null || map.isEmpty());
	}

	/**
     * Checks if a String is non null and is not empty (length > 0).
     *
     * @param str the string to check
     * @return true if the String is non-null, and not length zero
     */
	public static boolean isNotEmpty(String str) {
		return (str != null && str.length() > 0);
	}

	public static boolean isNotEmpty(Object[] array) {
		return (array != null && array.length > 0);
	}

	public static boolean isNotEmpty(Collection<?> collection) {
		return (collection != null && !collection.isEmpty());
	}

	public static boolean isNotEmpty(Map<?, ?> map) {
		return (map != null && !map.isEmpty());
	}

	/**
     * Checks if a (trimmed) String is null or empty.
     *
     * @param str the string to check
     * @return true if the String is null, or length zero once trimmed
     */
	public static boolean isBlank(String str) {
		return (str == null || str.trim().length() == 0);
	}

	/**
     * Checks if a (trimmed) String is non null and is not empty (length > 0).
     *
     * @param str the string to check
     * @return true if the String is non-null, and not length zero once trimmed
     */
	public static boolean isNotBlank(String str) {
		return (str != null && str.trim().length() > 0);
	}

	public static int length(CharSequence cs) {
		return cs == null ? 0 : cs.length();
	}

	/**
	 * Crops string by setting empty strings to <code>null</code>.
	 */
	public static String crop(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		return str;
	}

	/**
     * Removes control characters, including whitespace, from both ends of this 
     * string, handling null by returning an empty string.
     *
     * @see java.lang.String#trim()
     * @param str the string to check
     * @return the trimmed text (never {@code null})
     */
	public static String clean(String str) {
		return str == null ? "" : str.trim();
	}

    /**
     * Removes control characters, including whitespace, from both ends of this
     * string, handling null by returning null. 
     *
     * @see java.lang.String#trim()
     * @param str the string to check
     * @return the trimmed text (or {@code null})
     */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * Trim whitespaces from the left.
	 */
	public static String trimLeft(String str) {
		if (str == null) {
			return str;
		}
		int len = str.length();
		int st = 0;
		while ((st < len) && (Character.isWhitespace(str.charAt(st)))) {
			st++;
		}
		return st > 0 ? str.substring(st) : str;
	}

	/**
	 * Trim whitespaces from the right.
	 */
	public static String trimRight(String str) {
		if (str == null) {
			return str;
		}
		int len = str.length();
		int count = len;
		while ((len > 0) && (Character.isWhitespace(str.charAt(len - 1)))) {
			len--;
		}
		return (len < count) ? str.substring(0, len) : str;
	}

	/**
	 * Converts safely an object to a string.
	 */
	public static String toString(Object value) {
		if (value == null) {
			return null;
		}
		return value.toString();
	}

	/**
	 * Converts safely an object to a string. If object is <code>null</code> an
	 * empty string is returned.
	 */
	public static String toSafeString(Object value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}

	/**
     * Convert a String to upper case, null string returns null.
     * 
     * @param str the string to uppercase
     * @return the upper cased string
     */
	public static String upperCase(String str) {
		return str == null ? null : str.toUpperCase();
	}

	/**
     * Convert a String to lower case, null string returns null.
     * 
     * @param str the string to lowercase
     * @return the lower cased string
     */
	public static String lowerCase(String str) {
		return str == null ? null : str.toLowerCase();
	}

	public static boolean equalsLength(String[]... args) {
		boolean result = false;
		if (args != null && args.length > 0) {
			int start = -1;
			if (args[0] != null) {
				start = args[0].length;
			}
			for (int i = 0; i < args.length; i++) {
				int end = -1;
				if (args[i] != null) {
					end = args[i].length;
				}
				if (start != end) {
					result = false;
					break;
				} else {
					result = true;
				}
			}
		}
		return result;
	}

	public static boolean equalsLength(Collection<String>... args) {
		boolean result = false;
		if (args != null && args.length > 0) {
			int start = -1;
			if (args[0] != null) {
				start = args[0].size();
			}
			for (int i = 0; i < args.length; i++) {
				int end = -1;
				if (args[i] != null) {
					end = args[i].size();
				}
				if (start != end) {
					result = false;
					break;
				} else {
					result = true;
				}
			}
		}
		return result;
	}

}
