package ru.clevertec.exception;

import java.util.UUID;

public class CarNotFoundException extends RuntimeException {
    private CarNotFoundException(String message) {
        super(message);
    }

    public static CarNotFoundException byCarID(UUID carID) {
        return new CarNotFoundException(
                String.format("Car not found by id %s", carID)
        );
    }
}