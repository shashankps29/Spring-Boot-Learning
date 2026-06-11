package com.basic.SpringBoot.Basics.controller;

import com.basic.SpringBoot.Basics.entity.BasicEntity;
import com.basic.SpringBoot.Basics.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BasicController {
    @Autowired
    private PersonRepository personRepository;

    BasicEntity basic = new BasicEntity();

    @PostMapping("/s")
    public BasicEntity saveData(@RequestBody BasicEntity basic) {

        BasicEntity entity = new BasicEntity();

        entity.setName(basic.getName());
        entity.setEmailAddress(basic.getEmailAddress());

        return personRepository.save(entity);
    }

    @GetMapping("/getAll")
    public List<BasicEntity> getPerson() {
        return personRepository.findAll();
    }
}
