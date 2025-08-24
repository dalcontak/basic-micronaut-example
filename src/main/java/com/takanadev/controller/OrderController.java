package com.takanadev.controller;

import com.takanadev.domain.Order;
import com.takanadev.dto.OrderCreateRequest;
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
    @ApiResponse(responseCode = "200", description = "Returns all orders, or an empty list if none exist.")
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Post
    @Status(HttpStatus.CREATED)
    @Operation(summary = "Create an order", description = "Creates a new order in the system.")
    @ApiResponse(responseCode = "201", description = "Order created successfully.")
    @ApiResponse(responseCode = "400", description = "Invalid order data provided.")
    public Order create(@Body OrderCreateRequest request) {
        Order order = new Order();
        order.setCustomerName(request.customerName());
        order.setCost(request.cost());
        return repository.save(order);
    }
}