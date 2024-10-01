package tech.springWebflux.reactiveSpringV2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import tech.springWebflux.reactiveSpringV2.model.Customer;
import tech.springWebflux.reactiveSpringV2.repository.CustomerRepository;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private DatabaseClient databaseClient;

  /*  public Mono<Customer> saveCustomer (Customer customer) {
      return databaseClient.sql ("SELECT nextval('customer_sequence') AS nextId")
          .map (row -> row.get ("nextId", Long.class))
          .first ()
          .flatMap (nextId -> {
            customer.setCustomerId (nextId);
            return customerRepository.save (customer);
          });
    }*/
  public Mono<Customer> saveCustomer (Customer customer) {
    return databaseClient.sql ("SELECT nextval('customer_sequence') AS nextId")
        .map (row -> row.get ("nextId", Long.class))
        .first ()
        .flatMap (nextId -> {
          customer.setCustomerId (nextId);
          return databaseClient.sql ("INSERT INTO customers (customer_id, name,email) "
                                     + "VALUES (:id, :name, :email)")
              .bind ("id", customer.getCustomerId ())
              .bind ("name", customer.getName ())
              .bind ("email", customer.getEmail ())
              .then ();
        })
        .thenReturn (customer);
  }
}
