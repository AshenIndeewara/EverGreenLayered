package com.evergreen.zoo.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Animal {
    private String animalID;
    private String name;
    private String species;
    private String health;
    private String healthDescription = "None";
    private String gender;
    private int age;
    private String employeeID;

    public void clearAll(){
        this.name = "";
        this.species = "";
        this.health = "";
        this.healthDescription = "None";
        this.gender = "";
        this.age = 0;
        this.employeeID = "";
    }
    public Animal(String name, String species, String health, String healthDescription, String gender, int age, String employeeID) {
        this.name = name;
        this.species = species;
        this.health = health;
        this.healthDescription = healthDescription;
        this.gender = gender;
        this.age = age;
        this.employeeID = employeeID;
    }
}
