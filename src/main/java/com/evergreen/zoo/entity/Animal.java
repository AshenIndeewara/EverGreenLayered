package com.evergreen.zoo.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Animal {
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
}
