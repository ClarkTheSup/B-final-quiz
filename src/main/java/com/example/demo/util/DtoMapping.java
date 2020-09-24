package com.example.demo.util;

import com.example.demo.domain.Trainee;
import com.example.demo.dto.TraineeDto;
import org.springframework.stereotype.Component;

@Component
public class DtoMapping {
    public Trainee TraineeTransform(TraineeDto traineeDto) {
        return Trainee.builder()
                .id(traineeDto.getId())
                .name(traineeDto.getName())
                .email(traineeDto.getEmail())
                .office(traineeDto.getOffice())
                .zoomId(traineeDto.getZoomId())
                .build();
    }

    public TraineeDto TraineeDtoTransform(Trainee trainee) {
        return TraineeDto.builder()
                .id(trainee.getId())
                .name(trainee.getName())
                .email(trainee.getEmail())
                .office(trainee.getOffice())
                .zoomId(trainee.getZoomId())
                .build();
    }
}
