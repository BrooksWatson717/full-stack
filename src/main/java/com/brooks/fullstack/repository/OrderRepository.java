package com.brooks.fullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brooks.fullstack.definition.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
