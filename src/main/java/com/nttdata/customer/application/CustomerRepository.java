package com.nttdata.customer.application;

import com.nttdata.customer.domain.Customer;
import com.nttdata.customer.infraestructure.model.dao.CustomerDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * CUSTOMERREPOSITORY: Define las operaciones en la BD para el Cliente (Customer)
 */
public interface CustomerRepository {
    public Mono<Customer> create(Customer customer);
    public Mono<Customer> update(String id, Customer customer);
    public Mono<CustomerDao> delete(String id);
    public Mono<Customer> findById(String id);
    public Flux<Customer> findAll();
}
