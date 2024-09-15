package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.domain.Car;
import ru.clevertec.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> findAllCars() {
        List<Car> cars = carService.getCars();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cars);
    }
}