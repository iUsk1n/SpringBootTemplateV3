package com.market.sapphires.sbt.v3.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageUtil {

    private static MessageSource messageSource;

    public static void setMessageSource(MessageSource source) {
        messageSource = source;
    }

    public static String getValue(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    public static String getValue(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }

    public static String getValue(String key, Object... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }

    public static String getValue(String key, Locale locale, Object... args) {
        return messageSource.getMessage(key, args, locale);
    }

}
