package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.domain.Car;
import ru.clevertec.entity.CarEntity;
import ru.clevertec.exception.CarNotFoundException;
import ru.clevertec.mapper.CarMapper;
import ru.clevertec.repository.CarRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarService {
    private final static CarRepository CAR_REPOSITORY = new CarRepository();
    private final CarMapper carMapper;

    public List<Car> getCars() {
        List<CarEntity> carEntities = CAR_REPOSITORY.getCars();
        return carMapper.carEntitiesToCars(carEntities);
    }

    public Car getCarById(UUID carId) {
        CarEntity carEntity = CAR_REPOSITORY.getCarById(carId)
                .orElseThrow(() -> CarNotFoundException.byCarID(carId));
        return carMapper.carEntityToCar(carEntity);
    }

    public Car create(Car car) {
        try {
            CarEntity carEntity = carMapper.carToCarEntity(car);
            CAR_REPOSITORY.create(carEntity);
            return car;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Car update(UUID carId, Car newCar) {
        CarEntity existingEntity = CAR_REPOSITORY.getCarById(carId)
                .orElseThrow(() -> CarNotFoundException.byCarID(carId));
        CarEntity updatedEntity = carMapper.carToCarEntity(newCar);
        updatedEntity.setId(existingEntity.getId());

        CarEntity resultEntity = CAR_REPOSITORY.update(carId, updatedEntity);
        return carMapper.carEntityToCar(resultEntity);
    }

    public void delete(UUID carId) {
        CAR_REPOSITORY.delete(carId);
    }
}