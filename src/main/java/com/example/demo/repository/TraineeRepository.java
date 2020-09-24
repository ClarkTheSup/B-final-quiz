package com.example.demo.repository;

import com.example.demo.domain.Trainee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraineeRepository extends CrudRepository<Trainee, Integer> {
    List<Trainee> findTraineesByIsGrouped(Boolean grouped);
}
