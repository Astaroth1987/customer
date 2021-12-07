package com.nttdata.customer.infraestructure.repository;

import com.nttdata.customer.infraestructure.model.dao.CustomerDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
/**
 * ICUSTOMERCRUDREPOSITORY.
 * Define las operaciones (CRUD) del Cliente (Customer) el
 * cual extiende del Reactive CRUD.
 */
public interface ICustomerCrudRepository extends
        ReactiveCrudRepository<CustomerDao, String> {
}
