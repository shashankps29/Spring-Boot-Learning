package com.basic.SpringBoot.Basics.repository;

import com.basic.SpringBoot.Basics.entity.BasicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<BasicEntity, Long> {

}