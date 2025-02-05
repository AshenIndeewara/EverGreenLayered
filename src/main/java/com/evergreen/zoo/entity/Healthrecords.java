package com.evergreen.zoo.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Healthrecords {
    private int id;
    private String Description;
    private String Date;
    private int animalId;
    private String type;
}
