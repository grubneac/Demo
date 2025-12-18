package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarModel {
    private String modelName;
    private String colorCar;
    private int powerHorses;
}
