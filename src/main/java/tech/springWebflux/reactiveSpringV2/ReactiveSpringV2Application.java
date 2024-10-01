package tech.springWebflux.reactiveSpringV2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.log4j.Log4j2;
import tech.springWebflux.reactiveSpringV2.service.OrderService;

@Log4j2
@SpringBootApplication
public class ReactiveSpringV2Application {

  @Autowired
  private OrderService orderService;

  public static void main (String[] args) {
    SpringApplication.run (ReactiveSpringV2Application.class, args);
  }

  @Bean
  CommandLineRunner initDatabase () {
    log.info ("Loading primary data....");
    return args -> orderService.saveData ();

  }
}
/**
 * DROP TABLE IF EXISTS customer_order; DROP TABLE IF EXISTS customers; DROP TABLE IF EXISTS
 * products;
 * <p>
 * CREATE TABLE customers ( customer_id SERIAL primary key, name VARCHAR(100), email VARCHAR(100)
 * );
 * <p>
 * CREATE TABLE products ( product_id SERIAL primary key, description VARCHAR(100), price
 * NUMERIC(10, 2) );
 * <p>
 * CREATE TABLE customer_order ( order_id uuid default gen_random_uuid() primary key, customer_id
 * int, product_id int, amount NUMERIC(10, 2), order_date TIMESTAMP WITH TIME ZONE default
 * CURRENT_TIMESTAMP, foreign key (customer_id) references customers(customer_id) on delete cascade,
 * foreign key (product_id) references products(product_id) );
 */