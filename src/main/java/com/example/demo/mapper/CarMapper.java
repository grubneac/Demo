package com.example.demo.mapper;

import com.example.demo.entity.CarEntity;
import com.example.demo.model.CarModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "model",       source = "modelName")
    @Mapping(target = "color",       source = "colorCar")
    @Mapping(target = "power",       source = "powerHorses")
    CarEntity toEntity(CarModel model);

    @InheritInverseConfiguration
    CarModel toModel(CarEntity entity);
}