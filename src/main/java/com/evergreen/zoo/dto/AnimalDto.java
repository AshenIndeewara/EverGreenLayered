package com.evergreen.zoo.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnimalDto {
    private String name;
    private String species;
    private String health;
    private String healthDescription = "None";
    private String gender;
    private int age;
    private String employeeID;
//
//    public AnimalDto(String name, String species, String health, String healthDescription, String gender, int age) {
//        this.name = name;
//        this.species = species;
//        this.health = health;
//        this.healthDescription = healthDescription;
//        this.gender = gender;
//        this.age = age;
//    }

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
