package com.evergreen.zoo.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Animal {
    private int animalId;
    private String nickName;
    private int speciesId;
    private String gender;
    private int age;
    private int emID;
}
