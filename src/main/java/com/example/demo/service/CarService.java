package com.example.demo.service;

import com.example.demo.model.CarModel;

import java.util.List;

public interface CarService {

    List<CarModel> getAllCars();

    CarModel getCarByModel(String model);
}
