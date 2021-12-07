package com.nttdata.customer.infraestructure.repository;

import com.nttdata.customer.application.CustomerRepository;
import com.nttdata.customer.domain.Customer;
import com.nttdata.customer.infraestructure.model.dao.CustomerDao;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * CUSTOMERCRUDREPOSITORY.
 * Implementa las operaciones (CRUD) del Cliente (Customer)
 */
@Component
public class CustomerCrudRepository implements CustomerRepository {
    /**
     * Operaciones del Repositorio.
     */
    private final ICustomerCrudRepository repository;

    /**
     * Constructor.
     * @param iCustomerCrudRepository
     */
    public CustomerCrudRepository(
            final ICustomerCrudRepository iCustomerCrudRepository) {
        this.repository = iCustomerCrudRepository;
    }
    /**
     * Regitra los datos del cliente (Personal o Empresarial).
     * @param customer
     * @return Mono<Customer>
     */
    @Override
    public Mono<Customer> create(final Customer customer) {
        return repository.save(mapCustomerToCustomerDao(customer))
                .map(this::mapCustomerDaoToCustomer);
    }
    /**
     * Actualiza los datos del cliente (Personal o Empresarial).
     * @param id
     * @param customer
     * @return Mono<Customer>
     */
    @Override
    public Mono<Customer> update(final String id, final Customer customer) {
        return repository.findById(id)
                .flatMap(p -> create(mapCustomerDaoToCustomer(p, customer)));

    }
    /**
     * Elimina los datos del cliente (Personal o Empresarial).
     * @param id
     * @return Mono<CustomerDao>
     */
    @Override
    public Mono<CustomerDao> delete(final String id) {
        return repository.findById(id)
                .flatMap(p -> repository.deleteById(p.getCode()).thenReturn(p));
    }
    /**
     * Busca por el Id (Code) los datos de un cliente (Personal o Empresarial).
     * @param id
     * @return Mono<Customer>
     */
    @Override
    public Mono<Customer> findById(final String id) {
        return repository.findById((id))
                .map(this::mapCustomerDaoToCustomer);
    }
    /**
     * Busca  los datos de todos  los clientes (Personal o Empresarial).
     * @return Flux<Customer>
     */
    @Override
    public Flux<Customer> findAll() {
        return repository.findAll()
                .map(this::mapCustomerDaoToCustomer);
    }
    /**
     * Crea un clase CustomerDao y asigna los datos de Customer.
     * @param customer
     * @return CustomerDao
     */
    private CustomerDao mapCustomerToCustomerDao(final Customer customer) {
        CustomerDao customerDao = new CustomerDao();
        customerDao.setCode(customer.getCode());
        customerDao.setDocumentNumber(customer.getDocumentNumber());
        customerDao.setCustomerType(customer.getCustomerType());
        customerDao.setDocumentType(customer.getDocumentType());
        customerDao.setName(customer.getName());
        customerDao.setBusinessName(customer.getBusinessName());
        customerDao.setState(customer.getState());
        return customerDao;
    }
    /**
     * Crea una clase Customer y asigna los datos de CustomerDao.
     * @param customerDao
     * @return Customer
     */
    private Customer mapCustomerDaoToCustomer(final CustomerDao customerDao) {
        Customer customer = new Customer();
        customer.setCode(customerDao.getCode());
        customer.setCustomerType(customerDao.getCustomerType());
        customer.setDocumentNumber(customerDao.getDocumentNumber());
        customer.setDocumentType(customerDao.getDocumentType());
        customer.setName(customerDao.getName());
        customer.setBusinessName(customerDao.getBusinessName());
        customer.setState(customerDao.getState());
        return customer;
    }
    /**
     * Asigna el Id (Code) de CustomerDao a Customer.
     * @param customerDao
     * @param customer
     * @return Customer
     */
    private Customer mapCustomerDaoToCustomer(
            final CustomerDao customerDao, final Customer customer) {
        customer.setCode(customerDao.getCode());
        return customer;
    }


}
