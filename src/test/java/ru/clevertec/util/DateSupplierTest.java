package ru.clevertec.util;

import ru.clevertec.common.helper.DateSupplier;

import java.time.LocalDateTime;

public class DateSupplierTest implements DateSupplier {
    @Override
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.parse("2024-09-11:00:00");
    }
}