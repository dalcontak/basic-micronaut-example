package com.takanadev.controller;

import com.takanadev.domain.Order;
import com.takanadev.repository.OrderRepository;
import io.micronaut.http.annotation.*;
import io.micronaut.http.HttpStatus;

import java.util.List;

@Controller("/orders")
public class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @Get
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Post
    @Status(HttpStatus.CREATED)
    public Order create(@Body Order order) {
        return repository.save(order);
    }
}