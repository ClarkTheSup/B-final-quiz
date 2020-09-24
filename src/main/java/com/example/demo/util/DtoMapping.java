package com.example.demo.util;

import com.example.demo.domain.Group;
import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
import com.example.demo.dto.GroupDto;
import com.example.demo.dto.TraineeDto;
import com.example.demo.dto.TrainerDto;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DtoMapping {
    private TraineeRepository traineeRepository;
    private TrainerRepository trainerRepository;

    public DtoMapping(TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

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

    public Group groupTransform(GroupDto groupDto) {
        return Group.builder().id(groupDto.getId())
                .name(groupDto.getName())
                .build();
    }

    public GroupDto groupDtoTransform(Group group) {
        List<Trainee> traineeList = this.traineeRepository.findAllByGroupId(group.getId());
        List<Trainer> trainerList = this.trainerRepository.findAllByGroupId(group.getId());
        return GroupDto.builder().id(group.getId()).name(group.getName())
                .traineeList(traineeList).trainerList(trainerList).build();
    }
}
