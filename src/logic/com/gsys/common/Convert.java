package com.gsys.common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Provides a object conversion to a type.
 */
public class Convert {

	/**
	 * Converts value to <code>Boolean</code>. Returns <code>null</code>
	 * when conversion result is <code>null</code>
	 */
	public static Boolean toBoolean(Object value) {
		if (value == null) {
			return null;
		}
		if (value.getClass() == Boolean.class) {
			return (Boolean) value;
		}
		String stringValue = value.toString().trim().toLowerCase();
		if (stringValue.equals("true") || stringValue.equals("1")
				|| stringValue.equals("yes") || stringValue.equals("y")
				|| stringValue.equals("on")) {
			return Boolean.TRUE;
		}
		if (stringValue.equals("false") || stringValue.equals("0")
				|| stringValue.equals("no") || stringValue.equals("n")
				|| stringValue.equals("off")) {
			return Boolean.FALSE;
		}
		return null;
	}

	/**
	 * Converts value to <code>Boolean</code>. Returns default value
	 * when conversion result is <code>null</code>
	 */
	public static Boolean toBoolean(Object value, Boolean defaultValue) {
		Boolean result = toBoolean(value);
		if (result == null) {
			return defaultValue;
		}
		return result;
	}

	/**
	 * Converts value to <code>boolean</code>. Returns default value
	 * when conversion result is <code>null</code>.
	 */
	public boolean toBooleanValue(Object value, boolean defaultValue) {
		Boolean result = toBoolean(value);
		if (result == null) {
			return defaultValue;
		}
		return result.booleanValue();
	}

	/**
	 * Converts value to <code>boolean</code> with common default value.
	 */
	public boolean toBooleanValue(Object value) {
		return toBooleanValue(value, false);
	}

	/**
	 * Converts value to <code>Integer</code>. Returns <code>null</code>
	 * when conversion result is <code>null</code>
	 */
	public static Integer toInteger(Object value) {
		if (value == null) {
			return null;
		}
		if (value.getClass() == Integer.class) {
			return (Integer) value;
		}
		if (value instanceof Number) {
			return Integer.valueOf(((Number) value).intValue());
		}
		if (value instanceof Boolean) {
			return ((Boolean) value).booleanValue() ? Integer.valueOf(1)
					: Integer.valueOf(0);
		}
		try {
			String stringValue = value.toString().trim();
			if (stringValue.startsWith("+")) {
				stringValue = stringValue.substring(1);
			}
			return Integer.valueOf(stringValue);
		} catch (NumberFormatException nfex) {
		}
		return null;
	}

	/**
	 * Converts value to <code>Integer</code>. Returns default value
	 * when conversion result is <code>null</code>
	 */
	public static Integer toInteger(Object value, Integer defaultValue) {
		Integer result = toInteger(value);
		if (result == null) {
			return defaultValue;
		}
		return result;
	}

	/**
	 * Converts value to <code>int</code>. Returns default value
	 * when conversion result is <code>null</code>.
	 */
	public static int toIntValue(Object value, int defaultValue) {
		Integer result = toInteger(value);
		if (result == null) {
			return defaultValue;
		}
		return result.intValue();
	}

	/**
	 * Converts value to <code>int</code> with common default value.
	 */
	public static int toIntValue(Object value) {
		return toIntValue(value, 0);
	}

	/**
	 * Converts value to <code>Long</code>. Returns <code>null</code>
	 * when conversion result is <code>null</code>
	 */
	public static Long toLong(Object value) {
		if (value == null) {
			return null;
		}
		if (value.getClass() == Long.class) {
			return (Long) value;
		}
		if (value instanceof Number) {
			return Long.valueOf(((Number) value).longValue());
		}
		if (value instanceof Boolean) {
			return ((Boolean) value).booleanValue() ? Long.valueOf(1L) : Long
					.valueOf(0L);
		}
		try {
			String stringValue = value.toString().trim();
			if (stringValue.startsWith("+")) {
				stringValue = stringValue.substring(1);
			}
			return Long.valueOf(stringValue);
		} catch (NumberFormatException nfex) {
		}
		return null;
	}

	/**
	 * Converts value to <code>Long</code>. Returns default value
	 * when conversion result is <code>null</code>
	 */
	public static Long toLong(Object value, Long defaultValue) {
		Long result = toLong(value);
		if (result == null) {
			return defaultValue;
		}
		return result;
	}

	/**
	 * Converts value to <code>long</code>. Returns default value
	 * when conversion result is <code>null</code>.
	 */
	public static long toLongValue(Object value, long defaultValue) {
		Long result = toLong(value);
		if (result == null) {
			return defaultValue;
		}
		return result.longValue();
	}

	/**
	 * Converts value to <code>long</code> with common default value.
	 */
	public static long toLongValue(Object value) {
		return toLongValue(value, 0);
	}

	/**
	 * Converts value to <code>Float</code>. Returns <code>null</code>
	 * when conversion result is <code>null</code>
	 */
	public static Float toFloat(Object value) {
		if (value == null) {
			return null;
		}
		if (value.getClass() == Float.class) {
			return (Float) value;
		}
		if (value instanceof Number) {
			return Float.valueOf(((Number) value).floatValue());
		}
		if (value instanceof Boolean) {
			return ((Boolean) value).booleanValue() ? Float.valueOf(1) : Float
					.valueOf(0);
		}
		try {
			String stringValue = value.toString().trim();
			if (stringValue.startsWith("+")) {
				stringValue = stringValue.substring(1);
			}
			return Float.valueOf(stringValue);
		} catch (NumberFormatException nfex) {
		}
		return null;
	}

	/**
	 * Converts value to <code>Float</code>. Returns default value
	 * when conversion result is <code>null</code>
	 */
	public static Float toFloat(Object value, Float defaultValue) {
		Float result = toFloat(value);
		if (result == null) {
			return defaultValue;
		}
		return result;
	}

	/**
	 * Converts value to <code>float</code>. Returns default value
	 * when conversion result is <code>null</code>.
	 */
	public static float toFloatValue(Object value, float defaultValue) {
		Float result = toFloat(value);
		if (result == null) {
			return defaultValue;
		}
		return result.floatValue();
	}

	/**
	 * Converts value to <code>float</code> with common default value.
	 */
	public static float toFloatValue(Object value) {
		return toFloatValue(value, 0);
	}

	/**
	 * Converts value to <code>Double</code>. Returns <code>null</code>
	 * when conversion result is <code>null</code>
	 */
	public static Double toDouble(Object value) {
		if (value == null) {
			return null;
		}
		if (value.getClass() == Double.class) {
			return (Double) value;
		}
		if (value instanceof Number) {
			return Double.valueOf(((Number) value).doubleValue());
		}
		if (value instanceof Boolean) {
			return ((Boolean) value).booleanValue() ? Double.valueOf(1)
					: Double.valueOf(0);
		}
		try {
			String stringValue = value.toString().trim();
			if (stringValue.startsWith("+")) {
				stringValue = stringValue.substring(1);
			}
			return Double.valueOf(stringValue);
		} catch (NumberFormatException nfex) {
		}
		return null;
	}

	/**
	 * Converts value to <code>Double</code>. Returns default value
	 * when conversion result is <code>null</code>
	 */
	public static Double toDouble(Object value, Double defaultValue) {
		Double result = toDouble(value);
		if (result == null) {
			return defaultValue;
		}
		return result;
	}

	/**
	 * Converts value to <code>double</code>. Returns default value
	 * when conversion result is <code>null</code>.
	 */
	public static double toDoubleValue(Object value, double defaultValue) {
		Double result = toDouble(value);
		if (result == null) {
			return defaultValue;
		}
		return result.doubleValue();
	}

	/**
	 * Converts value to <code>double</code> with common default value.
	 */
	public static double toDoubleValue(Object value) {
		return toDoubleValue(value, 0);
	}

	/**
	 * Converts value to <code>Short</code>. Returns <code>null</code>
	 * when conversion result is <code>null</code>
	 */
	public static Short toShort(Object value) {
		if (value == null) {
			return null;
		}
		if (value.getClass() == Short.class) {
			return (Short) value;
		}
		if (value instanceof Number) {
			return Short.valueOf(((Number) value).shortValue());
		}
		if (value instanceof Boolean) {
			return ((Boolean) value).booleanValue() ? Short.valueOf((short) 1)
					: Short.valueOf((short) 0);
		}
		try {
			String stringValue = value.toString().trim();
			if (stringValue.startsWith("+")) {
				stringValue = stringValue.substring(1);
			}
			return Short.valueOf(stringValue);
		} catch (NumberFormatException nfex) {
		}
		return null;
	}

	/**
	 * Converts value to <code>Short</code>. Returns default value
	 * when conversion result is <code>null</code>
	 */
	public static Short toShort(Object value, Short defaultValue) {
		Short result = toShort(value);
		if (result == null) {
			return defaultValue;
		}
		return result;
	}

	/**
	 * Converts value to <code>short</code>. Returns default value
	 * when conversion result is <code>null</code>.
	 */
	public static short toShortValue(Object value, short defaultValue) {
		Short result = toShort(value);
		if (result == null) {
			return defaultValue;
		}
		return result.shortValue();
	}

	/**
	 * Converts value to <code>short</code> with common default value.
	 */
	public static short toShortValue(Object value) {
		return toShortValue(value, (short) 0);
	}

	/**
	 * Converts value to <code>Character</code>. Returns <code>null</code>
	 * when conversion result is <code>null</code>
	 */
	public static Character toCharacter(Object value) {
		if (value == null) {
			return null;
		}
		if (value.getClass() == Character.class) {
			return (Character) value;
		}
		if (value instanceof Number) {
			char c = (char) ((Number) value).intValue();
			return Character.valueOf(c);
		}
		try {
			String s = value.toString();
			if (s.length() != 1) {
				s = s.trim();
				if (!s.matches("^(-|\\+)?\\d+$")) {
					return null;
				}
				try {
					char c = (char) Integer.parseInt(s);
					return Character.valueOf(c);
				} catch (NumberFormatException nfex) {
					return null;
				}
			}
			return Character.valueOf(s.charAt(0));
		} catch (IndexOutOfBoundsException ioobex) {
		}
		return null;
	}

	/**
	 * Converts value to <code>Character</code>. Returns default value
	 * when conversion result is <code>null</code>
	 */
	public static Character toCharacter(Object value, Character defaultValue) {
		Character result = toCharacter(value);
		if (result == null) {
			return defaultValue;
		}
		return result;
	}

	/**
	 * Converts value to <code>char</code>. Returns default value
	 * when conversion result is <code>null</code>.
	 */
	public static char toCharValue(Object value, char defaultValue) {
		Character result = toCharacter(value);
		if (result == null) {
			return defaultValue;
		}
		return result.charValue();
	}

	/**
	 * Converts value to <code>char</code> with common default value.
	 */
	public static char toCharValue(Object value) {
		return toCharValue(value, (char) 0);
	}

	/**
	 * Converts value to <code>Byte</code>. Returns <code>null</code>
	 * when conversion result is <code>null</code>
	 */
	public static Byte toByte(Object value) {
		if (value == null) {
			return null;
		}
		if (value.getClass() == Byte.class) {
			return (Byte) value;
		}
		if (value instanceof Number) {
			return Byte.valueOf(((Number) value).byteValue());
		}
		if (value instanceof Boolean) {
			return ((Boolean) value).booleanValue() ? Byte.valueOf((byte) 1)
					: Byte.valueOf((byte) 0);
		}
		try {
			String stringValue = value.toString().trim();
			if (stringValue.startsWith("+")) {
				stringValue = stringValue.substring(1);
			}
			return Byte.valueOf(stringValue);
		} catch (NumberFormatException nfex) {
		}
		return null;
	}

	/**
	 * Converts value to <code>Byte</code>. Returns default value
	 * when conversion result is <code>null</code>
	 */
	public static Byte toByte(Object value, Byte defaultValue) {
		Byte result = toByte(value);
		if (result == null) {
			return defaultValue;
		}
		return result;
	}

	/**
	 * Converts value to <code>byte</code>. Returns default value
	 * when conversion result is <code>null</code>.
	 */
	public static byte toByteValue(Object value, byte defaultValue) {
		Byte result = toByte(value);
		if (result == null) {
			return defaultValue;
		}
		return result.byteValue();
	}

	/**
	 * Converts value to <code>byte</code> with common default value.
	 */
	public static byte toByteValue(Object value) {
		return toByteValue(value, (byte) 0);
	}

	/**
	 * Converts value to <code>Timestamp</code>. Returns <code>null</code>
	 * when conversion result is <code>null</code>
	 */
	public static Timestamp toTimestamp(String value, String pattern) {
		if (value == null || value.trim().length() == 0) {
			return null;
		}
		String stringValue = value.trim();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			Date date = dateFormat.parse(stringValue);
			return new Timestamp(date.getTime());
		} catch (ParseException e) {
		}
		try {
			long milliseconds = Long.parseLong(stringValue);
			return new Timestamp(milliseconds);
		} catch (NumberFormatException nfex) {
		}
		try {
			return Timestamp.valueOf(stringValue);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Converts value to <code>Timestamp</code>. Returns default value
	 * when conversion result is <code>null</code>
	 */
	public static Timestamp toTimestamp(String value, String pattern,
			Timestamp defaultValue) {
		Timestamp result = toTimestamp(value, pattern);
		if (result == null) {
			return defaultValue;
		}
		return result;
	}

	/**
	 * Converts value to <code>Timestamp</code>. Returns <code>null</code>
	 * when conversion result is <code>null</code>
	 */
	public static Timestamp toTimestamp(String value) {
		if (value == null || value.trim().length() == 0) {
			return null;
		}
		String stringValue = value.trim();
		try {
			long milliseconds = Long.parseLong(stringValue);
			return new Timestamp(milliseconds);
		} catch (NumberFormatException nfex) {
		}
		try {
			return Timestamp.valueOf(stringValue);
		} catch (Exception e) {
		}
		try {
			SimpleDateFormat dateFormat = null;
			if (stringValue.matches("^\\d{4}\\-\\d{1,2}\\-\\d{1,2}$")) {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			} else if (stringValue.matches("^\\d{4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}\\:\\d{1,2}\\:\\d{1,2}$")) {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			} else if (stringValue.matches("^\\d{4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}\\:\\d{1,2}\\:\\d{1,2}\\.\\d{1,3}$")) {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			} else if (stringValue.matches("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$")) {
				dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			} else if (stringValue.matches("^\\d{1,2}\\/\\d{1,2}\\/\\d{4} \\d{1,2}\\:\\d{1,2}\\:\\d{1,2}$")) {
				dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			} else if (stringValue.matches("^\\d{1,2}\\/\\d{1,2}\\/\\d{4} \\d{1,2}\\:\\d{1,2}\\:\\d{1,2}\\.\\d{1,3}$")) {
				dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.S");
			} else if (stringValue.matches("^\\d{4}\\/\\d{1,2}\\/\\d{1,2}$")) {
				dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			} else if (stringValue.matches("^\\d{4}\\/\\d{1,2}\\/\\d{1,2} \\d{1,2}\\:\\d{1,2}\\:\\d{1,2}$")) {
				dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			} else if (stringValue.matches("^\\d{4}\\/\\d{1,2}\\/\\d{1,2} \\d{1,2}\\:\\d{1,2}\\:\\d{1,2}\\.\\d{1,3}$")) {
				dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.S");
			}
			if (dateFormat != null) {
				Date date = dateFormat.parse(stringValue);
				return new Timestamp(date.getTime());
			}
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * Converts value to <code>Timestamp</code>. Returns default value
	 * when conversion result is <code>null</code>
	 */
	public static Timestamp toTimestamp(String value, Timestamp defaultValue) {
		Timestamp result = toTimestamp(value);
		if (result == null) {
			return defaultValue;
		}
		return result;
	}

	/**
	 * Converts value to <code>Date</code>. Returns <code>null</code>
	 * when conversion result is <code>null</code>
	 */
	public static Date toDate(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof Date) {
			return (Date) value;
		}
		if (value instanceof Calendar) {
			return new Date(((Calendar) value).getTimeInMillis());
		}
		if (value instanceof Number) {
			return new Date(((Number) value).longValue());
		}
		String stringValue = value.toString().trim();
		try {
			long milliseconds = Long.parseLong(stringValue);
			return new Date(milliseconds);
		} catch (NumberFormatException nfex) {
		}
		try {
			return toTimestamp(stringValue);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Converts value to <code>Date</code>. Returns default value
	 * when conversion result is <code>null</code>
	 */
	public static Date toDate(Object value, Date defaultValue) {
		Date result = toDate(value);
		if (result == null) {
			return defaultValue;
		}
		return result;
	}

	/**
	 * Converts value to <code>Calendar</code>. Returns <code>null</code>
	 * when conversion result is <code>null</code>
	 */
	public static Calendar toCalendar(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof Calendar) {
			return (Calendar) value;
		}
		if (value instanceof Date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime((Date) value);
			return calendar;
		}
		if (value instanceof Number) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(((Number) value).longValue());
			return calendar;
		}
		String stringValue = value.toString().trim();
		try {
			long milliseconds = Long.parseLong(stringValue);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(milliseconds);
			return calendar;
		} catch (NumberFormatException nfex) {
		}
		try {
			Timestamp timestamp = Timestamp.valueOf(stringValue);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(timestamp);
			return calendar;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Converts value to <code>Calendar</code>. Returns default value
	 * when conversion result is <code>null</code>
	 */
	public static Calendar toCalendar(Object value, Calendar defaultValue) {
		Calendar result = toCalendar(value);
		if (result == null) {
			return defaultValue;
		}
		return result;
	}

	/**
	 * Converts value to <code>BigInteger</code>. Returns <code>null</code>
	 * when conversion result is <code>null</code>
	 */
	public static BigInteger toBigInteger(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof BigInteger) {
			return (BigInteger) value;
		}
		if (value instanceof Number) {
			return new BigInteger(String.valueOf(((Number) value).longValue()));
		}
		try {
			return new BigInteger(value.toString().trim());
		} catch (NumberFormatException nfex) {
		}
		return null;
	}

	/**
	 * Converts value to <code>BigInteger</code>. Returns default value
	 * when conversion result is <code>null</code>
	 */
	public static BigInteger toBigInteger(Object value, BigInteger defaultValue) {
		BigInteger result = toBigInteger(value);
		if (result == null) {
			return defaultValue;
		}
		return result;
	}

	/**
	 * Converts value to <code>BigDecimal</code>. Returns <code>null</code>
	 * when conversion result is <code>null</code>
	 */
	public static BigDecimal toBigDecimal(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof BigDecimal) {
			return (BigDecimal) value;
		}
		if (value instanceof Number) {
			return new BigDecimal(
					String.valueOf(((Number) value).doubleValue()));
		}
		try {
			return new BigDecimal(value.toString().trim());
		} catch (NumberFormatException nfex) {
		}
		return null;
	}

	/**
	 * Converts value to <code>BigDecimal</code>. Returns default value
	 * when conversion result is <code>null</code>
	 */
	public static BigDecimal toBigDecimal(Object value, BigDecimal defaultValue) {
		BigDecimal result = toBigDecimal(value);
		if (result == null) {
			return defaultValue;
		}
		return result;
	}

}
