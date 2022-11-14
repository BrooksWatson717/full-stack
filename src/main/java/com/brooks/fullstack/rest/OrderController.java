package com.brooks.fullstack.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brooks.fullstack.definition.Order;
import com.brooks.fullstack.repository.OrderRepository;
import com.brooks.fullstack.rest.assembler.OrderModelAssembler;

@RestController
@RequestMapping("/orders")
public class OrderController {


    private final OrderModelAssembler assembler;
    private final OrderRepository repository;


    public OrderController(OrderRepository repository, OrderModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }
    
    @GetMapping()
    public CollectionModel<EntityModel<Order>> all() {
  
      List<EntityModel<Order>> orders = repository.findAll().stream()
          .map(assembler::toModel) //
          .collect(Collectors.toList());
  
      return CollectionModel.of(orders, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Order> one(@PathVariable Long id) {
  
      Order order = repository.findById(id) //
          .orElseThrow(null);
  
      return assembler.toModel(order);
    }
  
  
      @PostMapping
      public ResponseEntity createOrder(@RequestBody Order order) throws URISyntaxException {
          Order savedOrder = repository.save(order);
          return ResponseEntity.created(new URI("/orders/" + savedOrder.getId())).body(savedOrder);
      }
}
