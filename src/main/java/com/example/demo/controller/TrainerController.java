package com.example.demo.controller;

import com.example.demo.dto.TraineeDto;
import com.example.demo.dto.TrainerDto;
import com.example.demo.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @PostMapping("/trainers")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTrainer(@RequestBody @Valid TrainerDto trainerDto) {
        trainerService.createTrainer(trainerDto);
    }
}
