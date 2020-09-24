package com.example.demo.service;

import com.example.demo.domain.Trainer;
import com.example.demo.dto.TrainerDto;
import com.example.demo.exception.Error;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.util.DtoMapping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public void deleteTrainer(Long trainer_id) {
        Optional<Trainer> trainer = trainerRepository.findById(trainer_id);
        if (trainer.isPresent()) {
            trainerRepository.deleteById(trainer_id);
        } else {
            Error error = Error.builder()
                    .message("删除的数据不存在")
                    .status(404)
                    .build();
            throw new DataNotFoundException(error);
        }

    }
}
