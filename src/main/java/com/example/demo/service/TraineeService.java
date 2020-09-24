package com.example.demo.service;

import com.example.demo.domain.Trainee;
import com.example.demo.dto.TraineeDto;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.util.DtoMapping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TraineeService {
    private TraineeRepository traineeRepository;
    private DtoMapping dtoMapping;

    public TraineeService(TraineeRepository traineeRepository, DtoMapping dtoMapping) {
        this.traineeRepository = traineeRepository;
        this.dtoMapping = dtoMapping;
        traineeRepository.save(Trainee.builder().name("Jack").isGrouped(false).build());
        traineeRepository.save(Trainee.builder().name("Rose").isGrouped(false).build());
        traineeRepository.save(Trainee.builder().name("Mary").isGrouped(false).build());
    }

    public List<TraineeDto> getTrainees(boolean grouped) {
        List<Trainee> traineeList = traineeRepository.findTraineesByIsGrouped(grouped);

        List<TraineeDto> traineeDtoList = traineeList.stream().map(trainee -> dtoMapping.TraineeDtoTransform(trainee))
                .collect(Collectors.toList());
        return traineeDtoList;
    }

    public void createTrainee(TraineeDto traineeDto) {
        traineeRepository.save(dtoMapping.TraineeTransform(traineeDto));
    }
}
