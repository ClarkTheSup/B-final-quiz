package com.example.demo.controller;

import com.example.demo.dto.TraineeDto;
import com.example.demo.dto.TrainerDto;
import com.example.demo.service.TraineeService;
import com.example.demo.service.TrainerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TrainerController {
    private TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("/trainers")
    public List<TrainerDto> getTrainer(@RequestParam(defaultValue = "true") Boolean grouped) {
        return trainerService.getTrainers(grouped);
    }
}
