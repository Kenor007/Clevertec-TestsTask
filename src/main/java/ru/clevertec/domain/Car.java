package ru.clevertec.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.clevertec.common.CarType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Car {
    private UUID id;
    private String carModel;
    private CarType carType;
    private LocalDateTime manufacturingDate;
}