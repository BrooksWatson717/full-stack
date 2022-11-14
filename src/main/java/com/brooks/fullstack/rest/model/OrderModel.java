package com.brooks.fullstack.rest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderModel {
    private Long id;
    private String item;
    private String description;
}
