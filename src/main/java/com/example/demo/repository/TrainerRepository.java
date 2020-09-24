package com.example.demo.repository;

import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends CrudRepository<Trainer, Long> {
    List<Trainer> findTrainersByIsGrouped(Boolean grouped);

    @Override
    List<Trainer> findAll();

    List<Trainer> findAllByGroupId(Long groupId);
}
