package com.urise.webapp.util;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

public class DataUtil {
    public static LocalDate of(int year, int month) {
        //Objects.requireNonNull(year, "Year can't be NULL!");
//        Objects.requireNonNull(month, "Month date can't be NULL!");
        return LocalDate.of(year, Month.of(month), 1);
    }
}