package com.brooks.fullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brooks.fullstack.definition.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
