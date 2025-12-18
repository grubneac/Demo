package com.example.demo.repository;

import com.example.demo.entity.CarEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CarRepository {

    public Optional<List<CarEntity>> getAllCars() {
        CarEntity car1 = CarEntity.builder()
                .model("Toyota Auris")
                .color("Red")
                .power(100)
                .build();
        CarEntity car2 = CarEntity.builder()
                .model("Toyota Hilux")
                .color("Black")
                .power(200)
                .build();
        return Optional.of(List.of(car1, car2));
    }

    public Optional<CarEntity> getCarByModelName(String model) {
        String modelExist = "Hilux";
        if(modelExist.equals(model)) {
            return Optional.of(CarEntity.builder()
                    .model(modelExist)
                    .color("Black")
                    .power(200)
                    .build());
        } else {
            return Optional.empty();
        }
    }


}
