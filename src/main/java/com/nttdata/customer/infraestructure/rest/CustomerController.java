package com.nttdata.customer.infraestructure.rest;

import com.nttdata.customer.application.CustomerOperations;
import com.nttdata.customer.domain.Customer;
import com.nttdata.customer.infraestructure.model.dao.CustomerDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * CUSTOMERCONTROLLER.
 * Publica las operaciones del Cliente (Customer)
 */
@Slf4j
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    /**
     * Operaciones del cliente.
     */
    private final CustomerOperations customerOperations;
    /**
     * Busqueda de todos los clientes.
     * @return Flux<Customer>
     */
    @GetMapping
    public Flux<Customer> getAll() {
        return customerOperations.findAll();
    }

    /**
     * Búsqueda por Id de un cliente.
     * @param code
     * @return Mono<Customer>
     */
    @GetMapping("/{code}")
    public Mono<Customer> getById(@PathVariable final String code) {
        return customerOperations.findById(code);
    }

    /**
     * Registro de un cliente.
     * @param customer
     * @return Mono<ResponseEntity>
     */
    @PostMapping
    public Mono<ResponseEntity> post(@RequestBody final Customer customer) {
        return customerOperations.create(customer)
                .map(c -> ResponseEntity.status(HttpStatus.OK).body(c))
                .cast(ResponseEntity.class)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Datos invalidos"));
    }

    /**
     * Actualiza los datos de un cliente.
     * @param code
     * @param customer
     * @return Mono<Customer>
     */
    @PutMapping("/{code}")
    public Mono<Customer> put(@PathVariable final String code,
                              @RequestBody final Customer customer) {
        return customerOperations.update(code, customer);
    }

    /**
     * Elimina un cliente.
     * @param id
     * @return Mono<CustomerDao>
     */
    @DeleteMapping("/{id}")
    public  Mono<CustomerDao> delete(@PathVariable final String id) {
        return customerOperations.delete(id);
    }
}
