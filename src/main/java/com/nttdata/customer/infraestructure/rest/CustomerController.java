package com.nttdata.customer.infraestructure.rest;

import com.nttdata.customer.application.CustomerOperations;
import com.nttdata.customer.domain.Customer;
import com.nttdata.customer.infraestructure.model.dao.CustomerDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * CUSTOMERCONTROLLER: Publica las operaciones del Cliente (Customer)
 */
@Slf4j
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerOperations customerOperations;

    @GetMapping
    public Flux<Customer> getAll(){
        return customerOperations.findAll();
    }
    @GetMapping("/{code}")
    public Mono<Customer> getById(@PathVariable String code){
        return customerOperations.findById(code);
    }
    @PostMapping
    public Mono<Customer> post(@RequestBody Customer customer){
        return customerOperations.create(customer);
    }
    @PutMapping("/{code}")
    public Mono<Customer> put(@PathVariable String code, @RequestBody Customer customer){
        return customerOperations.update(code,customer);
    }
    @DeleteMapping("/{id}")
    public  Mono<CustomerDao> delete(@PathVariable String id){
        return customerOperations.delete(id);
    }
}
