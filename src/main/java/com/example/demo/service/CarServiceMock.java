package com.example.demo.service;

import com.example.demo.model.CarModel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "service.mock",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true)
public class CarServiceMock implements CarService {
    @Override
    public List<CarModel> getAllCars() {
        CarModel carModel = CarModel.builder()
                .modelName("Toyota Hilux1 Mock")
                .powerHorses(200)
                .colorCar("Grey")
                .build();
        return List.of(carModel);
    }

    @Override
    public CarModel getCarByModel(String model) {
        return CarModel.builder()
                .modelName("Toyota Hilux2 Mock")
                .powerHorses(200)
                .colorCar("Grey")
                .build();
    }
}
