package com.evergreen.zoo.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ticket {
    String ticketID;
    double price;
    String ticketType;
}
