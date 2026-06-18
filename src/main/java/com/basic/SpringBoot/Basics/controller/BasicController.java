package com.basic.SpringBoot.Basics.controller;

import com.basic.SpringBoot.Basics.Dtos.BasicEntityDTO;
import com.basic.SpringBoot.Basics.entity.BasicEntity;
import com.basic.SpringBoot.Basics.repository.PersonRepository;
import com.basic.SpringBoot.Basics.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BasicController {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
            private BasicService basicService;

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
        entity.setPassword(basic.getPassword());

        return personRepository.save(entity);
    }

    //Get Request Without DTO
    @GetMapping("/getAll")
    public List<BasicEntity> getPerson() {
        return personRepository.findAll();
    }


    //Get Request With DTO
    @GetMapping("/get")
    public List <BasicEntityDTO> getAll(){

        return basicService.getAll();

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

//    @GetMapping("/home")
//    public String getHome(){
//        return "home fqifgqifg";
//    }
}
