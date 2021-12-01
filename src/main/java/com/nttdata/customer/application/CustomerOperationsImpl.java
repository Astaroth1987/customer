package com.nttdata.customer.application;

import com.nttdata.customer.domain.Customer;
import com.nttdata.customer.infraestructure.model.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * CUSTOMEROPERATIONSIMPL: Implementa las operaciones (CRUD) del Cliente (Customer)
 */
@Service
public class CustomerOperationsImpl  implements CustomerOperations{
    @Autowired
    CustomerRepository repository;

    @Override
    public Mono<Customer> create(Customer customer) {
        return repository.create(customer);
    }

    @Override
    public Mono<Customer> update(String id, Customer customer){
        return repository.update(id,customer);
    }

    @Override
    public Mono<CustomerDao> delete(String id) {
        return repository.delete(id);
    }

    @Override
    public Mono<Customer> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Customer> findAll() {
        return repository.findAll();
    }
}
