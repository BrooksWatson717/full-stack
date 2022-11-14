package com.brooks.fullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brooks.fullstack.definition.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
