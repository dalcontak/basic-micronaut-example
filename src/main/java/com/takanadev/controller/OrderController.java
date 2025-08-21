package com.takanadev.controller;

import com.takanadev.domain.Order;
import com.takanadev.repository.OrderRepository;
import io.micronaut.http.annotation.*;
import io.micronaut.http.HttpStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Controller("/orders")
@Tag(name = "Orders")
public class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @Get
    @Operation(summary = "Get all orders", description = "We can get all orders of the system")
    @ApiResponse(responseCode = "200", description = "Orders found")
    @ApiResponse(responseCode = "404", description = "There was an error")
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Post
    @Status(HttpStatus.CREATED)
    @Operation(summary = "Create an order", description = "We can create an order in the system")
    @ApiResponse(responseCode = "200", description = "Order created")
    @ApiResponse(responseCode = "404", description = "Order wasn't able to be created")
    public Order create(@Body Order order) {
        return repository.save(order);
    }
}