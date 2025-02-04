package com.evergreen.zoo.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ticket {
    private double total;
    private String paymentMethod;
    private int adult;
    private int child;
    private int foreigner;
    private int foreignerChild;
    private int student;
    private String name;
    private String email;
    private String num;
    private String employeeId;
}
