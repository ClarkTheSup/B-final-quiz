package com.example.demo.service;

import com.example.demo.domain.Trainee;
import com.example.demo.dto.TraineeDto;
import com.example.demo.exception.Error;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.util.DtoMapping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        traineeRepository.save(Trainee.builder().name("Bob").isGrouped(false).build());
        traineeRepository.save(Trainee.builder().name("Chris").isGrouped(false).build());
        traineeRepository.save(Trainee.builder().name("Json").isGrouped(false).build());
        traineeRepository.save(Trainee.builder().name("Peter").isGrouped(false).build());
        traineeRepository.save(Trainee.builder().name("Irving").isGrouped(false).build());
        traineeRepository.save(Trainee.builder().name("James").isGrouped(false).build());
    }

    public List<TraineeDto> getTrainees(boolean grouped) {
        List<Trainee> traineeList = traineeRepository.findTraineesByIsGrouped(grouped);

        List<TraineeDto> traineeDtoList = traineeList.stream().map(trainee -> dtoMapping.traineeDtoTransform(trainee))
                .collect(Collectors.toList());
        return traineeDtoList;
    }

    public void createTrainee(TraineeDto traineeDto) {
        traineeRepository.save(dtoMapping.traineeTransform(traineeDto));
    }

    public void deleteTrainee(Long trainee_id) {
        Optional<Trainee> trainee = traineeRepository.findById(trainee_id);
        if (trainee.isPresent()) {
            traineeRepository.deleteById(trainee_id);
        } else {
            Error error = Error.builder()
                    .message("删除的数据不存在")
                    .status(404)
                    .build();
            throw new DataNotFoundException(error);
        }
    }
}
