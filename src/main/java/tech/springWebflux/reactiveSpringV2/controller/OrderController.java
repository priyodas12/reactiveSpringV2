package tech.springWebflux.reactiveSpringV2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import tech.springWebflux.reactiveSpringV2.model.Order;
import tech.springWebflux.reactiveSpringV2.service.OrderService;

@RequestMapping ("/api")
@RestController
public class OrderController {

  @Autowired
  private OrderService orderService;

  @GetMapping ("/orders")
  public Mono<Order> getOrderDetails (@RequestBody Order order) {
    return orderService.saveOrder (order);
  }
}
