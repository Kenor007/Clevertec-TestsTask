package ru.clevertec.util;

import ru.clevertec.common.CarType;
import ru.clevertec.domain.Car;
import ru.clevertec.entity.CarEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class TestData {
    public static CarEntity generateCarEntity() {
        return new CarEntity()
                .setId(UUID.randomUUID())
                .setCarType(CarType.SEDAN)
                .setCarModel("Sedan")
                .setManufacturingDate(LocalDateTime.now());
    }

    public static Car generateCar() {
        return new Car()
                .setId(UUID.randomUUID())
                .setCarType(CarType.SEDAN)
                .setCarModel("Sedan")
                .setManufacturingDate(LocalDateTime.now());
    }
}