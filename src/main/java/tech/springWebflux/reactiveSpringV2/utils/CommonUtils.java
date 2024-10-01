package tech.springWebflux.reactiveSpringV2.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import static java.time.ZoneOffset.UTC;
import tech.springWebflux.reactiveSpringV2.model.Customer;
import tech.springWebflux.reactiveSpringV2.model.Order;
import tech.springWebflux.reactiveSpringV2.model.Product;

@Component
public class CommonUtils {

  @Autowired
  private Faker faker;

  public Customer getRandomCustomer () {
    return Customer.builder ()
        //.customerId (faker.number ().randomNumber (8, true))
        .name (faker.name ().fullName ())
        .email (faker.internet ().safeEmailAddress ())
        .build ();
  }

  public Product getRandomProduct () {
    return Product.builder ()
        //.productId (faker.number ().randomNumber (8, true))
        .price (BigDecimal.valueOf (faker.number ().randomDouble (2, 1000, 4000)))
        .description (faker.lorem ().sentence (4))
        .build ();
  }

  public Order getRandomOrder (Long customerId, Long productId) {
    return Order.builder ()
        //.orderId (UUID.randomUUID ())
        .customerId (customerId)
        .productId (productId)
        .price (BigDecimal.valueOf (new Random ().nextDouble (1000, 2000)))
        .deliveryAddress (faker.address ().fullAddress ())
        .orderCreateDate (LocalDateTime.now (UTC).minusDays (new Random ().nextInt (1000, 2000)))
        .build ();
  }
}
