package com.nttdata.customer.domain;

import lombok.Data;

/**
 * CUSTOMER.
 * La clase customer contendrá  información de los clientes Personales
 * y Empresariales
 */
@Data
public class Customer {
    /**
     * Codigo del cliente.
     */
    private String code;
    /**
     * Tipo de cliente.
     */
    private CustomerType customerType;
    /**
     * Tipo de documento.
     */
    private DocumentType documentType;
    /**
     * Numero de documento.
     */
    private String documentNumber;
    /**
     * Nombre del cliente.
     */
    private String name;
    /**.
     * Razon social del cliente.
     */
    private String businessName;
    /**
     * Estado: Activo, Inactivo.
     */
    private Status state;
}
