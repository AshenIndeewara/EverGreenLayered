package com.evergreen.zoo.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Visitor {
    int visitorID;
    String date;
    String name;
    String number;
    String email;
    int emID;
}
