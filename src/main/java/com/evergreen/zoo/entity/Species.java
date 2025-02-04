package com.evergreen.zoo.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Species {
    private String id;
    private String name;
    private String ConservationStatus;
    private int foodID;
}
