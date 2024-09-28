package tech.springWebflux.reactiveSpringV2.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import tech.springWebflux.reactiveSpringV2.model.Customer;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {


}
