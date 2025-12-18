package com.example.demo.controller;

import com.example.demo.exeption.DbResourceNotFound;
import com.example.demo.model.CarModel;
import com.example.demo.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarService carService;

    @Test
    void getAllCars_shouldReturnListOfCars() throws Exception {
        //given
        CarModel carModel = CarModel.builder()
                .modelName("Toyota Hilux")
                .powerHorses(200)
                .colorCar("Grey")
                .build();
        when(carService.getAllCars()).thenReturn(List.of(carModel));

        // when + then
        mockMvc.perform(get("/api/v1/cars")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                // JSON assertions
                .andExpect(jsonPath("$[0].modelName").value("Toyota Hilux"))
                .andExpect(jsonPath("$[0].powerHorses").value(200))
                .andExpect(jsonPath("$[0].colorCar").value("Grey"));
    }

    @Test
    void getCar_shouldReturn200AndBody_whenCarExists() throws Exception {
        // given
        CarModel carModel = CarModel.builder()
                .modelName("Hilux")
                .colorCar("Grey")
                .powerHorses(200)
                .build();

        when(carService.getCarByModel("Hilux"))
                .thenReturn(carModel);

        // when / then
        mockMvc.perform(get("/api/v1/cars/{id}", "Hilux")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.modelName").value("Hilux"))
                .andExpect(jsonPath("$.colorCar").value("Grey"))
                .andExpect(jsonPath("$.powerHorses").value(200));
    }

    @Test
    void getCar_shouldReturn404AndMessage_whenCarNotFound() throws Exception {
        // given
        when(carService.getCarByModel("unknown"))
                .thenThrow(new DbResourceNotFound("Car not found"));

        // when / then
        mockMvc.perform(get("/api/v1/cars/{id}", "unknown")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Car not found"));
    }

}