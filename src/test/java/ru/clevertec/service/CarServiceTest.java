package ru.clevertec.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.domain.Car;
import ru.clevertec.entity.CarEntity;
import ru.clevertec.exception.CarNotFoundException;
import ru.clevertec.mapper.CarMapper;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.util.TestData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {
    @Mock
    private CarRepository carRepository;
    @Mock
    private CarMapper carMapper;
    @InjectMocks
    private CarService carService;

    @Test
    @DisplayName("Should get cars method")
    void shouldGetCars() {
        CarEntity carEntity = TestData.generateCarEntity();
        List<CarEntity> carEntities = List.of(carEntity);
        List<Car> cars = List.of(TestData.generateCar().setId(carEntity.getId()));

        when(carRepository.getCars())
                .thenReturn(carEntities);
        when(carMapper.carEntitiesToCars(carEntities))
                .thenReturn(cars);

        List<Car> testCars = carService.getCars();

        assertFalse(testCars.isEmpty());
    }

    @Test
    @DisplayName("Should not get car if car does not exist")
    void shouldNotGetCarById() {
        UUID carUUID = UUID.randomUUID();
        when(carRepository.getCarById(carUUID))
                .thenReturn(Optional.empty());

        assertThrows(CarNotFoundException.byCarID(UUID.randomUUID()).getClass(), () -> carService.getCarById(carUUID));
    }

    @Test
    @DisplayName("Should add car in db")
    void shouldAddCarInDataBase() {
        UUID carId = UUID.randomUUID();
        CarEntity carEntity = TestData.generateCarEntity();
        carEntity.setId(carId);
        Car car = new Car();
        car.setId(carId);
        when(carRepository.getCarById(carId)).thenReturn(Optional.of(carEntity));

        carService.create(car);

        assertDoesNotThrow(() -> carService.getCarById(car.getId()));
    }

    @Test
    @DisplayName("Should update car in db")
    void shouldUpdateCarInDataBase() {
        UUID carId = UUID.randomUUID();
        Car car = TestData.generateCar().setId(carId);
        CarEntity carEntity = TestData.generateCarEntity().setId(carId);
        Car testCar = TestData.generateCar();
        List<CarEntity> carEntities = List.of(carEntity);

        when(carRepository.getCarById(carId))
                .thenReturn(Optional.of(carEntity));
        when(carMapper.carToCarEntity(car))
                .thenReturn(carEntity);
        when(carRepository.getCars())
                .thenReturn(carEntities);
        carService.update(carId, testCar);

        assertEquals(carId, carService.getCarById(carId).getId());
    }

    @Test
    @DisplayName("Should delete car from db")
    void shouldDeleteCarFromDataBase() {
        UUID carId = UUID.randomUUID();

        assertThrows(CarNotFoundException.class, () -> carService.delete(carId));
    }
}