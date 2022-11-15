package com.brooks.fullstack.rest.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.brooks.fullstack.definition.Order;
import com.brooks.fullstack.rest.CustomerController;
import com.brooks.fullstack.rest.OrderController;
import com.brooks.fullstack.rest.model.OrderModel;

@Component
public class OrderModelAssembler extends RepresentationModelAssemblerSupport<Order, OrderModel> {

    public OrderModelAssembler() {
        super(OrderController.class, OrderModel.class);
    }

    @Override
    public OrderModel toModel(Order order) {
        OrderModel orderModel = instantiateModel(order);

        orderModel.add( //
        linkTo(methodOn(OrderController.class).one(order.getId())).withSelfRel());
        orderModel.add(
        linkTo(methodOn(OrderController.class).all()).withRel("orders"));

        orderModel.setId(order.getId());
        orderModel.setItem(order.getItem());
        orderModel.setDescription(order.getDescription());
        return orderModel;
    }
}
