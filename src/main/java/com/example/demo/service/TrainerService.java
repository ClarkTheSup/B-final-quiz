package com.example.demo.service;

import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
import com.example.demo.dto.TraineeDto;
import com.example.demo.dto.TrainerDto;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.util.DtoMapping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerService {
    private TrainerRepository trainerRepository;
    private DtoMapping dtoMapping;

    public TrainerService(TrainerRepository trainerRepository, DtoMapping dtoMapping) {
        this.trainerRepository = trainerRepository;
        this.dtoMapping = dtoMapping;
        trainerRepository.save(Trainer.builder().name("娟姐").isGrouped(false).build());
        trainerRepository.save(Trainer.builder().name("亚峰").isGrouped(false).build());
        trainerRepository.save(Trainer.builder().name("梦秋").isGrouped(false).build());
    }

    public List<TrainerDto> getTrainers(Boolean grouped) {
        List<Trainer> trainerList = trainerRepository.findTrainersByIsGrouped(grouped);

        List<TrainerDto> trainerDtoList = trainerList.stream().map(trainer -> dtoMapping.trainerDtoTransform(trainer))
                .collect(Collectors.toList());
        return trainerDtoList;
    }

    public void createTrainer(TrainerDto trainerDto) {
        trainerRepository.save(dtoMapping.trainerTransform(trainerDto));
    }
}
