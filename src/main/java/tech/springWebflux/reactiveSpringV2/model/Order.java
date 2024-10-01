package tech.springWebflux.reactiveSpringV2.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Builder
@Table (name = "customer_order")
public class Order {

  @Id
  private UUID orderId;
  private Long customerId;
  private Long productId;
  @Column ("amount")
  private BigDecimal price;
  @Column ("order_date")
  private LocalDateTime orderCreateDate;
  @Column ("address")
  private String deliveryAddress;

}
