package com.example.demo.service;

import com.example.demo.domain.Group;
import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
import com.example.demo.dto.GroupDto;
import com.example.demo.exception.Error;
import com.example.demo.exception.NoGroupException;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.util.DtoMapping;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class GroupService {
    private TrainerRepository trainerRepository;
    private TraineeRepository traineeRepository;
    private GroupRepository groupRepository;
    private DtoMapping dtoMapping;

    public GroupService(TrainerRepository trainerRepository, TraineeRepository traineeRepository,
                        GroupRepository groupRepository, DtoMapping dtoMapping) {
        this.trainerRepository = trainerRepository;
        this.traineeRepository = traineeRepository;
        this.groupRepository = groupRepository;
        this.dtoMapping = dtoMapping;
    }

    public List<GroupDto> getGroups() {
        List<Group> groupList = groupRepository.findAll();
        List<GroupDto> groupDtoList = groupList.stream().map(group -> dtoMapping.groupDtoTransform(group))
                .collect(Collectors.toList());
        return groupDtoList;
    }

    public List<GroupDto> createGroups() {
        resetGroup();

        List<Trainer> trainerList = this.trainerRepository.findAll();
        List<Trainee> traineeList = this.traineeRepository.findAll();
        int groupSize = trainerList.size()/2;
        if (groupSize == 0) {
            Error error = Error.builder().status(400).message("分组失败").build();
            throw new NoGroupException(error);
        }

        List<GroupDto> groupDtoList = new ArrayList<>(groupSize);
        Collections.shuffle(trainerList);
        Collections.shuffle(traineeList);

        for (Long i=0L; i<groupSize; i++) {
            Long groupId = i+1;
            GroupDto groupDto = GroupDto.builder()
                    .id(groupId).name((groupId) + "组")
                    .traineeList(new ArrayList<>())
                    .trainerList(new ArrayList<>())
                    .build();
            List<Trainer> groupTrainerList = groupDto.getTrainerList();
            for (int j=0; j<2; j++) {
                Trainer trainer = trainerList.remove(0);
                trainer.setIsGrouped(true);
                trainer.setGroupId(groupId);
                trainerRepository.save(trainer);
                groupTrainerList.add(trainer);
            }
            groupDtoList.add(groupDto);
        }

        for (int i=0; i<traineeList.size(); i++) {
            Trainee trainee  = traineeList.get(i);
            trainee.setIsGrouped(true);
            Integer groupId = i%groupSize+1;
            trainee.setGroupId(groupId.longValue());
            traineeRepository.save(trainee);
            groupDtoList.get(i%groupSize).getTraineeList().add(trainee);
        }


        groupDtoList.forEach(groupDto -> groupRepository.save(dtoMapping.groupTransform(groupDto)));

        return groupDtoList;
    }

    private void resetGroup() {
        List<Group> groupList = this.groupRepository.findAll();
        groupList.forEach(group -> {
            Long groupId = group.getId();
            List<Trainee> traineeList = this.traineeRepository.findAllByGroupId(groupId);
            List<Trainer> trainerList = this.trainerRepository.findAllByGroupId(groupId);
            traineeList.forEach(trainee -> {
                trainee.setGroupId(null);
                trainee.setIsGrouped(false);
            });
            trainerList.forEach(trainer -> {
                trainer.setGroupId(null);
                trainer.setIsGrouped(false);
            });
        });
        this.groupRepository.deleteAll();
    }

//    public void changeGroupName(int index, String newName) {
//        List<Group> GroupList = this.getGroupList();
//        GroupList.forEach((Group -> {
//            if (Group.getName().equals(newName)) {
//                throw new RuntimeException();
//            }
//        }));
//        GroupList.get(index).setName(newName);
//    }
}
