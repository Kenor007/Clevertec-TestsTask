package ru.clevertec.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.clevertec.service.CarService;
import ru.clevertec.util.TestData;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
class CarControllerTest {
    @MockBean
    private CarService carService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Find all cars method")
    void shouldFindAllCars() throws Exception {
        when(carService.getCars()).thenReturn(List.of(TestData.generateCar()));

        mockMvc.perform(get("/api/v1/cars")).andExpect(status().isOk());
    }
}