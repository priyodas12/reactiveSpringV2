package tech.springWebflux.reactiveSpringV2.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import tech.springWebflux.reactiveSpringV2.model.Product;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
}
