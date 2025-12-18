package com.example.demo.service;

import com.example.demo.entity.CarEntity;
import com.example.demo.mapper.CarMapper;
import com.example.demo.model.CarModel;
import com.example.demo.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class CarServiceImpTest {

    @Mock
    private CarMapper carMapper;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImp carService;

    @Test
    void getAllCars() {
        // given
        CarEntity entity1 = CarEntity.builder()
                .model("Hilux")
                .color("Black")
                .power(300)
                .build();

        CarEntity entity2 = CarEntity.builder()
                .model("Auris")
                .color("White")
                .power(100)
                .build();

        List<CarEntity> entities = List.of(entity1, entity2);

        when(carRepository.getAllCars()).thenReturn(Optional.of(entities));

        CarModel model1 = CarModel.builder()
                .modelName("Hilux")
                .colorCar("Black")
                .powerHorses(300)
                .build();

        CarModel model2 = CarModel.builder()
                .modelName("Auris")
                .colorCar("White")
                .powerHorses(100)
                .build();

        when(carMapper.toModel(entity1)).thenReturn(model1);
        when(carMapper.toModel(entity2)).thenReturn(model2);

        //when
        List<CarModel> actualList = carService.getAllCars();

        //then
        assertThat(actualList.get(0).getColorCar()).isEqualTo(model1.getColorCar());
        assertThat(actualList.get(0).getPowerHorses()).isEqualTo(model1.getPowerHorses());
        assertThat(actualList.get(0).getModelName()).isEqualTo(model1.getModelName());

        assertThat(actualList.get(1).getColorCar()).isEqualTo(model2.getColorCar());
        assertThat(actualList.get(1).getPowerHorses()).isEqualTo(model2.getPowerHorses());
        assertThat(actualList.get(1).getModelName()).isEqualTo(model2.getModelName());
    }

    @Test
    void getCarByModel() {
        //given
        String modelName = "Hilux";
        CarEntity entity = CarEntity.builder()
                .model(modelName)
                .color("Black")
                .power(300)
                .build();

        when(carRepository.getCarByModelName(modelName)).thenReturn(Optional.of(entity));

        CarModel model = CarModel.builder()
                .modelName(modelName)
                .colorCar("Black")
                .powerHorses(300)
                .build();

        when(carMapper.toModel(entity)).thenReturn(model);

        //when
        CarModel actualCarByModel = carService.getCarByModel(modelName);

        //then
        assertThat(actualCarByModel.getColorCar()).isEqualTo(model.getColorCar());
        assertThat(actualCarByModel.getModelName()).isEqualTo(model.getModelName());
        assertThat(actualCarByModel.getPowerHorses()).isEqualTo(model.getPowerHorses());
    }
}