package tech.springWebflux.reactiveSpringV2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;
import tech.springWebflux.reactiveSpringV2.model.Customer;
import tech.springWebflux.reactiveSpringV2.model.Order;
import tech.springWebflux.reactiveSpringV2.model.Product;
import tech.springWebflux.reactiveSpringV2.repository.OrderRepository;
import tech.springWebflux.reactiveSpringV2.utils.CommonUtils;

@Log4j2
@Service
public class OrderService {

  @Autowired
  private ProductService productService;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private CommonUtils commonUtils;

  public Mono<Order> saveOrder (Order order) {
    return orderRepository.save (order);
  }

  public void saveData () {
    for (int i = 0; i < 10; i++) {
      log.info ("creating bulk data...");
      var order = createBulkOrder ();
      order.subscribe (o -> log.info ("saved order; {}", o));
    }
  }

  public Mono<Order> createBulkOrder () {
    Customer customer = commonUtils.getRandomCustomer ();
    Product product = commonUtils.getRandomProduct ();
    var savedCustomer = customerService.saveCustomer (customer);
    var savedProduct = productService.saveProduct (product);
    log.info ("Creating Order...");
    return Mono.zip (savedCustomer, savedProduct)
        .flatMap (tuple -> {
          Long customerId = tuple.getT1 ().getCustomerId ();
          Long productId = tuple.getT2 ().getProductId ();
          log.info ("productId: {}, customerId: {}", productId, customerId);
          var order = commonUtils.getRandomOrder (customerId, productId);
          log.info ("order : {}", order);
          return orderRepository.save (order);
        });
  }
}



