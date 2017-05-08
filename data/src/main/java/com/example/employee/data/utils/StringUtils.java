package com.example.employee.data.utils;

import org.apache.commons.lang3.text.WordUtils;

/**
 * Утилитный класс для работы со строками
 */
public class StringUtils {

    private static final  String menus = "-";

    public static String toCamelCase(String value) {
        return WordUtils.capitalizeFully(value);
    }

    private static String modifyString(char firstCharacter, String srcString, int indexOfSubstring) {
        return (indexOfSubstring < srcString.length())
                ? firstCharacter + srcString.substring(indexOfSubstring)
                : String.valueOf(firstCharacter);
    }

    public static boolean isEmpty(String value) {
        if (value == null || value.trim().length() == 0)
            return true;
        else
            return false;
    }

    public static String getMenusIfNull(String value )
    {
        if (isEmpty(value))
            return menus;
        return value;
    }

}
