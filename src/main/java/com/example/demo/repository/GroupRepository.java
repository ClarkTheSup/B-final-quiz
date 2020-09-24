package com.example.demo.repository;

import com.example.demo.domain.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends CrudRepository<Group, Long> {
    @Override
    List<Group> findAll();
}
