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
        personRepository.save(user);

        return user;

    }

    @DeleteMapping("del/{id}")
    public void del(@PathVariable Long id){
         personRepository.deleteById(id);

    }

    @PatchMapping("/upd/{id}")
    public BasicEntity updatePartial(@PathVariable("id") Long id, @RequestBody BasicEntity entity){
        BasicEntity user = personRepository.findById(id).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

       if(entity.getName()!=null) {
           user.setName(entity.getName());

       }

       if(entity.getEmailAddress()!=null) {
           user.setEmailAddress(entity.getEmailAddress());

       }

        personRepository.save(user);

        return user;

    }
}
