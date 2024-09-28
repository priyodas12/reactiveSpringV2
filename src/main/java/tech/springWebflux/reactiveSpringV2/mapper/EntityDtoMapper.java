package tech.springWebflux.reactiveSpringV2.mapper;


import tech.springWebflux.reactiveSpringV2.model.Customer;

public class EntityDtoMapper {

  public static Customer toEntity (tech.springWebflux.reactiveSpringV2.dto.Customer customerDto) {
    return Customer
        .builder ()
        .customerId (customerDto.Id ())
        .name (customerDto.name ())
        .email (customerDto.email ())
        .build ();
  }
}
