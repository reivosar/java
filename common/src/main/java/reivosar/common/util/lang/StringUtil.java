package reivosar.common.util.lang;

import org.apache.commons.lang3.StringUtils;

/**
 * Utility class for working with Strings.
 */
public final class StringUtil {
    
    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private StringUtil() {
        // This constructor must be private
    }
    
    /**
     * Returns the specified default string if the input string is null or empty.
     *
     * @param string        the input string to check
     * @param defaultString the default string to return if the input string is null or empty
     * @return the input string if it is not null or empty, or the default string otherwise
     */
    public static String defaultIfNullOrEmpty(final String string, final String defaultString) {
        return isNotEmpty(string) ? string : defaultString;
    }
    
    /**
     * Checks whether the specified CharSequence is empty (null or zero length).
     *
     * @param cs the CharSequence to check
     * @return true if the specified CharSequence is empty, false otherwise
     */
    public static boolean isEmpty(final CharSequence cs) {
        return StringUtils.isEmpty(cs);
    }
    
    /**
     * Checks whether the specified CharSequence is not empty (not null and non-zero length).
     *
     * @param cs the CharSequence to check
     * @return true if the specified CharSequence is not empty, false otherwise
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }
}
