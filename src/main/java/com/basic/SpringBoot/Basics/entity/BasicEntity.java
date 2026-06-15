package com.basic.SpringBoot.Basics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Person")
@Getter
@Setter
public class BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @Column(unique = true)
    private String emailAddress;

//    public void setEmailAddress(String emailAddress){
//
//        this.emailAddress = emailAddress;
//    }
//
//    public String getEmailAddress() {
//        return emailAddress;
//    }
//
//    public void setName(String name){
//        this.name = name;
//    }
//
//    public String getName(){
//        return name;
//    }
}
