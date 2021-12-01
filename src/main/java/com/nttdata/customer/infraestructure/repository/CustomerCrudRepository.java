package com.nttdata.customer.infraestructure.repository;

import com.nttdata.customer.application.CustomerRepository;
import com.nttdata.customer.domain.Customer;
import com.nttdata.customer.infraestructure.model.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * CUSTOMERCRUDREPOSITORY: Implementa las operaciones (CRUD) del Cliente (Customer)
 */
@Component
public class CustomerCrudRepository implements CustomerRepository {
    @Autowired
    ICustomerCrudRepository repository;

    /*
    create: Regitra los datos del cliente (Personal o Empresarial)
     */
    @Override
    public Mono<Customer> create(Customer customer) {
        return repository.save(mapCustomerToCustomerDao(customer))
                .map(this::mapCustomerDaoToCustomer);
    }
    /*
    update: Actualiza los datos del cliente (Personal o Empresarial)
     */
    @Override
    public Mono<Customer> update(String id, Customer customer) {
        return repository.findById(id)
                .flatMap( p ->create(mapCustomerDaoToCustomer(p,customer)));

    }
    /*
    delete: Elimina los datos del cliente (Personal o Empresarial)
     */
    @Override
    public Mono<CustomerDao> delete(String id) {
        return repository.findById(id)
                .flatMap(p -> repository.deleteById(p.getCode()).thenReturn(p));
    }
    /*
    findById: Busca por el Id (Code) los datos de un cliente (Personal o Empresarial)
     */
    @Override
    public Mono<Customer> findById(String id) {
        return repository.findById( (id))
                .map( this::mapCustomerDaoToCustomer);
    }
    /*
    findAll: Busca  los datos de todos  los clientes (Personal o Empresarial)
     */
    @Override
    public Flux<Customer> findAll() {
        return repository.findAll()
                .map(this::mapCustomerDaoToCustomer);
    }

    /*
    mapCustomerToCustomerDao: Crea un clase CustomerDao y asigna los datos de Customer
     */
    private CustomerDao mapCustomerToCustomerDao (Customer customer){
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
    /*
    mapCustomerDaoToCustomer: Crea una clase Customer y asigna los datos de CustomerDao
     */
    private Customer mapCustomerDaoToCustomer (CustomerDao customerDao){
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
    /*
    mapCustomerDaoToCustomer: Asigna el Id (Code) de CustomerDao a Customer
    */
    private Customer mapCustomerDaoToCustomer (CustomerDao customerDao,  Customer customer){
        customer.setCode(customerDao.getCode());
        return customer;
    }


}
