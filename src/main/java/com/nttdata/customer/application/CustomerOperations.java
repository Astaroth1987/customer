package com.nttdata.customer.application;

import com.nttdata.customer.domain.Customer;
import com.nttdata.customer.infraestructure.model.dao.CustomerDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * CUSTOMEROPERATIONS: Define las operaciones (CRUD) del Cliente (Customer)
 */

public interface CustomerOperations {
    public Mono<Customer> create(Customer customer);
    public Mono<Customer> update(String id, Customer customer);
    public Mono<CustomerDao> delete(String id);
    public Mono<Customer> findById(String id);
    public Flux<Customer> findAll();
}
