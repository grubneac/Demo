package com.example.demo.service;

import com.example.demo.entity.CarEntity;
import com.example.demo.exeption.DbResourceNotFound;
import com.example.demo.mapper.CarMapper;
import com.example.demo.model.CarModel;
import com.example.demo.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@ConditionalOnMissingBean(CarServiceMock.class)
public class CarServiceImp implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public List<CarModel> getAllCars() {
        List<CarEntity> carEntities = carRepository.getAllCars().orElse(List.of());
        return carEntities.stream()
                .map(carMapper::toModel)
                .toList();
    }

    @Override
    public CarModel getCarByModel(String model) throws DbResourceNotFound{
        CarEntity carEntity = carRepository.getCarByModelName(model)
                .orElseThrow(() -> new DbResourceNotFound("Car not found"));
        return carMapper.toModel(carEntity);
    }
}
