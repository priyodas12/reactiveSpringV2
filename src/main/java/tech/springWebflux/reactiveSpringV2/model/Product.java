package tech.springWebflux.reactiveSpringV2.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Table (name = "products")
public class Product {

  @Id
  @Column ("product_id")
  private Long productId;
  private String description;
  private BigDecimal price;
}
