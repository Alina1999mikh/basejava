package com.urise.webapp.storage.util;

import java.time.LocalDate;
import java.time.Month;

public class DataUtil {
    public static LocalDate of(int year, int month) {
        return LocalDate.of(year, Month.of(month), 1);
    }
}