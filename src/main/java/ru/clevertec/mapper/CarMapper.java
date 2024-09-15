package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.domain.Car;
import ru.clevertec.entity.CarEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    List<Car> carEntitiesToCars(List<CarEntity> carEntities);

    Car carEntityToCar(CarEntity carEntity);

    CarEntity carToCarEntity(Car car);
}