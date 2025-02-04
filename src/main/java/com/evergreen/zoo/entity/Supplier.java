package com.evergreen.zoo.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Supplier {
    int supplierID;
    String name;
    String contact;
    String email;
    String address;
    String description;

    public Supplier(String name, String phone, String email, String address, String description) {
        this.name = name;
        this.contact = phone;
        this.email = email;
        this.address = address;
        this.description = description;
    }
}
