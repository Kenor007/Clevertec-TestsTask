package ru.clevertec.repository;

import org.springframework.stereotype.Repository;
import ru.clevertec.common.CarType;
import ru.clevertec.entity.CarEntity;
import ru.clevertec.exception.CarNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CarRepository {
    private static final List<CarEntity> db = new ArrayList<>(List.of(
            CarEntity.builder()
                    .id(UUID.randomUUID())
                    .carType(CarType.SEDAN)
                    .carModel("Sedan1")
                    .manufacturingDate(LocalDateTime.now())
                    .build(),
            CarEntity.builder()
                    .id(UUID.randomUUID())
                    .carType(CarType.SEDAN)
                    .carModel("Sedan2")
                    .manufacturingDate(LocalDateTime.now())
                    .build(),
            CarEntity.builder()
                    .id(UUID.randomUUID())
                    .carType(CarType.CABRIOLET)
                    .carModel("Cabriolet1")
                    .manufacturingDate(LocalDateTime.now())
                    .build())
    );

    public List<CarEntity> getCars() {
        return db;
    }

    public Optional<CarEntity> getCarById(UUID carId) {
        return db.stream()
                .filter(carEntity -> carEntity.getId().equals(carId))
                .findFirst();
    }

    public boolean create(CarEntity carEntity) {
        try {
            return db.add(carEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CarEntity update(UUID carId, CarEntity newCarEntity) {
        CarEntity carById = getCarById(carId)
                .orElseThrow(() -> CarNotFoundException.byCarID(carId));

        int index = db.indexOf(carById);
        if (index >= 0) {
            db.set(index, newCarEntity);
            return newCarEntity;
        } else {
            throw CarNotFoundException.byCarID(carId);
        }
    }

    public void delete(UUID carId) {
        CarEntity carById = getCarById(carId)
                .orElseThrow(() -> CarNotFoundException.byCarID(carId));
        db.remove(carById);
    }
}