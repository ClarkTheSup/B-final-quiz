package com.example.demo.util;

import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
import com.example.demo.dto.TraineeDto;
import com.example.demo.dto.TrainerDto;
import org.springframework.stereotype.Component;

@Component
public class DtoMapping {
    public Trainee traineeTransform(TraineeDto traineeDto) {
        return Trainee.builder()
                .id(traineeDto.getId())
                .name(traineeDto.getName())
                .email(traineeDto.getEmail())
                .office(traineeDto.getOffice())
                .zoomId(traineeDto.getZoomId())
                .isGrouped(false)
                .build();
    }

    public TraineeDto traineeDtoTransform(Trainee trainee) {
        return TraineeDto.builder()
                .id(trainee.getId())
                .name(trainee.getName())
                .email(trainee.getEmail())
                .office(trainee.getOffice())
                .zoomId(trainee.getZoomId())
                .build();
    }

    public Trainer trainerTransform(TrainerDto trainerDto) {
        return Trainer.builder()
                .id(trainerDto.getId())
                .name(trainerDto.getName())
                .isGrouped(false)
                .build();
    }

    public TrainerDto trainerDtoTransform(Trainer trainer) {
        return TrainerDto.builder()
                .id(trainer.getId())
                .name(trainer.getName())
                .build();
    }
}
