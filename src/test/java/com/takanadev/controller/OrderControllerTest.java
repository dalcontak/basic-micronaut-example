package com.takanadev.controller;

import com.takanadev.domain.Order;
import com.takanadev.dto.OrderCreateRequest;
import io.micronaut.context.annotation.Property;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Property(name = "micronaut.server.port", value = "-1")
@MicronautTest
class OrderControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testCreateAndGetOrders() {
        // 1. Create a new order
        OrderCreateRequest newOrderRequest = new OrderCreateRequest("Jane Doe", new BigDecimal("250.75"));
        HttpRequest<OrderCreateRequest> request = HttpRequest.POST("/orders", newOrderRequest);
        HttpResponse<Order> response = client.toBlocking().exchange(request, Order.class);

        assertEquals(HttpStatus.CREATED, response.getStatus());
        Order createdOrder = response.body();
        assertNotNull(createdOrder);
        assertEquals("Jane Doe", createdOrder.getCustomerName());

        // 2. Retrieve all orders and verify the new order is there
        List<Order> orders = client.toBlocking().retrieve(HttpRequest.GET("/orders"), Argument.listOf(Order.class));
        assertEquals(1, orders.size());
        assertEquals("Jane Doe", orders.get(0).getCustomerName());
        // Note: BigDecimal equality should be checked with compareTo
        assertEquals(0, new BigDecimal("250.75").compareTo(orders.get(0).getCost()));
    }
}