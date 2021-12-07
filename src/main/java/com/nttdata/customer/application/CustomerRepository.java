package com.nttdata.customer.application;

import com.nttdata.customer.domain.Customer;
import com.nttdata.customer.infraestructure.model.dao.CustomerDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * CUSTOMERREPOSITORY.
 * Define las operaciones en la BD para el Cliente (Customer)
 */
public interface CustomerRepository {
    /**
     * Creación de cliente.
     * @param customer
     * @return Mono<Customer>
     */
    Mono<Customer> create(Customer customer);

    /**
     * Actualización de cliente.
     * @param id
     * @param customer
     * @return Mono<Customer>
     */
    Mono<Customer> update(String id, Customer customer);

    /**
     * Eliminación de cliente.
     * @param id
     * @return Mono<CustomerDao>
     */
    Mono<CustomerDao> delete(String id);

    /**
     * Búsqueda por Id de cliente.
     * @param id
     * @return Mono<Customer>
     */
    Mono<Customer> findById(String id);

    /**
     * Búsqueda de todos los clientes.
     * @return Flux<Customer>
     */
    Flux<Customer> findAll();
}
