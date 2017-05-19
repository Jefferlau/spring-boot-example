package com.jusfoun.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 刘体阳 jefferlzu@gmail.com
 *         Created on 2016-08-11
 */
public class CamelUnderlineUtils {

    private static final char UNDERLINE = '_';

    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i != 0) sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String underlineToCamel2(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile("_").matcher(param);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            //String.valueOf(Character.toUpperCase(sb.charAt(position)));
            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();
    }

    public static String firstCharLowerCase(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        char c1 = param.charAt(0);

        StringBuilder sb = new StringBuilder(param);
        sb.replace(0, 1, (String.valueOf(c1)).toLowerCase());
        return sb.toString();
    }

    public static void main(String[] args) {
        String entityName = "SysUser";
        System.out.println(camelToUnderline(entityName));
        System.out.println(firstCharLowerCase(entityName));
    }
}
