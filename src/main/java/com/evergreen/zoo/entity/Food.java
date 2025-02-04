package com.evergreen.zoo.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Food {
    int foodID;
    String name;
    int supplierId;
    double price;
    int minQTY;
    int QtyOnHand;
}
