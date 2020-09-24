package com.example.demo.service;

import com.example.demo.domain.Group;
import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GroupService {
    private TrainerRepository trainerRepository;
    private TraineeRepository traineeRepository;

    public GroupService(TrainerRepository trainerRepository, TraineeRepository traineeRepository) {
        this.trainerRepository = trainerRepository;
        this.traineeRepository = traineeRepository;
    }

    public List<Group> getGroups() {
        List<Group> groupList = createGroups();
        return groupList;
    }

    public List<Group> createGroups() {
        List<Trainer> trainerList = this.trainerRepository.findAll();
        List<Trainee> traineeList = this.traineeRepository.findAll();
        int groupSize = trainerList.size()/2;
        if (groupSize == 0) {
            return new ArrayList<>();
        }

        List<Group> groupList = new ArrayList<>(groupSize);
        Collections.shuffle(trainerList);
        Collections.shuffle(traineeList);

        for (Long i=0L; i<groupSize; i++) {
            Group group = Group.builder()
                    .id(i+1).name((i+1) + "组")
                    .traineeList(new ArrayList<>())
                    .trainerList(new ArrayList<>())
                    .build();
            List<Trainer> groupTrainerList = group.getTrainerList();
            for (int j=0; j<2; j++) {
                Trainer trainer = trainerList.remove(0);
                trainer.setIsGrouped(true);
                trainerRepository.save(trainer);
                groupTrainerList.add(trainer);
            }
            groupList.add(group);
        }

        for (int i=0; i<traineeList.size(); i++) {
            Trainee trainee  = traineeList.get(i);
            trainee.setIsGrouped(true);
            traineeRepository.save(trainee);
            groupList.get(i%groupSize).getTraineeList().add(trainee);
        }

        return groupList;
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
