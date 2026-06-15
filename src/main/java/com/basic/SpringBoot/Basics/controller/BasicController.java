package com.basic.SpringBoot.Basics.controller;

import com.basic.SpringBoot.Basics.entity.BasicEntity;
import com.basic.SpringBoot.Basics.repository.PersonRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BasicController {
    @Autowired
    private PersonRepository personRepository;

    BasicEntity basic = new BasicEntity();

    @PostMapping("/s")
    public BasicEntity saveData(@RequestBody BasicEntity basic) {
       List<BasicEntity> existing = personRepository.findByEmailAddress(basic.getEmailAddress());
       if(!existing.isEmpty()){
           throw new RuntimeException("User Already Exist");
       }
        BasicEntity entity = new BasicEntity();
        entity.setName(basic.getName());
        entity.setEmailAddress(basic.getEmailAddress());

        return personRepository.save(entity);
    }

    @GetMapping("/getAll")
    public List<BasicEntity> getPerson() {
        return personRepository.findAll();
    }

    @PutMapping("/update/{id}")
    public BasicEntity update(@PathVariable Long id, @RequestBody BasicEntity entity){

        BasicEntity user= personRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));

        user.setName(entity.getName());
        user.setEmailAddress(entity.getEmailAddress());
        return personRepository.save(user);

    }

    @DeleteMapping("del/{id}")
    public void del(@PathVariable Long id){
         personRepository.deleteById(id);

    }
}
