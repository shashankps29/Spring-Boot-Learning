package com.basic.SpringBoot.Basics.repository;

import com.basic.SpringBoot.Basics.entity.BasicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<BasicEntity, Long> {

    List<BasicEntity> findByEmailAddress(String emailAddress);
}