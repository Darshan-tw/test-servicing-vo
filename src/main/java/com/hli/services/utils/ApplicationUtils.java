package com.hli.services.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class ApplicationUtils {

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }


    public static boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            java.util.Date date = sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}