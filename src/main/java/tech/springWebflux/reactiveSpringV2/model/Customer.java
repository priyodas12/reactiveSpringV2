package tech.springWebflux.reactiveSpringV2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Table (name = "customers")
public class Customer {

  @Id
  @Column ("customer_id")
  private Long customerId;
  private String name;
  private String email;
}
