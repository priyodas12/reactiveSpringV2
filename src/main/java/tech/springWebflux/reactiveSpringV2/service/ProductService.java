package tech.springWebflux.reactiveSpringV2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import tech.springWebflux.reactiveSpringV2.model.Product;
import tech.springWebflux.reactiveSpringV2.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private DatabaseClient databaseClient;

  @Autowired
  private ProductRepository productRepository;

  /*public Mono<Product> saveProduct1 (Product product) {
    return productRepository.save (product);
  }*/

  public Mono<Product> saveProduct (Product product) {
    return databaseClient.sql ("SELECT nextval('product_sequence') AS nextId")
        .map (row -> row.get ("nextId", Long.class))
        .first ()
        .flatMap (nextId -> {
          product.setProductId (nextId);
          return databaseClient.sql ("INSERT INTO products (product_id, description, price) "
                                     + "VALUES (:id, :description, :price)")
              .bind ("id", product.getProductId ())
              .bind ("description", product.getDescription ())
              .bind ("price", product.getPrice ())
              .then ();
        })
        .thenReturn (product);
  }
}
