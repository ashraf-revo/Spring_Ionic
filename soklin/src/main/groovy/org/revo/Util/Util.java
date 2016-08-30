package org.revo.Util;

/**
 * Created by revo on 5/9/16.
 */
public class Util {
    public static final String EMPTY = "";
    public static final int INDEX_NOT_FOUND = -1;

    public static String SubstringAfter(String str, String separator) {
        if (isEmpty(str)) {
            return str;
        }
        if (separator == null) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == INDEX_NOT_FOUND) {
            return EMPTY;
        }
        return str.substring(pos + separator.length());
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String SplitCut(String text) {
        String s = text.split(" ")[1];
        return s.substring(0, s.length() - 1);
    }
}
