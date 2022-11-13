package com.brooks.fullstack.customer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {


    private final CustomerModelAssembler assembler;
    private final CustomerRepository repository;


    public CustomerController(CustomerRepository repository, CustomerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }
    
    @GetMapping()
    CollectionModel<EntityModel<Customer>> all() {
  
      List<EntityModel<Customer>> customers = repository.findAll().stream()
          .map(assembler::toModel) //
          .collect(Collectors.toList());
  
      return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).all()).withSelfRel());
    }

    
  @GetMapping("/{id}")
  EntityModel<Customer> one(@PathVariable Long id) {

    Customer customer = repository.findById(id) //
        .orElseThrow(null);

    return assembler.toModel(customer);
  }


    @PostMapping
    public ResponseEntity createCustomer(@RequestBody Customer customer) throws URISyntaxException {
        Customer savedCustomer = repository.save(customer);
        return ResponseEntity.created(new URI("/customers/" + savedCustomer.getId())).body(savedCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer currentCustomer = repository.findById(id).orElseThrow(RuntimeException::new);
        currentCustomer.setName(customer.getName());
        currentCustomer.setEmail(customer.getEmail());
        currentCustomer = repository.save(customer);

        return ResponseEntity.ok(currentCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
