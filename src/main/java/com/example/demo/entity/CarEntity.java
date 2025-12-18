package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CarEntity {
    private String model;
    private String color;
    private int power;
}
