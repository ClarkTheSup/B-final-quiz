package com.example.demo.util;

import com.example.demo.domain.Trainee;
import com.example.demo.dto.TraineeDto;

public class DtoMapping {
    public Trainee TraineeTransform(TraineeDto traineeDto) {
        return Trainee.builder()
                .id(traineeDto.getId())
                .name(traineeDto.getName())
                .isGrouped(traineeDto.getIsGrouped())
                .build();
    }

    public TraineeDto TraineeDtoTransform(Trainee trainee) {
        return TraineeDto.builder()
                .id(trainee.getId())
                .name(trainee.getName())
                .isGrouped(trainee.getIsGrouped())
                .build();
    }
}
