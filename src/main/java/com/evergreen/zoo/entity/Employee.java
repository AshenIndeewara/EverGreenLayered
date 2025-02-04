package com.evergreen.zoo.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String position;
    private String hier_date;
    private String userId;

    public Employee(int id,String name, String email, String phone, String address, String position) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.position = position;
    }
    public Employee(String name, String email, String phone, String address, String position) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.position = position;
    }
    public Employee(String name, String email, String phone, String address, String position, String hier_date) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.position = position;
        this.hier_date = hier_date;
    }

}
