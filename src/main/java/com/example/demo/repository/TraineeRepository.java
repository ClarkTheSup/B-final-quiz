package com.example.demo.repository;

import com.example.demo.domain.Trainee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraineeRepository extends CrudRepository<Trainee, Long> {
    List<Trainee> findTraineesByIsGrouped(Boolean grouped);

    @Override
    List<Trainee> findAll();

    List<Trainee> findAllByGroupId(Long groupId);
}
