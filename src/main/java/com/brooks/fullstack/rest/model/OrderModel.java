package com.brooks.fullstack.rest.model;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderModel extends RepresentationModel<OrderModel>{
    private Long id;
    private String item;
    private String description;
}
