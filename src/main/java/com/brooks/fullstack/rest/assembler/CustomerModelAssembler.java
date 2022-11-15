package com.brooks.fullstack.rest.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.brooks.fullstack.definition.Customer;
import com.brooks.fullstack.rest.CustomerController;
import com.brooks.fullstack.rest.model.CustomerModel;

@Component
public class CustomerModelAssembler extends RepresentationModelAssemblerSupport<Customer, CustomerModel> {

    public CustomerModelAssembler() {
        super(CustomerController.class, CustomerModel.class);
    }

    @Override
    public CustomerModel toModel(Customer customer) {
        CustomerModel customerModel = instantiateModel(customer);

        customerModel.add( //
        linkTo(methodOn(CustomerController.class).one(customer.getId())).withSelfRel());
        customerModel.add(
        linkTo(methodOn(CustomerController.class).all()).withRel("customers"));

        customerModel.setId(customer.getId());
        customerModel.setName(customer.getName());
        customerModel.setEmail(customer.getEmail());
        return customerModel;

    }
}
