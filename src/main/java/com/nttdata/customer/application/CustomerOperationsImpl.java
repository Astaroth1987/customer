package com.nttdata.customer.application;

import com.nttdata.customer.domain.Customer;
import com.nttdata.customer.domain.CustomerResponse;
import com.nttdata.customer.domain.ErrorData;
import com.nttdata.customer.infraestructure.model.dao.CustomerDao;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * CUSTOMEROPERATIONSIMPL.
 * Implementa las operaciones (CRUD) del Cliente (Customer)
 */
@Slf4j
@Service
public class CustomerOperationsImpl  implements CustomerOperations {
    /**
     * Repositorio de cliente.
     */
    private final CustomerRepository repository;

    /**
     * Constructor.
     * @param customerRepository
     */
    public CustomerOperationsImpl(final CustomerRepository customerRepository) {
        this.repository = customerRepository;
    }

    /**
     * Creación de cliente.
     * @param customer
     * @return Mono<Customer>
     */
    @Override
    public Mono<Customer> create(final Customer customer) {
        log.info("[create] Inicio");
        if (validateData(customer)) {
            return repository.create(customer);
        } else {
            return Mono.empty();
        }
    }

    /**
     * Actualización de cliente.
      * @param id
     * @param customer
     * @return Mono<Customer>
     */
    @Override
    public Mono<Customer> update(final String id, final Customer customer) {
        return repository.update(id, customer);
    }

    /**
     * Elimina un cliente.
     * @param id
     * @return Mono<CustomerDao>
     */
    @Override
    public Mono<CustomerDao> delete(final String id) {
        return repository.delete(id);
    }

    /**
     * Busqueda por Id de un cliente.
     * @param id
     * @return Mono<Customer>
     */
    @Override
    public Mono<Customer> findById(final String id) {
        return repository.findById(id);
    }

    /**
     * Busqueda de todos los clientes.
     * @return Flux<Customer>
     */
    @Override
    public Flux<Customer> findAll() {
        return repository.findAll();
    }

    /**
     * Validación de los datos.
     * @param customer
     * @return boolean
     */
    public boolean validateData(final Customer customer) {
        boolean result = true;

        if (customer.getCustomerType().equals("")) {
            ErrorData error = new ErrorData();
            error.setName("customerType");
            error.setDescription("No debe ser vacio");
            result = false;
        }
        if (customer.getDocumentType().equals("")) {
            ErrorData error = new ErrorData();
            error.setName("documentType");
            error.setDescription("No debe ser vacio");

            result = false;
        }
        if (customer.getDocumentNumber().equals("")) {
            ErrorData error = new ErrorData();
            error.setName("documentNumber");
            error.setDescription("No debe ser vacio");
            result = false;
        }
        return  result;
    }

    /**
     * Asigna el Id del cliente al Bean.
     * @param customerResponse
     * @param customer
     * @return Mono<CustomerResponse>
     */
    private Mono<CustomerResponse> setIdCustomerResponse(
            final CustomerResponse customerResponse, final Customer customer) {
        log.info("[create] setIdCustomerResponse:" + customer.getCode());
        customerResponse.setCode(customer.getCode());
        return Mono.just(customerResponse);
    }
}
