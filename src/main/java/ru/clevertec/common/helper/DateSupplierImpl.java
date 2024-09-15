package ru.clevertec.common.helper;

import java.time.LocalDateTime;

public class DateSupplierImpl implements DateSupplier {
    @Override
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}