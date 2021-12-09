package com.nttdata.customer.infraestructure.rest;

import com.nttdata.customer.application.CustomerOperations;
import com.nttdata.customer.domain.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


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
    public Mono<ResponseEntity<Flux<Customer>>> getAll() {
        return Mono.just(
                ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(customerOperations.findAll()));
    }

    /**
     * Búsqueda por Id de un cliente.
     * @param code Identificador del cliente
     * @return Mono<Customer>
     */
    @GetMapping("/{code}")
    public Mono<ResponseEntity<Customer>> getById(@PathVariable final String code) {
        return customerOperations.findById(code)
                .map( customer -> ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(customer))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Registro de un cliente.
     * @param customer Datos del cliente.
     * @return Mono<ResponseEntity>
     */
    @PostMapping
    public Mono<ResponseEntity<Customer>> post(@RequestBody final Customer customer) {
        return customerOperations.create(customer)
                .map(c -> ResponseEntity.status(HttpStatus.OK).body(c))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Actualiza los datos de un cliente.
     * @param code Codigo.
     * @param customer Cliente.
     * @return Mono<Customer>
     */
    @PutMapping("/{code}")
    public Mono<ResponseEntity<Customer>> put(@PathVariable final String code,
                              @RequestBody final Customer customer) {
        return customerOperations.update(code, customer)
                .map(c -> ResponseEntity.status(HttpStatus.OK).body(c))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Elimina un cliente.
     * @param id Codigo.
     * @return Mono<CustomerDao>
     */
    @DeleteMapping("/{id}")
    public  Mono<ResponseEntity<Void>> delete(@PathVariable final String id) {
        return customerOperations.delete(id)
                .map(v -> ResponseEntity.noContent().<Void>build())
                 .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
