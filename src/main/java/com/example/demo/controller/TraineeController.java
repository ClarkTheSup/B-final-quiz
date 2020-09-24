package com.example.demo.controller;

import com.example.demo.dto.TraineeDto;
import com.example.demo.service.TraineeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TraineeController {
    private TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping("/trainees")
    public List<TraineeDto> getTrainees(@RequestParam(defaultValue = "true") boolean grouped) {
        return traineeService.getTrainees(grouped);
    }
}
